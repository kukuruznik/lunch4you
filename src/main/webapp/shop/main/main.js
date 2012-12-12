steal(
	"jquery/controller",
	"jquery/event/bbq",
	"jquery/dom/cookie",
	"shop/utils",
	"shop/order/new/newOrder.js",
	"shop/menu/list",
	"shop/referral/new/newReferral.js",
	"shop/profile/edit/editProfile.js"
).then( function( $ ) {

	/**
	 * @class Shop.Main
	 * @parent index
	 * @inherits jQuery.Controller Main controller for the shop application.
	 */
	$.Controller( 'Shop.Main',

	/** @Static */
	{
		_availableLocales: [ "cz", "en" ],
		
		_navigationItems: [ "menu", "referral" ],

		getNavigationItems : function() {
			return this._navigationItems;
		},

		getAvailableLocales : function() {
			return this._availableLocales;
		},

		getLocale: function() {
			if ( !this.locale )
				this.locale = this._load( "locale" ) || "cz";
			return this.locale;
		},

		setLocale: function( locale ) {
			for ( var i = 0; i < this._availableLocales.length; i++ ) {
				if ( this._availableLocales[ i ] == locale ) {
					this._save( "locale", locale );
					this.locale = locale;
					break;
				}
			}
		},

		getToken: function() {
			if ( !this.token )
				this.token = this._load( "token" );
			return this.token;
		},

		setToken: function( token ) {
			this._save( "token", token );
			this.token = token;
		},

		_load: function( key ) {
			var value = $.cookie( key );
			steal.dev.log( "load from cookie: ", key, " = ", value );
			//alert( "load from cookie: " + key + " = " + value );
			return value;
		},

		_save: function( key, value ) {
			steal.dev.log( "save into cookie: ", key, " = ", value );
			$.cookie( key, value );
		},

		getDeliveryLocationById : function (id){
			var result = $.grep(Shop.deliveryLocations, function(deliveryLocation){
				return deliveryLocation.id == id;
			});
			if(result.length == 0){
				alert("Error, DeliveryLocation not found for id : " + id);
				return null;
			}
			if(result.length > 1){
				alert("Error, Multiple DeliveryLocations found for id : " + id);
				return null;
			}
			return result[0];
				
		},

		/**
		 * Reloads current customer object (by token). This method should be called after any updates to user's profile
		 */
		reloadCustomer: function() {
			var token = this.getToken();
			Shop.Models.Customer.findByToken( token, this.proxy( "_customerLoaded" ) );
		}

	},

	/** @Prototype */
	{
		init: function() {
			steal.dev.log( "Shop main controller initialized" );

			// we need to set this up in order to prevent IE from caching the AJAX responses
			$.ajaxSetup( { cache: false } );

			// register view helpers
			$.EJS.Helpers.prototype.currentView = function() {
				return this.msg( "header." + Shop.params.view );
			};

			$.EJS.Helpers.prototype.msg = function( key ) {
				return Shop.Main.dictionary[ key ];
			};

			$.EJS.Helpers.prototype.localize = function( object, attrName ) {
				return object[ attrName + "_" + Shop.Main.getLocale() ];
			};

			// if the user token is set in the URL then save it and remove from the URL
			var hash = $.bbq.getState();

			if ( hash.token ) {
				this.Class.setToken( hash.token );
				//$.bbq.removeState( "token" );
			}

			// if we know the user token, then let's get the user data from the server,
			// otherwise we set user to null and only anonymous actions will be enabled
			var token = this.Class.getToken();
			//alert( "token = " + token);

			var customerDfr = null;
			
			if ( token ) {
				customerDfr = Shop.Models.Customer.findByToken( token, this.proxy( "_customerLoaded" ) );
			} else {
				customerDfr = function(){
					return null;
				};
			}
			
			
			var deliveryLocationsDfr = Shop.Models.DeliveryLocation.findAll( this.proxy( "_deliveryLocationsLoaded" ) );
			
			var dictionaryDfr = Shop.Models.Dictionary.loadDictionary( this.Class.getLocale(), this.proxy( "_dictionaryLoaded" ) );

			$.when( customerDfr, deliveryLocationsDfr, dictionaryDfr ).done( this.proxy( "_dataLoaded" ) );
			
		},

		_dataLoaded: function( customerResponse, deliveryLocationsResponse, dictionaryResponse ) {
			
			// render the main page structure
			$( "#content" ).html( this.view( 'page', this ) );

			this.initialized = true;
			
			// start the UI by triggering the initial hash change event
			$( function() {
				$( window ).trigger( "hashchange" );
			});
		},

		_customerLoaded: function( customer ) {
			Shop.customer = customer;
		},

		_deliveryLocationsLoaded: function( deliveryLocations ) {
			Shop.deliveryLocations = deliveryLocations;
		},

		_dictionaryLoaded: function( dictionary ) {
			this.Class.dictionary = dictionary;
		},

		_loadDictionary : function ( locale ){
			var dictionaryDfr = Shop.Models.Dictionary.loadDictionary( this.Class.getLocale(), this.proxy( "_dictionaryLoaded" ) );
			$.when(dictionaryDfr).done(this.proxy( function(){

				var cl = "main-lang-flag-" + this.Class.getLocale();
				$("#main-lang-flag").attr("class", cl);

				$( function() {
					$( window ).trigger( "hashchange" );
				});
			}));
		},

		"{window} hashchange": function( el, evt ) {
			if ( !this.initialized )
				return;

			Shop.params = $.bbq.getState();
			steal.dev.log( "Hash changed: ", Shop.params );

			// URL validation and view selection
			switch ( Shop.params.view ) {
			case "menu":
				this._setScreen( "shop_menu_list" );
				break;
			case "order":
				if ( Shop.params.meal ) {
					this._setScreen( "shop_order_new" );
				} else {
					alert( "Invalid URL! Missing article." );
				}
				break;
			case "referral":
				this._setScreen( "shop_referral_new" );
				break;
			case "profile":
				this._setScreen( "shop_profile_edit" );
				break;
			default:
				alert( "Invalid URL! Unknown or missing view." );
			};
		},
		
		"#main-customer-link click" : function( el, evt ) {
			window.location.hash = "#view=profile&token=" + Shop.params.token;
		},

		"#main-lang-selector click": function( el, evt ) {
			var newLocale = el.attr("locale");

			steal.dev.log( "Switching to locale: ", newLocale );
			Shop.Main.setLocale( newLocale );

			// initiate screen refresh
			this._loadDictionary( newLocale );
		},
		
		"#main-navigation-selector click" : function( el, evt ) {
			var navIndex = el.attr("navigationIndex");
			var navItem = this.Class.getNavigationItems()[navIndex];
			window.location.hash = "#view=" + navItem + "&token=" + Shop.params.token;
		},

		_setScreen: function( controllerName ) {
			$( "#main-screen-name" ).html( $.EJS.Helpers.prototype.currentView() );
			$( '#main-detail' )[ controllerName ]();
		}
	});

	Shop.errorHandler = function( jqXHR, textStatus, error ) {
		alert( "An error occured!\nStatus: " + textStatus + "\nDetails: " + error );
	};
});


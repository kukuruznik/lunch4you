steal( "jquery/controller", "jquery/event/bbq", "jquery/dom/cookie", "shop/order/new", "shop/menu/list" ).then( function( $ ) {

	/**
	 * @class Shop.Main
	 * @parent index
	 * @inherits jQuery.Controller Main controller for the shop application.
	 */
	$.Controller( 'Shop.Main',

	/** @Static */
	{
		_availableLocales: [ "cz", "en" ],

		getLocale: function() {
			if ( !this.locale )
				this.locale = $.cookie( "locale" ) || "cz";
			return this.locale;
		},

		getOtherLocale: function() {
			var loc = this.getLocale();

			if ( loc == "cz" )
				return "en";
			else
				return "cz";
		},

		setLocale: function( locale ) {
			for ( var i = 0; i < this._availableLocales.length; i++ ) {
				if ( this._availableLocales[ i ] == locale ) {
					this.locale = locale;
					$.cookie( "locale", locale );
					break;
				}
			}
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

			// load localization data
			this._loadDictionary( Shop.Main.getLocale() );
		},

		"{window} hashchange": function( el, evt ) {
			Shop.params = $.bbq.getState();
			console.log( "Hash changed: ", Shop.params );

			// URL validation and view selection
			switch ( Shop.params.view ) {
			case "menu":
				if ( Shop.params.token ) {
					$( "#screen-name" ).html( $.EJS.Helpers.prototype.currentView() );
					$( '#content' ).shop_menu_list();
				} else {
					alert( "Invalid URL! Missing user." );
				}
				break;
			case "order":
				if ( Shop.params.meal && Shop.params.token ) {
					$( "#screen-name" ).html( $.EJS.Helpers.prototype.currentView() );
					$( '#content' ).shop_order_new();
				} else {
					alert( "Invalid URL! Missing user and/or article." );
				}
				break;
			default:
				alert( "Invalid URL! Unknown or missing view." );
			}
		},

		"#change-lang click": function( el, evt ) {
			var newLocale = Shop.Main.getOtherLocale();

			steal.dev.log( "Switching to locale: ", newLocale );
			Shop.Main.setLocale( newLocale );

			// initiate screen refresh
			this._loadDictionary( newLocale );
		},

		_loadDictionary: function( locale ) {
			$.ajax({
	  			url: "shop/i18n/" + locale + ".dict",
	  			dataType: "json",
	  			success: this.proxy( "_dictionaryLoaded" ),
	  			error: function() {
	  				alert( "Error loading localization data for locale '" + locale + "'" );
	  			}
	  		});
		},

		_dictionaryLoaded: function( dictionary ) {
			steal.dev.log( "... finishing main controller initialization" );
			Shop.Main.dictionary = dictionary;

			// render the main page structure
			$( "#content" ).html( this.view( 'page', this ) );

			// start the UI by triggering the initial hash change event
			$( function() {
				$( window ).trigger( "hashchange" );
			});
		}
	});

	Shop.errorHandler = function( jqXHR, textStatus, error ) {
		alert( "An error occured!\nStatus: " + textStatus + "\nDetails: " + error );
	};
});


steal(
	'jquery/controller',
	'jquery/view/ejs', 
	'jquery/controller/view', 
	'shop/models/dictionary.js'
	
).then(	

	'./views/restaurantWeekly.ejs',
	'./views/restaurantDaily.ejs',


	function( $ ) {

	/**
	 * @parent index
	 * @inherits jQuery.Controller Main controller for the shop application.
	 */
	$.Controller( "Backoffice.Menu.Restaurant",

	/** @Static */
	{
		_availableLocales: [ "cz", "en" ],
		
		getAvailableLocales : function() {
			return this._availableLocales;
		},
		
		getLocale : function() {
			return "cz";
		} 

	},

	/** @Prototype */
	{
		init: function() {
			//steal.dev.log( "Shop main controller initialized" );
			
			// reference to another window with menu
			this.menuWindow = null;
			
			// we need to set this up in order to prevent IE from caching the AJAX responses
			$.ajaxSetup( { cache: false } );
			
			Backoffice.Main.dictionary = new Array();


			$.EJS.Helpers.prototype.msg = function( key, locale ) {
				return Backoffice.Main.dictionary[locale][ key ];
			};

			$.EJS.Helpers.prototype.localize = function( object, attrName, locale ) {
				
				return object[ attrName + "_" + (locale ? locale : Backoffice.Main.getLocale()) ];
			};
			
			var self = this;
			window.menuOpened = function( menuWindow ) {
				self._loadRestaurantData( menuWindow );
			};
		

			
			var dictionaryDfrEN = Shop.Models.Dictionary.loadDictionary( "en", this.proxy( "_dictionaryLoadedEN" ) );
			var dictionaryDfrCZ = Shop.Models.Dictionary.loadDictionary( "cz", this.proxy( "_dictionaryLoadedCZ" ) );
//
//			$.when( customerDfr, deliveryLocationsDfr, dictionaryDfr ).done( this.proxy( "_render" ) );
			
		},


		_dictionaryLoadedEN: function( dictionary ) {
			Backoffice.Main.dictionary["en"] = dictionary;
		},

		_dictionaryLoadedCZ: function( dictionary ) {
			Backoffice.Main.dictionary["cz"] = dictionary;
		},


		_loadRestaurantData: function( menuWindow ) {
			if (this.menuType == "restaurantWeekly"){
				
			} else if (this.menuType == "restaurantDaily") {
				
			}
			var activeDelivery = null;
			var activeRestaurant = true;
			Backoffice.Models.Article.getGroupedMenu( activeDelivery, activeRestaurant ).done( this.proxy( "_renderRestaurantMenu" ) );
		},
		
		_renderRestaurantMenu : function( groupedMenu ) {
			$(this.menuWindow.document.body).html( this.view( this.menuType, {groupedMenu : groupedMenu, locale : this.menuLocale} ) );
		},

		"div.menu-link click": function( el, evt ) {
			
			// save menu type to controller to refer to later
			this.menuType = el.attr("menuType");

			// save menu type to controller to refer to later
			this.menuLocale = el.attr("locale");

			
			this.menuWindow = window.open("backoffice/menu/restaurant/menuRestaurant.html", "menuWindow");
		}
	});

});


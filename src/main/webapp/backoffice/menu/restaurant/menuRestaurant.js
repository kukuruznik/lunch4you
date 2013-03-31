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
		
	},

	/** @Prototype */
	{
		init: function() {
			//steal.dev.log( "Shop main controller initialized" );
			
			// reference to another window with menu
			this.menuWindow = null;
			
			
			var self = this;
			window.menuOpened = function( menuWindow ) {
				self._loadRestaurantData( menuWindow );
			};
		},


		_loadRestaurantData: function( menuWindow ) {

			var activeDelivery = null;
			var activeRestaurantWeekly = null;
			var activeRestaurantDaily = null;

			if (this.menuType == "restaurantWeekly"){
				activeRestaurantWeekly = true;				
			} else if (this.menuType == "restaurantDaily") {
				activeRestaurantDaily = true;				
			}
			Backoffice.Models.Article.getGroupedMenu( activeDelivery, activeRestaurantWeekly, activeRestaurantDaily ).done( this.proxy( "_renderRestaurantMenu" ) );
		},
		
		_renderRestaurantMenu : function( groupedMenu ) {
			$(this.menuWindow.document.body).html( this.view( this.menuType, {groupedMenu : groupedMenu, locale : this.menuLocale} ) );
		},

		"div.menu-link click": function( el, evt ) {
			
			// save menu type to controller to refer to later
			this.menuType = el.attr("menuType");

			// save locale to controller to refer to later
			this.menuLocale = el.attr("locale");

			
			this.menuWindow = window.open("backoffice/menu/restaurant/menuRestaurant.html", "menuWindow");
		}
	});

});


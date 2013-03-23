steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view' ).then( 
	
	"./views/list.ejs", 
	"./views/restaurantMenu.ejs", 
	
	function( $ ) {

	/**
	 * @class Shop.Menu.List
	 * @parent index
	 * @inherits jQuery.Controller Lists articles.
	 */
	$.Controller( 'Shop.Menu.List',

	/** @Static */
	{
	},

	/** @Prototype */
	{
		init: function() {
			steal.dev.log( "Menu list controller initialized" );
			this._reloadMenu();
		},

		update: function() {
			this._reloadMenu();
		},

		_reloadMenu: function() {
			var activeDelivery = true;
			var activeRestaurant = null;
			Shop.Models.Article.getGroupedMenu( activeDelivery, activeRestaurant ).done( this.proxy( "_render" ) );
		},

		_render: function( menu ) {
			this.groupedMenu = menu;
			this.element.html( this.view( 'list', this.groupedMenu ) );
		},
	} );

} );

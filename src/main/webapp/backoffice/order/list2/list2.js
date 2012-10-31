steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view' ).then( './views/list2.ejs', './views/deliveryLocationWithArticles.ejs', function( $ ) {

	/**
	 * @class Backoffice.Order.List
	 * @parent index
	 * @inherits jQuery.Controller Lists articles.
	 */
	$.Controller( 'Backoffice.Order.List2',

	/** @Static */
	{
	},

	/** @Prototype */
	{
		init: function() {
			steal.dev.log( "Order list2 controller initialized" );
			//return;
//			this.counter = 0;
			this._refresh();
		},

		update: function() {
			this._refresh();
		},

		_refresh: function() {
			Backoffice.Models.Order.getActiveOrdersGroupedByDeliveryLocation().done( this.proxy( "_renderOrdersGroupedByDeliveryLocation" ) );
		},

		_renderOrdersGroupedByDeliveryLocation: function( ordersGroupedByArticle ) {
			this.element.html( this.view( 'list2', ordersGroupedByArticle ) );
		}

	} );

} );

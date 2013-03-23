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
			this.timeOutID = setTimeout( this.proxy( "_refresh" ), 60000 );
		},
		
		".delivery-notification-button click": function( el, evt ) {
			var orderIds = el.attr("orderIds").split(",");
			var action = "notifyDelivery";
			Backoffice.Models.Order.executeAction( orderIds, action, this.proxy( "_handleActionResponse" ) );
		},

		_handleActionResponse: function( notFound ) {
			if ( notFound.length > 0 )
				alert( "Orders with ID-s " + notFound + " not found!" );
			
			this._refresh();
		},

		_renderOrdersGroupedByDeliveryLocation: function( ordersGroupedByArticle ) {
			this.element.html( this.view( 'list2', ordersGroupedByArticle ) );
		}

	} );

} );

steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view' ).then( './views/packagingList.ejs', function( $ ) {

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
			//this.timeOutID = setTimeout( this.proxy( "_refresh" ), 60000 );
		},

		"#refreshPackagingButton click": function( el, evt ) {
			this._refresh();
		},

		".delivery-notification-button click": function( el, evt ) {
			var deliveryLocation = el.attr("deliveryLocation");
			if(!confirm("Pozor, potvrzenim se odesle notifikace do odberneho mista \n\n!!! " + deliveryLocation + " !!!\n\n Prejete si pokracovat?"))
				return;
			var orderIds = el.attr("orderIds").split(",");
			var action = "notifyDelivery";
			Backoffice.Models.Order.executeAction( orderIds, action, this.proxy( "_handleNotificationResponse" ) );
		},

		_handleActionResponse: function( notFound ) {
			if ( notFound.length > 0 )
				alert( "Orders with ID-s " + notFound + " not found!" );
			
			this._refresh();
		},

		_handleNotificationResponse: function( notFound ) {
			if ( notFound.length > 0 ) {
				alert( "Orders with ID-s " + notFound + " not found!" );
			} else {
				alert("OK - notifikace byli uspesne odeslany.");
			}
			this._refresh();
		},

		_renderOrdersGroupedByDeliveryLocation: function( ordersGroupedByArticle ) {
			this.element.html( this.view( 'packagingList', ordersGroupedByArticle ) );
		}

	} );

} );

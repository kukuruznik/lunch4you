steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view' ).then( './views/list3.ejs', './views/ordersByDate.ejs', function( $ ) {

	/**
	 * @class Backoffice.Order.List
	 * @parent index
	 * @inherits jQuery.Controller Lists articles.
	 */
	$.Controller( 'Backoffice.Order.List3',

	/** @Static */
	{
	},

	/** @Prototype */
	{
		/**
		 * Recently checked items (checkboxes in the list)
		 */
		checkedItems : {},

		init: function() {
			steal.dev.log( "Order list3 controller initialized" );
			this.articleNames = [];
			this.ordersByArticleMap = {};
			this._refresh();
		},

		update: function() {
			this._refresh();
		},

		"#refreshList3 click": function( el, evt ) {
			this._refresh();
		},
		
		"input.orderItemSelectorCheckbox click": function( el, evt ) {
			//alert(el.attr("orderId"));
			var orderId = el.attr("orderId");
			var checked = el.is(':checked');
			this.checkedItems[orderId] = checked;
		},
		
		
		"#orderItemSelectorAllCheckbox click": function( el, evt ) {
			var checked = el.attr("checked");
			var orderSelectors = $(".orderItemSelectorCheckbox");
			orderSelectors.each(function( index ){
				if(checked){					
					$(this).attr("checked", true);
				}else{
					$(this).attr("checked", false);
				}
			});
		},
		
		"#ordersActionButton click": function( el, evt ) {
			var action = el.attr("action");
			var orderSelectors = $(".orderItemSelectorCheckbox:checked");
			if(orderSelectors.size() == 0 ){
				alert("No orders selected!");
				return;
			}
			if( !confirm("Are you sure you want to " + action.toUpperCase() + " these Orders?") ){
				return;
			}
			
			var orderIds = $( orderSelectors ).map( function( index, checkboxEl ) {
				return $(checkboxEl).attr("orderId");
			});
			
			orderIds = $.makeArray( orderIds );
			
			clearTimeout( this.timeOutID );
			Backoffice.Models.Order.executeAction( orderIds, action, this.proxy( "_handleActionResponse" ) );
		},

		
		_refresh: function() {
			Backoffice.Models.Order.getActiveOrdersByDate().done( this.proxy( "_renderOrdersByDate" ) );
			//			this.counter++;
//			if ( this.counter < 20 )
				this.timeOutID = setTimeout( this.proxy( "_refresh" ), 60000 );
		},

		_handleActionResponse: function( notFound ) {
			if ( notFound.length > 0 )
				alert( "Orders with ID-s " + notFound + " not found!" );
			
			this._refresh();
		},

		_renderOrdersByDate: function( ordersByDate ) {
			//console.log("_renderOrdersGroupedByArticle")
			this.element.html( this.view( 'list3', {ordersByDate : ordersByDate, checkedItems : this.checkedItems} ) );
		}

	} );

} );

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
		
		_getSelectedOrderIds: function(){
			var orderSelectors = $(".orderItemSelectorCheckbox:checked");			
			var orderIds = $( orderSelectors ).map( function( index, checkboxEl ) {
				return parseInt( $(checkboxEl).attr("orderId") );
			});
			
			return $.makeArray( orderIds );
			
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

		"#orderItemSelectorNotPrinted click": function( el, evt ) {
			var orderSelectors = $( ".orderItemSelectorCheckbox" );
			var orders = Backoffice.orders;

			orderSelectors.each( function( index ) {
				var orderId = $( this ).attr( "orderId" );

				for ( var i = 0; i < orders.length; i++ ) {
					var id = orders[ i ].id;
					
					if ( orderId == id ) {
						if ( orders[ i ].onlyItem.isLabelPrinted )
							$( this ).attr( "checked", false );
						else
							$( this ).attr( "checked", true );
					}
				}
			} );
		},

		"#ordersActionButton click": function( el, evt ) {
			var orderIds = this._getSelectedOrderIds();

			if(orderIds.length == 0 ){
				alert("No orders selected!");
				return;
			}

			var action = el.attr("action");
			if( !confirm("Are you sure you want to " + action.toUpperCase() + " these Orders?") ){
				return;
			}
						
			Backoffice.Models.Order.executeAction( orderIds, action, this.proxy( "_handleActionResponse" ) );
		},

		"#printLabelsButton click": function( el, evt ) {
			var orderIds = this._getSelectedOrderIds();

			if(orderIds.length == 0 ){
				alert("No orders selected!");
				return;
			}
						
			Backoffice.orderIdsToPrint = orderIds;
			
			var newWin = Backoffice.Utils.openWindow( "printing.html", "printing" );
			newWin.focus();
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
			// store the value in global Backoffice object
			Backoffice.orders = ordersByDate;
			//console.log("_renderOrdersGroupedByArticle")
			this.element.html( this.view( 'list3', {ordersByDate : ordersByDate, checkedItems : this.checkedItems} ) );
		}

	} );

} );

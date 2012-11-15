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
		init: function() {
			steal.dev.log( "Order list3 controller initialized" );
			this.articleNames = [];
			this.ordersByArticleMap = {};
			this._refresh();
		},

		update: function() {
			this._refresh();
		},

		"h5 click": function( el, evt ) {
			var elementId = el.attr( "id" );
			// parse out articleId from the element id
			var articleId = elementId.substring("article".length); 
			// find div containing customers
			var customersDiv = $("#customersForArticle" + articleId);
			customersDiv.toggle();			
		},

		"input[type=button] click": function( el, evt ) {
			clearTimeout( this.timeOutID );
			var infoRowElement = el.parents( "table" ).prev();
			var articleName = infoRowElement.attr( "id" );
			var orderGroup = this.ordersByArticleMap[ articleName ];
			var orderIds = $( orderGroup.items ).map( function( index, order ) {
				return order.id;
			});
			orderIds = $.makeArray( orderIds );
			delete this.ordersByArticleMap[ articleName ];
			this.articleNames = $( this.articleNames ).map( function( index, name ) {
				if ( name != articleName )
					return name;
			});
			Backoffice.Models.Order.close( orderIds, this.proxy( "_handleCloseResponse" ) );
		},

		_refresh: function() {
			Backoffice.Models.Order.getActiveOrdersByDate().done( this.proxy( "_renderOrdersByDate" ) );
			//			this.counter++;
//			if ( this.counter < 20 )
				this.timeOutID = setTimeout( this.proxy( "_refresh" ), 60000 );
		},

		_handleCloseResponse: function( notFound ) {
			if ( notFound.length > 0 )
				alert( "Orders with ID-s " + notFound + " not found!" );
			
			this._refresh();
		},

		_renderOrdersByDate: function( ordersByDate ) {
			//console.log("_renderOrdersGroupedByArticle")
			this.element.html( this.view( 'list3', ordersByDate ) );
		},

	} );

} );

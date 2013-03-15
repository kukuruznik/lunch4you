steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view' ).then( './views/list1.ejs', './views/articleWithOrders.ejs', function( $ ) {

	/**
	 * Cooking view - Orders grouped by Articles
	 * 
	 * @class Backoffice.Order.List
	 * @parent index
	 * @inherits jQuery.Controller Lists articles.
	 */
	$.Controller( 'Backoffice.Order.List1',

	/** @Static */
	{
	},

	/** @Prototype */
	{
		openGroups : {},
		
		init: function() {
			steal.dev.log( "Order list1 controller initialized" );
			this.articleNames = [];
			this.ordersByArticleMap = {};
//			this.counter = 0;
			this._refresh();
		},

		update: function() {
			this._refresh();
		},
		
		"#refreshList1 click": function( el, evt ) {
			this._refresh();
		},

		"h5 click": function( el, evt ) {
			var elementId = el.attr( "id" );
			// parse out articleId from the element id
			var articleId = elementId.substring("article".length);
			// find div containing customers
			var customersDiv = $("#customersForArticle" + articleId);
			customersDiv.toggle();

			var isVisible = customersDiv.is(':visible');
			this.openGroups["" + articleId] = isVisible ? true : false;
		},

		_refresh: function() {
			Backoffice.Models.Order.getActiveOrdersGroupedByArticle().done( this.proxy( "_renderOrdersGroupedByArticle" ) );			
//			this.counter++;
//			if ( this.counter < 20 )
				this.timeOutID = setTimeout( this.proxy( "_refresh" ), 60000 );
				//alert(this.timeOutID);
		},

		_handleCloseResponse: function( notFound ) {
			if ( notFound.length > 0 )
				alert( "Orders with ID-s " + notFound + " not found!" );
			
			this._refresh();
		},

		_renderOrdersGroupedByArticle: function( ordersGroupedByArticle ) {
			//steal.dev.log("_renderOrdersGroupedByArticle")
			this.element.html( this.view( 'list1', { ordersGroupedByArticle : ordersGroupedByArticle, openGroups : this.openGroups } ) );
		}

	} );

} );

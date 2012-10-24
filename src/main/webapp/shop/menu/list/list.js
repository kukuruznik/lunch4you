steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view' ).then( './views/list.ejs', /*'./views/item.ejs',*/ function( $ ) {

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

		"h3 click": function( el, evt ) {
			var articleName = el.attr( "id" );
			var orderGroup = this.articlesByCategoryMap[ articleName ];
			orderGroup.closed = !orderGroup.closed;

			el.parent().html( this.view( 'group', orderGroup ) );
		},

		"input[type=button] click": function( el, evt ) {
			clearTimeout( this.timeOutID );
			var infoRowElement = el.parents( "table" ).prev();
			var articleName = infoRowElement.attr( "id" );
			var orderGroup = this.articlesByCategoryMap[ articleName ];
			var orderIds = $( orderGroup.items ).map( function( index, order ) {
				return order.id;
			});
			orderIds = $.makeArray( orderIds );
			delete this.articlesByCategoryMap[ articleName ];
			this.articleNames = $( this.articleNames ).map( function( index, name ) {
				if ( name != articleName )
					return name;
			});
			Backoffice.Models.Order.close( orderIds, this.proxy( "_handleCloseResponse" ) );
		},

		_reloadMenu: function() {
			this.articlesByCategoryMap = {};
			Shop.Models.Category.findAll().done( this.proxy( "_onCategoriesLoaded" ) );
		},

		_groupArticlesByCategory: function( articles ) {
			var articlesByCategoryMap = this.articlesByCategoryMap;

			// grouping together the articles by category
			$( articles ).each( function( i, article ) {
				var categoryId = article.category.id;

				var articleGroup = articlesByCategoryMap[ categoryId ];

				if ( articleGroup )
					articleGroup.items.push( article );
				else {
					articleGroup = {
						category: article.category,
						items: [ article ]
					};
					articlesByCategoryMap[ categoryId ] = articleGroup;
				}
			});

			var articleGroups = [];

			// building the list of groups ordered in category order (as received from the server)
			var categories = this.categories;
			$( categories ).each( function( i, category ) {
				articleGroups.push( articlesByCategoryMap[ category.id ] );
			});

			// render the resulting list of article groups
			this._render( articleGroups );
		},

		_onCategoriesLoaded: function( categories ) {
			this.categories = categories;
			Shop.Models.Article.findAll().done( this.proxy( "_groupArticlesByCategory" ) );
		},

		_handleCloseResponse: function( notFound ) {
			if ( notFound.length > 0 )
				alert( "Orders with ID-s " + notFound + " not found!" );
			
			this._refresh();
		},

		_render: function( articleGroups ) {
			this.element.find( "#detail" ).html( this.view( 'list', articleGroups ) );
		}
	} );

} );

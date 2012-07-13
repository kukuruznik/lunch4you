steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view' ).then( './views/list.ejs', './views/item.ejs', function( $ ) {

	/**
	 * @class App.Order.List
	 * @parent index
	 * @inherits jQuery.Controller Lists articles.
	 */
	$.Controller( 'Backoffice.Order.List',

	/** @Static */
	{
	},

	/** @Prototype */
	{
		init: function() {
			steal.dev.log( "Order list controller initialized" );
			this.articleNames = [];
			this.ordersByArticleMap = {};
//			this.counter = 0;
			this._refresh();
		},

		"h3 click": function( el, evt ) {
			var articleName = el.attr( "id" );
			var orderGroup = this.ordersByArticleMap[ articleName ];
			orderGroup.closed = !orderGroup.closed;

			el.parent().html( this.view( 'group', orderGroup ) );
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
			Backoffice.Models.Order.findActive().done( this.proxy( "_groupOrdersByArticle" ) );
//			this.counter++;
//			if ( this.counter < 20 )
				this.timeOutID = setTimeout( this.proxy( "_refresh" ), 1000 );
		},

		_groupOrdersByArticle: function( orders ) {
			var ordersByArticleMap = this.ordersByArticleMap;
			var articleNames = this.articleNames;

			// clear the remembered order lists
			for ( attrName in ordersByArticleMap ) {
				var orderGroup = ordersByArticleMap[ attrName ];
				orderGroup.items = [];
			}

			// grouping together the orders by article
			$( orders ).each( function( i, order ) {
				var article = order.onlyItem.article;

				var orderGroup = ordersByArticleMap[ article.name ];

				if ( orderGroup )
					orderGroup.items.push( order );
				else {
					articleNames.push( article.name );
					orderGroup = {
						article: article,
						closed: true,
						items: [ order ]
					};
					ordersByArticleMap[ article.name ] = orderGroup;
				}
			});

			// building a list of orderGroup-s sorted by article name
			articleNames.sort();

			var orderGroups = [];
			$( articleNames ).each( function( i, name ) {
				orderGroups.push( ordersByArticleMap[ name ] );
			});

			// render the resulting list of order groups
			this._render( orderGroups );
		},

		_handleCloseResponse: function( notFound ) {
			if ( notFound.length > 0 )
				alert( "Orders with ID-s " + notFound + " not found!" );
			
			this._refresh();
		},

		_render: function( orderGroups ) {
			this.element.html( this.view( 'list', orderGroups ) );
		}
	} );

} );

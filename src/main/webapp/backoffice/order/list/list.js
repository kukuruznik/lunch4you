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
			this._refresh();
		},

		"h3 click": function( el, evt ) {
			var signElement = el.find( ".switch" );
			var sign = signElement.html();

			if ( sign == "+" ) {
				signElement.html( "-" );
				el.next().show();
			} else {
				signElement.html( "+" );
				el.next().hide();
			}
		},

		"input[type=button] click": function( el, evt ) {
			alert( "Not implemented!" );
		},

		_refresh: function() {
			Backoffice.Models.Order.findActive().done( this.proxy( "_groupOrdersByArticle" ) );
//			setTimeout( this.proxy( "_refresh" ), 1000 );
		},

		_groupOrdersByArticle: function( orders ) {
			var ordersByArticleMap = {};
			var articleNames = [];

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

		_render: function( orderGroups ) {
			this.element.html( this.view( 'list', orderGroups ) );
		}
	} );

} );

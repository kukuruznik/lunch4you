steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view' ).then( './views/list.ejs', './views/item.ejs', function( $ ) {

	/**
	 * @class App.Article.List
	 * @parent index
	 * @inherits jQuery.Controller Lists articles.
	 */
	$.Controller( 'App.Article.List',

	/** @Static */
	{
	},

	/** @Prototype */
	{
		init: function() {
			steal.dev.log( "Article list controller initialized" );
			this._render();
		},

		_render: function() {
			this.element.html( this.view( 'list', App.Models.Article.findAll() ) );
		}
	} );

} );

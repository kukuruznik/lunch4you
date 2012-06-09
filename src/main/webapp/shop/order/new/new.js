steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view' ).then( './views/page.ejs', function( $ ) {

	/**
	 * @class Shop.Order.New
	 * @parent index
	 * @inherits jQuery.Controller Manages ordering (display and submission).
	 */
	$.Controller( 'Shop.Order.New',

	/** @Static */
	{
	},

	/** @Prototype */
	{
		init: function() {
			steal.dev.log( "Order creation controller initialized" );
			this._render();
		},

		_render: function() {
			this.element.html( this.view( 'page', Shop.Models.Article.findOne( { id: Shop.params.meal } ) ) );
		}
	} );

} );

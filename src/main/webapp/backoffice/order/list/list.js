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
			this._render();
		},

		_render: function() {
			this.element.html( this.view( 'list', Backoffice.Models.Order.findActive() ) );
		}
	} );

} );

steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view' ).then( 
	
	"./views/home.ejs",

	function( $ ) {

	/**
	 * @class Shop.Home
	 * @parent index
	 * @inherits jQuery.Controller Manages referral (display and submission).
	 */
	$.Controller( 'Shop.Home',

	/** @Static */
	{
	},

	/** @Prototype */
	{
		init: function() {
			steal.dev.log( "Home creation controller initialized" );
			this._reloadData();
		},

		update: function() {
			this._reloadData();
		},

		_reloadData: function() {
			this._render();
		},

		_render: function() {
			this.element.html( this.view( 'home', this ) );
		}
	} );

} );

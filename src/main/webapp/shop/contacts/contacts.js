steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view' ).then( 

	"./views/contacts.ejs",

	function( $ ) {

	/**
	 * @class Shop.Ordering
	 * @parent index
	 * @inherits jQuery.Controller Manages referral (display and submission).
	 */
	$.Controller( 'Shop.Ordering',

	/** @Static */
	{
	},

	/** @Prototype */
	{
		init: function() {
			steal.dev.log( "Referral creation controller initialized" );
			this._reloadData();
		},

		update: function() {
			this._reloadData();
		},

		_reloadData: function() {
			this._render();
		},

		_render: function() {
			this.element.html( this.view( 'ordering', this ) );
		}
	} );

} );

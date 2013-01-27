steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view' ).then(
		
	"./views/ordering.ejs",		
		
	function( $ ) {

	/**
	 * @class Shop.Contacts
	 * @parent index
	 * @inherits jQuery.Controller Manages referral (display and submission).
	 */
	$.Controller( 'Shop.Contacts',

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
			this.element.html( this.view( 'contacts', this ) );
		}
	} );

} );

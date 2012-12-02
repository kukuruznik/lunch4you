steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view', "common/register_customer" ).then( function( $ ) {

	/**
	 * @class Shop.Profile.Edit
	 * @parent index
	 * @inherits jQuery.Controller Handles profile editing.
	 */
	$.Controller( 'Shop.Profile.Edit',

	/** @Static */
	{
	},

	/** @Prototype */
	{
		init: function() {
			steal.dev.log( "Profile edit controller initialized" );
			this._reloadData();
		},

		update: function() {
			this._reloadData();
		},

		_reloadData: function() {
			Shop.Models.DeliveryLocation.findAll( this.proxy( "_onDataLoaded" ) );
		},

		_onDataLoaded: function( deliveryLocations ) {
			this.deliveryLocations = deliveryLocations;
			this.customer = Shop.customer; // can be null!
			this.deliveryLocation = this.customer ? this.customer.defaultDeliveryLocation : null;

			this._render();
		},

		_render: function() {
			this.element.html( this.view( "edit" ) );
			this.element.find( "#profile-edit" ).common_register_customer( this );
		}
	});
});

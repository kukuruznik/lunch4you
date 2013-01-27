steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view', "common/register_customer" ).then( 
		
	"./views/edit.ejs",
	"./views/updateConfirmation.ejs",
				
	function( $ ) {

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
			//TODO is there a different way how to access this object? What does "this" refer to?
			this.token = Shop.Main.getToken();
			this._reloadData();
		},

		update: function() {
			this._reloadData();
		},

		_reloadData: function() {
			this._onDataLoaded();
		},

		_onDataLoaded: function( ) {
			this.deliveryLocations = Shop.deliveryLocations;
						
			this.customer = Shop.customer; // can be null!
			this.deliveryLocation = this.customer ? this.customer.defaultDeliveryLocation : null;

			this._render();
		},

		"{window} updateProfile" : function( el, evt, formParams ) {

			if ( ! this._validateProfileForm() )
				return;
			
			// create an empty customer object
			var cust = new function(){};
			cust.id = this.customer.id;
			cust.firstName = $.trim( $( "#reg_firstName" ).attr( "value" ));
			cust.lastName = $.trim( $( "#reg_lastName" ).attr( "value" ));
			cust.email = $.trim( $( "#reg_email" ).attr( "value" ));
			cust.isSubscribedMenuWeekly = $( "#reg_subscribeMenuWeekly" ).attr( "checked" ) == "checked";
			cust.isSubscribedNews = $( "#reg_subscribeNews" ).attr( "checked" ) == "checked";
			var ddlId = $( "#reg_ddl" ).val();

			var self = this;

			Shop.Models.Customer.updateProfile( cust, ddlId , function( ) {
				self._renderConfirmation();
				Shop.Main.prototype._reloadCustomer();
			} );
		},

		_validateProfileForm : function() {
			var email = $( "#reg_email" ).attr( "value" );
			
			if ($.trim(email) == "") {
				alert( $.EJS.Helpers.prototype.msg( "customer.registration.validation.emptyEmailAddress" ) );
				return false;
			}

			if ( !Shop.Utils.isEmailAddressValid( email ) ) {
				alert( $.EJS.Helpers.prototype.msg( "customer.registration.validation.invalidEmailFormat" ) );
				return false;
			}
			return true;
		},

		_renderConfirmation: function() {
			this.element.html( this.view( "updateConfirmation", this ) );
		},

		_render: function() {
			this.element.html( this.view( "edit" ) );
			this.element.find( "#profile-edit" ).common_register_customer( this );
		}
	});
});

steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view', "common/register_customer" ).then( function( $ ) {

	/**
	 * @class Shop.Referral.New
	 * @parent index
	 * @inherits jQuery.Controller Manages referral (display and submission).
	 */
	$.Controller( 'Shop.Referral.New',

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

		"#submitButton click": function( el, evt ) {
			this._submitReferral();
		},

		_reloadData: function() {
			this.customer = Shop.customer;
			this.deliveryLocation = this.customer ? this.customer.defaultDeliveryLocation : null;

			this._render();
		},

		_submitReferral: function() {
			
			if ( ! this._validateReferralForm() )
				return;
			this._enableReferralSubmit( false );
			var self = this;

			var deliveryLocationId = this.customer.defaultDeliveryLocation.id;
			var recipientEmail = $.trim( $( "#recipientEmail" ).attr( "value" ));
			var referralMessage = $( "#referralMessage" ).attr( "value" );

			Shop.Models.Customer.createReferral( this.customer, deliveryLocationId, recipientEmail, referralMessage, function( referral ) {
				alert( $.EJS.Helpers.prototype.msg( "referral.detail.action.referralMsg" ) );
				self._enableReferralSubmit( true );
			} );
		},

		_validateReferralForm : function() {
			var email = $( "#recipientEmail" ).attr( "value" );
			
			if ($.trim(email) == "") {				
				alert( $.EJS.Helpers.prototype.msg( "referral.detail.validation.emptyEmailAddress" ) );
				return false;
			}

			if ( !Shop.Utils.isEmailAddressValid( email ) ) {
				alert( $.EJS.Helpers.prototype.msg( "referral.detail.validation.invalidEmailFormat" ) );
				return false;
			}
			return true;
		},

		_enableReferralSubmit: function( enable ) {
			var button = $( "#submitReferralButton" );
			Shop.Utils.enableInput( button, enable );
		},

		_render: function() {
			this.element.html( this.view( 'referralDetail', this ) );
		}
	} );

} );

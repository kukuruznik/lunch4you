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

		"#submitReferralButton click": function( el, evt ) {
			this._submitReferral();
		},

		"#changeCustomerButton click": function( el, evt ) {
			this._showCustomerForm();
		},

		"#customerForm close": function( el, evt ) {
			this._hideCustomerForm();
		},
		
		"#customerForm register": function( el, evt, customerData ) {
			steal.dev.log( "registering customer ", customerData );

			var customerDfr = Shop.Models.Customer.create( customerData );
			customerDfr.done( this.proxy( "_onCustomerLoaded" ) );
		},

		"#deliveryLocationsSelect change": function( el, evt ) {
			// TODO - tu je problem. Raz sa do tejto premennej odklada DeliveryLocatuion object, inokedy zas ID
			this.deliveryLocation = el.val();
		},

		_reloadData: function() {
			var customerDfr = Shop.Models.Customer.findByToken( Shop.params.token );
			var deliveryLocationsDfr = Shop.Models.DeliveryLocation.findAll();

			$.when( customerDfr, deliveryLocationsDfr ).done( this.proxy( "_onDataLoaded" ) );
		},

		_onDataLoaded: function( customerResponse, deliveryLocationsResponse ) {
			this.deliveryLocations = deliveryLocationsResponse[ 0 ];
			this._onCustomerLoaded( customerResponse[ 0 ] );
		},

		_onCustomerLoaded: function( customer ) {
			this.customer = customer;
			this.deliveryLocation = customer ? customer.defaultDeliveryLocation : null;

			this._render();
		},

		_submitReferral: function() {
			
			if(! this._validateReferralForm())
				return;
			this._enableReferralSubmit( false );
			var self = this;
			//var deliveryLocationId = $( "#deliveryLocationsSelect" ).val();
			var deliveryLocationId = this.customer.defaultDeliveryLocation.id;
			var recipientEmail = $.trim($( "#recipientEmail" ).attr("value"));
			var referralMessage = $( "#referralMessage" ).attr("value");

			Shop.Models.Customer.createReferral( this.customer, deliveryLocationId, recipientEmail, referralMessage, function( referral ) {
				alert($.EJS.Helpers.prototype.msg("referral.detail.action.referralMsg"));
				self._enableReferralSubmit( true );
			} );
		},

		_validateReferralForm : function() {
			var email = $( "#recipientEmail" ).attr("value");
			
			if ($.trim(email) == ""){				
				alert($.EJS.Helpers.prototype.msg("referral.detail.validation.emptyEmailAddress"));
				return false;
			}

			var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			if (!regex.test(email)){
				alert($.EJS.Helpers.prototype.msg("referral.detail.validation.invalidEmailFormat"));
				return false;
			}
			return true;
		},

		/*
		 * TODO this may want some refactoring to some utility method for enabling / disabling buttons. Maybe jQuery already has it
		 */
		_enableReferralSubmit: function( yes ) {
			var button = $( "#submitReferralButton" );
			if ( yes )
				button.removeAttr( "disabled" );
			else
				button.attr( "disabled", "disabled" );
		},


		_render: function() {
			var detailElem = this.element.find( "#detail" );

			detailElem.html( this.view( 'referralDetail', this ) );
		}
	} );

} );

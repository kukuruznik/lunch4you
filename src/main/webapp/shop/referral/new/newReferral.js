steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view' ).then( 

	"./views/referralForm.ejs",


	function( $ ) {

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

		"#referralForm #submitButton click": function( el, evt ) {
			this._submitReferral();
		},

		_reloadData: function() {
			this.customer = Shop.customer;

			this._render();
		},

		_submitReferral: function() {
			
			if ( ! this._validateReferralForm() )
				return;
			this._enableReferralSubmit( false );
			var self = this;

			var referralMessage = $( "#referralMessage" ).attr( "value" );

			Shop.Models.Customer.createReferral( this.customer, this.parsedEmails, referralMessage, function( referral ) {
				alert( $.EJS.Helpers.prototype.msg( "referral.detail.action.referralMsg" ) );
				self._enableReferralSubmit( true );
			} );
		},
		
		/**
		 * Parses email(s) text and converts it to an array of potential email addresses and saves it within Controller namespace. 
		 * Method does not
		 * validate individual addresses at this point.
		 */
		_parseEmails : function(){
			var emailStr = $( "#recipientEmail" ).attr( "value" );
						
			// split array by a set of separators
			var arr = emailStr.split(/[,;\t\n\r]/);
			// trim each element in the array;
			arr = $.map( arr, function(el){ return el.trim(); });
			// filter out any blank elements
			arr = $.grep( arr, function(el){ return el.trim(); });

			// store parsed emails into Controller namespace.
			this.parsedEmails = arr;
			
		},

		_validateReferralForm : function() {
			this._parseEmails();

			var arr = this.parsedEmails;

			if (arr.length == 0) {				
				alert( $.EJS.Helpers.prototype.msg( "referral.detail.validation.emptyEmailAddress" ) );
				return false;
			}

			for(var i=0; i<arr.length; i++){
				var email = arr[i];
				if ( !Shop.Utils.isEmailAddressValid( email ) ) {
					alert( $.EJS.Helpers.prototype.msg( "referral.detail.validation.invalidEmailFormat" ) + " " + email);
					return false;
				}
			}
			return true;
		},

		_enableReferralSubmit: function( enable ) {
			var button = $( "#submitReferralButton" );
			Shop.Utils.enableInput( button, enable );
		},

		_render: function() {
			this.element.html( this.view( 'referralForm', this ) );
		}
	} );

} );

steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view', "common/register_customer" ).then( 
		
	"./views/registerForm.ejs",
				
	function( $ ) {

	/**
	 * @parent index
	 * @inherits jQuery.Controller Handles profile editing.
	 */
	$.Controller( 'Shop.Auth.Register',

	/** @Static */
	{
	},

	/** @Prototype */
	{
		init: function() {
			this._render();
		},

		update: function() {
			this._render();
		},

		"#registerForm #submitButton click": function( el, evt ) {
			this._submitRegistration();
		},

		_submitRegistration : function( ) {

			if ( ! this._validateInputForm() )
				return;
			
			// Now check, whether customer with this email already exists
			var email = $.trim( $( "#reg_email" ).attr( "value" ));
			Shop.Models.Customer.findByEmail( email , this.proxy( "_customerCheckedHandler"));
		},

		_customerCheckedHandler : function( customer ) {
			
			if( customer ){
				// customer already exists
				// TODO handle this situation
				alert("Customer with this email address already exists");
				return;
			}
			
			// Create new Customer
			var cust = new function(){};
			cust.email = $.trim( $( "#reg_email" ).attr( "value" ));
			cust.firstName = $.trim( $( "#reg_firstName" ).attr( "value" ));
			cust.lastName = $.trim( $( "#reg_lastName" ).attr( "value" ));

			Shop.Models.Customer.register( cust , this.proxy( "_customerCreatedHandler"));
		},

		_customerCreatedHandler : function( customer ) {
			// TODO message taht user was created and verification code was sent
			alert("customer created ");
			window.location.href="#view=signin&phase=verifyPIN&email=" + customer.email;
		},

		_validateInputForm : function() {
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
			this.element.html( this.view( "registerConfirmation", this ) );
		},

		_render: function() {
			this.element.html( this.view( "registerForm" ) );
		}
	});
});

steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view' ).then( 
		
	"./views/signinForm.ejs",
	"./views/pinForm.ejs",
	"./views/signedIn.ejs",
	"./views/success.ejs",
				
	function( $ ) {

	/**
	 * @parent index
	 * @inherits jQuery.Controller Handles profile editing.
	 */
	$.Controller( 'Shop.Auth.Signin',

	/** @Static */
	{
		email : null,
	
		getSigninEmail: function( ) {
			return this.signinEmail;
		},

		setSigninEmail: function( email ) {
			this.signinEmail = email;
		}
	},

	/** @Prototype */
	{
		init: function() {
			this._render();
		},

		update: function() {
			this._render();
		},
		
		"#signinForm #submitButton click": function( el, evt ) {
			this._submitEmail();
		},

		"#pinForm #submitButton click": function( el, evt ) {
			this._submitPin();
		},
		
		/**
		 * Phase 1 - requesting Sign-in / verification email
		 * Handler when user submits his email address to obtain a verification / sign-in email
		 */
		_submitEmail : function( ) {

			if ( ! this._validateInputForm() )
				return;
			
			// Now check, whether customer with this email already exists
			var email = $.trim( $( "#reg_email" ).attr( "value" ));
			this.Class.setSigninEmail(email);
			Shop.Models.Customer.sendSigninEmail( email , this.proxy( "_emailSentHandler"));
		},

		_emailSentHandler : function( customer ) {
			if(!customer){
				alert($.EJS.Helpers.prototype.msg( "auth.signin.userNotExist" ));
				return;
			}
			
			this._renderPinForm();
			alert($.EJS.Helpers.prototype.msg( "auth.signin.emailSent" ));
		},

		/**
		 * Phase 2 - PIN verification
		 * Handler when user submits his email address to obtain a verification / sign-in email
		 */
		_submitPin : function( ) {
			var pin = $.trim( $( "#reg_pin" ).attr( "value" ));
			var email = this.Class.getSigninEmail();
			Shop.Models.Customer.findByPin( email, pin , this.proxy( "_pinSentHandler"));
		},

		_pinSentHandler : function( customer ) {
			if(!customer){
				// TODO handle this situation with proper text and instructions
				alert($.EJS.Helpers.prototype.msg( "customer.registration.validation.invalidPIN" ));
				
			} else {
				Shop.Main.setToken( customer.token );
				this.Class.setSigninEmail(null);
				this._renderSuccess();
				Shop.Main.reloadCustomer();
				
				// TODO re-render header
			}
		},


		_validateInputForm : function() {
			var email = $( "#reg_email" ).attr( "value" );
			
			// TODO move following dictionary entries to some "common.validation" namespace
			
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

		_render: function() {
			if(Shop.Main.getCustomer()){
				this._renderSignedInForm();
				return;
			}
			
			var phase = Shop.params.phase;
			if(phase == "verifyPIN") {
				this.Class.setSigninEmail(Shop.params.email);
				this._renderPinForm();
			} else {
				this._renderRequestForm();
			}
		},

		_renderRequestForm: function() {
			this.element.html( this.view( "signinForm" ) );
		},

		_renderSuccess: function() {
			this.element.html( this.view( "success" ) );
		},

		_renderSignedInForm: function() {
			this.element.html( this.view( "signedIn" ) );
		},

		_renderPinForm: function() {
			this.element.html( this.view( "pinForm" ) );
		}
		
	});
});

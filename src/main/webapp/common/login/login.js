steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view', 'jquery/dom/form_params' ).then( './views/form.ejs', function( $ ) {

	/**
	 * @class Common.Login
	 * @parent index
	 * @inherits jQuery.Controller Implements login functionality.
	 */
	$.Controller( 'Common.Login',

	/** @Static */
	{
	},

	/** @Prototype */
	{
		init: function() {
			steal.dev.log( "Login controller initialized" );
			this._render();
		},

		update: function() {
			this.element.show();
			this._render();
		},

		"form submit": function( el, evt ) {
			var credentials = el.formParams();
			this._login( credentials );
			evt.preventDefault();
			return false;
		},

		_login: function( credentials ) {
			var loggedInHandler = this.proxy( "_loggedIn" );
			$.ajax( {
				url: "j_spring_security_check?spring-security-redirect=/backoffice/loggedInUser.json",
				data: credentials,
				type: "post",
				accept: "json",
				success: loggedInHandler,
				failure: Backoffice.errorHandler
			});
		},

		_loggedIn: function( user ) {
			this.element.hide();
			$( window ).trigger( "loggedIn", user );
		},

		_render: function() {
			this.element.html( this.view( "form", this ) );
		}
	});
});

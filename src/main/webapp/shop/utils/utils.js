steal( "jquery/class" ).then( function( $ ) {

	/**
	 * @class Shop.Utils
	 * @parent index
	 * @inherits jQuery.Class Utility class.
	 */
	$.Class( 'Shop.Utils',

	/** @Static */
	{
		getLocalizedMessage: function( key ) {
			$.EJS.Helpers.prototype.msg( key );
		},

		isEmailAddressValid: function( email ) {
			var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

			return regex.test( email );
		},

		isCookieEnabled: function () {
			var test = "tEsT";
			this.saveToCookie( test, test );
			var enabled = this.loadFromCookie( test ) == test;
			this.saveToCookie( test, null );
			return enabled;
		},

		loadFromCookie: function( key ) {
			var value = $.cookie( key );
			//steal.dev.log( "load from cookie: ", key, " = ", value );
			return value;
		},

		saveToCookie: function( key, value ) {
			//steal.dev.log( "save into cookie: ", key, " = ", value );
			$.cookie( key, value, {expires : 90} );
		},

		enableInput: function( input, enable ) {
			if ( enable )
				input.removeAttr( "disabled" );
			else
				input.attr( "disabled", "disabled" );
		}
	},

	/** @Prototype */
	{
	});
});
	
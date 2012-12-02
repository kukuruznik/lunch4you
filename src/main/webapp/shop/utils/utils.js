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
	
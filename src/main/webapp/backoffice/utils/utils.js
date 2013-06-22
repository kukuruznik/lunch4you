steal( "jquery/class" ).then( function( $ ) {

	/**
	 * @class Backoffice.Utils
	 * @parent index
	 * @inherits jQuery.Class Utility class.
	 */
	$.Class( 'Backoffice.Utils',

	/** @Static */
	{
		openWindow: function( url, name, width, height ) {
			width = width || 800;
			height = height || 600;
			var optionsText = "width=" + width + ",height=" + height + ", menubar=yes, toolbar=yes, scrollbars=yes";
			return window.open( url, name, optionsText );
		}
	},

	/** @Prototype */
	{
	});
});

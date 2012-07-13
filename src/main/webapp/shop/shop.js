steal(
	'./shop.css', 		// application CSS file
	'//jquery/lang/string/deparam/deparam.js',
	'./models/models.js',		// steals all your models
	'./order/new/new.js'
).then( function() {	// configure your application
	steal.dev.log( "Lunch4you shop started." );

	var hashText = window.location.hash;
	if ( hashText.charAt( 0 ) == "#" )
		hashText = hashText.substring( 1 );

	Shop.params = $.String.deparam( hashText );

	Shop.errorHandler = function( jqXHR, textStatus, error ) {
		alert( "An error occured!\nStatus: " + textStatus + "\nDetails: " + error );
	};

	if ( Shop.params.meal && Shop.params.token )
		$( '#content' ).shop_order_new();
	else
		alert( "Invalid URL!" );
});

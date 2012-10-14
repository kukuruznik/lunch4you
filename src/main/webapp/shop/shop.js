steal(
	'./shop.css', 		// application CSS file
	'//jquery/lang/string/deparam/deparam.js',
	'//jquery/event/bbq/bbq.js',
	'./models/models.js',		// steals all your models
	'./order/new/new.js',
	'./menu/list/list.js'
).then( function() {	// configure your application
	steal.dev.log( "Lunch4you shop started." );

	var parseHash = function() {
		var hashText = window.location.hash;
		if ( hashText.charAt( 0 ) == "#" )
			hashText = hashText.substring( 1 );

		return $.String.deparam( hashText );
	};

	Shop.errorHandler = function( jqXHR, textStatus, error ) {
		alert( "An error occured!\nStatus: " + textStatus + "\nDetails: " + error );
	};

	$( window ).bind( "hashchange", function() {
		Shop.params = parseHash();
		console.log( "Hash changed: ", Shop.params );

		// URL validation and view selection
		switch ( Shop.params.view ) {
		case "menu":
			if ( Shop.params.token )
				$( '#content' ).shop_menu_list();
			else
				alert( "Invalid URL! Missing user." );
			break;
		case "article":
			if ( Shop.params.meal && Shop.params.token )
				$( '#content' ).shop_order_new();
			else
				alert( "Invalid URL! Missing user and/or article." );
			break;
		default:
			alert( "Invalid URL! Unknown or missing view." );
		}
	});

	$( function() {
		$( window ).trigger( "hashchange" );
	});
});

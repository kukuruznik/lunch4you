steal(
	'./shop.css', 		// application CSS file
	'./models/models.js',		// steals all your models
	'./order/new/new.js'
).then( function() {	// configure your application
	steal.dev.log( "Lunch4you shop started." );

	(function($) {
		var re = /([^&=]+)=?([^&]*)/g;
		var decodeRE = /\+/g; // Regex for replacing addition symbol with a space
		var decode = function (str) { return decodeURIComponent( str.replace(decodeRE, " ") ); };
		$.parseParams = function(query) {
		    var params = {}, e;
		    while ( e = re.exec(query) ) params[ decode(e[1]) ] = decode( e[2] );
		    return params;
		};
	})(jQuery);

	var hashText = window.location.hash;
	if ( hashText.charAt( 0 ) == "#" )
		hashText = hashText.substring( 1 );

	Shop.params = $.parseParams( hashText );

	Shop.errorHandler = function( jqXHR, textStatus, error ) {
		alert( "An error occured!\nStatus: " + textStatus + "\nDetails: " + error );
	};

	if ( Shop.params.meal && Shop.params.token )
		$( '#article' ).shop_order_new();
	else
		alert( "Invalid URL!" );
});

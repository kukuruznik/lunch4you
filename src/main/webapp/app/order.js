steal(
	'./order.css', 		// application CSS file
	'app/models/order.js',		// steals all your models
	'app/order/new'
).then( function() {	// configure your application
	steal.dev.log( "Lunch4you order started." );

	App.errorHandler = function( jqXHR, textStatus, error ) {
		alert( "An error occured!\nStatus: " + textStatus + "\nDetails: " + error );
	};

	$( '#article' ).app_order_new();
});

steal(
	'./dashboard.css', 		// application CSS file
	'app/models',		// steals all your models
	'app/order/list'
).then( function() {	// configure your application
	steal.dev.log( "Lunch4you dashboard started." );

	App.errorHandler = function( jqXHR, textStatus, error ) {
		alert( "An error occured!\nStatus: " + textStatus + "\nDetails: " + error );
	};

	$( '#meals' ).app_order_list();
});

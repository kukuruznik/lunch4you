steal(
	'./backoffice.css', 		// application CSS file
	'./models/models.js',		// steals all your models
	'./order/list/list.js'
).then( function() {	// configure your application
	steal.dev.log( "Lunch4you back-office started." );

	Backoffice.errorHandler = function( jqXHR, textStatus, error ) {
		alert( "An error occured!\nStatus: " + textStatus + "\nDetails: " + error );
	};

	$( '#meals' ).backoffice_order_list();
});

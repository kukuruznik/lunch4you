steal(
	'./backoffice.css', 		// application CSS file
	'./models/models.js',		// steals all your models
	'./order/list1/list1.js',
	'./order/list2/list2.js',
	'//common/login/login.js'
).then( function() {	// configure your application
	steal.dev.log( "Lunch4you back-office started." );

	Backoffice.errorHandler = function( jqXHR, textStatus, error ) {
		console.log( "response error ", jqXHR.status );
		if ( jqXHR.status == 404 ) {
			$( '#overlay' ).common_login();
		} else {
			alert( "An error occured!\nStatus: " + textStatus + "\nDetails: " + error );
		}
	};

	$( window ).bind( "loggedIn", function( evt, data ) {
		steal.dev.log( "logged in as ", data );
		$( '#meals1' ).backoffice_order_list1();
		$( '#meals2' ).backoffice_order_list2();
	});

	$( '#overlay' ).common_login();
	$( '#meals1' ).backoffice_order_list1();
	$( '#meals2' ).backoffice_order_list2();
});

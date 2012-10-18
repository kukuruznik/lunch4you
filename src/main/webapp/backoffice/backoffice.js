steal(
	'./backoffice.css', 		// application CSS file
	'./models/models.js',		// steals all your models
	'./order/list/list.js'
).then( function() {	// configure your application
	steal.dev.log( "Lunch4you back-office started." );

	Backoffice.errorHandler = function( jqXHR, textStatus, error ) {
		if ( jqXHR.status = 404 ) {
			$.ajax( {
				url: "j_spring_security_check?spring-security-redirect=/backoffice/loggedInUser.json",
				data: { j_username: "kuchar", j_password: "szakacs" },
				type: "post",
				accept: "json",
				success: function( user ) {
					console.log( "Logged in as ", user );
				},
				failure: Backoffice.errorHandler
			});
		} else {
			alert( "An error occured!\nStatus: " + textStatus + "\nDetails: " + error );
		}
	};

	$( '#meals' ).backoffice_order_list();
});

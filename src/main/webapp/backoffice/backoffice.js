steal(
	'./backoffice.css', 		// application CSS file
	'common/form.css', 			// application CSS file
	'./models/models.js',		// steals all your models
	'./order/list1/list1.js',
	'./order/list2/list2.js',
	'./order/list3/list3.js',
	'./menu/edit/menuEdit.js',
	'//common/login/login.js'
).then( function() {	// configure your application
	steal.dev.log( "Lunch4you back-office started." );

	Backoffice.errorHandler = function( jqXHR, textStatus, error ) {
		steal.dev.log( "response error ", jqXHR.status );
		if ( jqXHR.status == 404 ) {
			$( '#overlay' ).common_login();
		} else {
			alert( "An error occured!\nStatus: " + textStatus + "\nDetails: " + error );
		}
	};
	
	window.onTabClick = function(index){
		var tabContentEl = $("#tabContent" + index);
		var otherContents = tabContentEl.siblings();
		otherContents.hide();
		tabContentEl.show();
	};

	$( window ).bind( "loggedIn", function( evt, data ) {
		steal.dev.log( "logged in as ", data );
		$( '#meals1' ).backoffice_order_list1();
		$( '#meals2' ).backoffice_order_list2();
		$( '#meals3' ).backoffice_order_list3();
		$( '#menuEdit' ).backoffice_menu_edit();
	});

	$( '#overlay' ).common_login();
	$( '#meals1' ).backoffice_order_list1();
	$( '#meals2' ).backoffice_order_list2();
	$( '#meals3' ).backoffice_order_list3();
	$( '#menuEdit' ).backoffice_menu_edit();
});

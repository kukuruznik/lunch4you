steal(
	'./backoffice.css', 		// application CSS file
	'common/form.css', 			// application CSS file
	'./models/models.js',		// steals all your models
	'./order/list1/list1.js',
	'./order/list2/list2.js',
	'./order/list3/list3.js',
	'./menu/edit/menuEdit.js',
	'./menu/restaurant/menuRestaurant.js',
	'//common/login/login.js'
).then( 
	"backoffice/main",				// main controller...

	function() {
		// setting up the main controller as the first thing
		$( "#content" ).backoffice_main();
	}
);

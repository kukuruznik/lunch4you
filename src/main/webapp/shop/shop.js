steal(
	'./shop.css', 			// application CSS file
	'./models/models.js'	// steals all your models
).then(
	"shop/main",				// main controller...

	function() {

		// setting up the main controller as the first thing
		$( window ).shop_main();
	}
);

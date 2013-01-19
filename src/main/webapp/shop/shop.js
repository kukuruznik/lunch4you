steal(
	'shop/shop.css', 			// application CSS file
	'shop/models/models.js'	// steals all your models
).then(
	"shop/main",				// main controller...

	function() {

		// setting up the main controller as the first thing
		$( "#content" ).shop_main();
	}
);

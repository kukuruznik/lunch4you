steal(
	'./app.css', 		// application CSS file
	'app/models',		// steals all your models
//	'app/meal/create', 
	'app/article/list'
).then( function() {				// configure your application
	console.log( "No nazdar!" );
	$( '#meals' ).app_article_list();
//	$( '#create' ).app_meal_create();
});

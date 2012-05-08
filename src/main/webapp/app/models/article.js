steal('jquery/model', 'jquery/lang/json', function(){

/**
 * @class App.Models.Article
 * @parent index
 * @inherits jQuery.Model
 * Wraps backend article services.  
 */
$.Model('App.Models.Article',
	/* @Static */
	{
		findAll: "articles/find.json",
	  	findOne: "articles/{id}.json"
	},

	/* @Prototype */
	{
	});
});

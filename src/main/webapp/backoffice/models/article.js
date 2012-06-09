steal('jquery/model', 'jquery/lang/json', function(){

/**
 * @class Backoffice.Models.Article
 * @parent index
 * @inherits jQuery.Model
 * Wraps backend article services.  
 */
$.Model('Backoffice.Models.Article',
	/* @Static */
	{
		findAll: "articles/find.json",
	  	findOne: "articles/{id}.json"
	},

	/* @Prototype */
	{
	});
});

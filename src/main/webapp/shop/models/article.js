steal('jquery/model', 'jquery/lang/json', function(){

/**
 * @class Shop.Models.Article
 * @parent index
 * @inherits jQuery.Model
 * Wraps backend article services.  
 */
$.Model('Shop.Models.Article',
	/* @Static */
	{
		findAll: "articles/find.json",
	  	findOne: "articles/{id}.json"
	},

	/* @Prototype */
	{
	});
});

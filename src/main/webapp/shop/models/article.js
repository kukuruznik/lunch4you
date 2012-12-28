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
	  	findAll: function( success, error ) {
	  		return $.ajax({
	  			url: "articles/find.json",
	  			dataType: "json article.models",
	  			success: success,
	  			error: error || Shop.errorHandler
	  		});
	  	},

	  	getGroupedMenu: function( activeOnly, success, error ) {
	  		return $.ajax({
	  			url: "articles/groupedByCategory.json?activeOnly=" + activeOnly,
	  			dataType: "json article.models",
	  			success: success,
	  			error: error || Shop.errorHandler
	  		});
	  	},
	  	
	  	findOne: function( id, success, error ) {
	  		return $.ajax({
	  			url: "articles/" + id + ".json",
	  			dataType: "json article.model",
	  			success: success,
	  			error: error || Shop.errorHandler
	  		});
	  	}
	},

	/* @Prototype */
	{
	});
});

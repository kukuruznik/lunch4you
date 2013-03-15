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

	  	getGroupedMenu: function( activeDelivery, activeRestaurant, success, error ) {
	  		return $.ajax({
	  			url: "articles/groupedByCategory.json?activeDelivery=" + activeDelivery + "&activeRestaurant=" + activeRestaurant,
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

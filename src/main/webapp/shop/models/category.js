steal('jquery/model', 'jquery/lang/json', function(){

/**
 * @class Shop.Models.Category
 * @parent index
 * @inherits jQuery.Model
 * Wraps backend category services.  
 */
$.Model('Shop.Models.Category',
	/* @Static */
	{
	  	findAll: function( success, error ) {
	  		return $.ajax({
	  			url: "categories/find.json",
	  			dataType: "json category.models",
	  			success: success,
	  			error: error || Shop.errorHandler
	  		});
	  	}
	},

	/* @Prototype */
	{
	});
});

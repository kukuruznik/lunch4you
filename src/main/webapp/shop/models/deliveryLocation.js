steal('jquery/model', 'jquery/lang/json', function(){

/**
 * @class Shop.Models.DeliveryLocation
 * @parent index
 * @inherits jQuery.Model
 * Wraps backend delivery location services.  
 */
$.Model('Shop.Models.DeliveryLocation',
	/* @Static */
	{
	  	findAll: function( success, error ) {
	  		return $.ajax({
	  			url: "deliveryLocations/find.json",
	  			dataType: "json",
	  			success: success,
	  			error: error || Shop.errorHandler
	  		});
	  	},
	  	
	  	findOne: function( id, success, error ) {
	  		return $.ajax({
	  			url: "deliveryLocations/" + id + ".json",
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

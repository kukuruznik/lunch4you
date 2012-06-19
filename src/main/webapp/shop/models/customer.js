steal('jquery/model', 'jquery/lang/json', function(){

/**
 * @class Shop.Models.Customer
 * @parent index
 * @inherits jQuery.Model
 * Wraps backend customer services.  
 */
$.Model('Shop.Models.Customer',
	/* @Static */
	{
	  	findByToken: function( token, success, error ) {
	  		return $.ajax({
	  			url: "customers/byToken/" + token + ".json",
	  			dataType: "json customer.model",
	  			success: success,
	  			error: error || Shop.errorHandler
	  		});
	  	}
	},

	/* @Prototype */
	{
	});
});

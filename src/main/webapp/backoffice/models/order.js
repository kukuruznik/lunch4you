steal('jquery/model', 'jquery/lang/json', function(){

/**
 * @class Backoffice.Models.Order
 * @parent index
 * @inherits jQuery.Model
 * Wraps backend order services.  
 */
$.Model('Backoffice.Models.Order',
	/* @Static */
	{
		findAll: "orders/find.json",
	  	findActive: function( success, error ) {
	  		return $.ajax({
	  			url: "orders/findActive.json",
	  			dataType: "json order.models",
	  			success: success,
	  			error: error || Backoffice.errorHandler
	  		});
	  	}
	},

	/* @Prototype */
	{
	});
});

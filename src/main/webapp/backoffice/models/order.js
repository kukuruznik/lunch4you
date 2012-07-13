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
	  	findActive: function( success, error ) {
	  		return $.ajax({
	  			url: "orders/active.json",
	  			dataType: "json order.models",
	  			success: success,
	  			error: error || Backoffice.errorHandler
	  		});
	  	},

		close: function( ids, success, error ) {
			return $.ajax({
				url: "orders/close.json",
				type: "PUT",
	  			contentType: "application/json",
				data: $.toJSON( ids ),
				dataType: "json",
				success: success,
				error: error || Backoffice.errorHandler
			});
		}
	},

	/* @Prototype */
	{
	});
});

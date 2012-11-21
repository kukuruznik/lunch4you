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

	  	getActiveOrdersByDate: function( success, error ) {
	  		return $.ajax({
	  			url: "backoffice/orders/activeByDate.json",
	  			dataType: "json order.models",
	  			success: success,
	  			error: error || Backoffice.errorHandler
	  		});
	  	},

	  	getActiveOrdersGroupedByArticle: function( success, error ) {
	  		return $.ajax({
	  			url: "backoffice/orders/activeGroupedByArticle.json",
	  			dataType: "json order.models",
	  			success: success,
	  			error: error || Backoffice.errorHandler
	  		});
	  	},

	  	getActiveOrdersGroupedByDeliveryLocation: function( success, error ) {
	  		return $.ajax({
	  			url: "backoffice/orders/activeGroupedByDeliveryLocation.json",
	  			dataType: "json order.models",
	  			success: success,
	  			error: error || Backoffice.errorHandler
	  		});
	  	},

		executeAction: function( ids, action, success, error ) {
			return $.ajax({
				url: "backoffice/orders/" + action + ".json",
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

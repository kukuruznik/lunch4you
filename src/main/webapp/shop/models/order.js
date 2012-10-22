steal('jquery/model', 'jquery/lang/json', function(){

/**
 * @class Shop.Models.Order
 * @parent index
 * @inherits jQuery.Model
 * Wraps backend order services.  
 */
$.Model('Shop.Models.Order',
	/* @Static */
	{
	  	create: function( article, customer, deliveryLocationId, success, error ) {
	  		return $.ajax({
	  			url: "orders.json",
	  			type: "POST",
	  			contentType: "application/json",
	  			data: $.toJSON( { articleId: article.id, customerId: customer.id, deliveryLocationId: deliveryLocationId } ),
	  			dataType: "json order.model",
	  			success: success,
	  			error: error || Shop.errorHandler
	  		});
	  	}
	},

	/* @Prototype */
	{
		getTotal: function() {
			return this.onlyItem.article.price * this.onlyItem.amount;
		}
	});
});

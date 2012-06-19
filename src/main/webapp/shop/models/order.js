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
		findAll: "orders/find.json",
	  	findActive: function( success, error ) {
	  		return $.ajax({
	  			url: "orders/findActive.json",
	  			dataType: "json order.models",
	  			success: success,
	  			error: error || Shop.errorHandler
	  		});
	  	},
	  	create: function( article, token, success, error ) {
	  		return $.ajax({
	  			url: "orders.json",
	  			type: "POST",
	  			contentType: "application/json",
	  			data: $.toJSON( { articleId: article.id, token: token } ),
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

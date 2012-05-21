steal('jquery/model', 'jquery/lang/json', function(){

/**
 * @class App.Models.Order
 * @parent index
 * @inherits jQuery.Model
 * Wraps backend order services.  
 */
$.Model('App.Models.Order',
	/* @Static */
	{
		findAll: "orders/find.json",
	  	findOne: "orders/{id}.json"
	},

	/* @Prototype */
	{
	});
});

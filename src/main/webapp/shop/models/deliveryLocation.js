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
		findAll: "deliveryLocations/find.json",
	  	findOne: "deliveryLocations/{id}.json"
	},

	/* @Prototype */
	{
	});
});

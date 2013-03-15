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
	  	},

	  	getCurrent: function( success, error ) {
			return $.ajax({
	  			url: "shop/currentCustomer.json",
	  			dataType: "json customer.model",
	  			success: success,
	  			error: error || Shop.errorHandler
	  		});
	  	},

		create: function( customer, success, error ) {
	  		return $.ajax({
	  			url: "customers.json",
	  			type: "POST",
	  			contentType: "application/json",
	  			data: $.toJSON( customer ),
	  			dataType: "json customer.model",
	  			success: success,
	  			error: error || Shop.errorHandler
	  		});
		},
		
		updateProfile: function( customer, defaultDeliveryLocationId, success, error ) {
			return $.ajax({
				url: "customers/updateProfile.json",
				type: "POST",
				contentType: "application/json",
	  			data: $.toJSON( { customer: customer, defaultDeliveryLocationId: defaultDeliveryLocationId} ),
				dataType: "json customer.model",
				success: success,
				error: error || Shop.errorHandler
			});
		},
		
		createReferral: function( customer, recipientEmails, referralMessage, success, error ) {
	  		return $.ajax({
	  			url: "customers/createReferral.json",
	  			type: "POST",
	  			contentType: "application/json",
	  			data: $.toJSON(  { senderId: customer.id, recipientEmails : recipientEmails, referralMessage : referralMessage } ),
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

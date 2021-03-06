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

		findByEmail: function( email, success, error ) {
			return $.ajax({
	  			url: "customers/byEmail.json?email=" + email,
	  			dataType: "json customer.model",
	  			success: success,
	  			error: error || Shop.errorHandler
	  		});
	  	},
	  	
		findByPin: function( email, pin, success, error ) {
			return $.ajax({
	  			url: "customers/byPin.json?email=" + email + "&pin=" + pin,
	  			dataType: "json customer.model",
	  			success: success,
	  			error: error || Shop.errorHandler
	  		});
	  	},

	  	sendSigninEmail: function( email, success, error ) {
			return $.ajax({
	  			url: "customers/sendSignInEmail.json?email="+email,
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

		register: function( customer, success, error ) {
	  		return $.ajax({
	  			url: "customers/register.json",
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

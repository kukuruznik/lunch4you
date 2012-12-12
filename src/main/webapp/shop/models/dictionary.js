steal('jquery/model', 'jquery/lang/json', function(){

/**
 * @class Shop.Models.DeliveryLocation
 * @parent index
 * @inherits jQuery.Model
 * Wraps backend delivery location services.  
 */
$.Model('Shop.Models.Dictionary',
	/* @Static */
	{

		loadDictionary: function( locale, success ) {
	  		return $.ajax({
	  			url: "shop/i18n/" + locale + ".dict",
	  			dataType: "json",
	  			success: success,
	  			error: function() {
	  				alert( "Error loading localization data for locale '" + locale + "'" );
	  			}
	  		});
	  	}
	},

	/* @Prototype */
	{
	});
});

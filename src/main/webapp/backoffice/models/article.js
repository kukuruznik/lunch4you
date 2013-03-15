steal('jquery/model', 'jquery/lang/json', function(){

/**
 * @class Backoffice.Models.Article
 * @parent index
 * @inherits jQuery.Model
 * Wraps backend article services.  
 */
$.Model('Backoffice.Models.Article',
	/* @Static */
	{
		findAll: "articles/find.json",
	  	findOne: "articles/{id}.json",

	  	createOrUpdateArticle: function( article, categoryId, success, error ) {
			return $.ajax({
				url: "articles/createOrUpdateArticle.json",
				type: "POST",
				contentType: "application/json",
	  			data: $.toJSON( { article: article, categoryId: categoryId} ),
				dataType: "json article.model",
				success: success,
				error: error || Backoffice.errorHandler
			});
		},
		
	  	setActive: function( articleId, active, service, success, error ) {
			return $.ajax({
				url: "articles/setActive.json?articleId=" + articleId + "&active=" + active + "&service=" + service,
				dataType: "json article.model",
				success: success,
				error: error || Backoffice.errorHandler
			});
		},

	  	setLimit: function( articleId, limit, success, error ) {
			return $.ajax({
				url: "articles/setLimit.json?articleId=" + articleId + "&limit=" + limit,
				dataType: "json article.model",
				success: success,
				error: error || Backoffice.errorHandler
			});
		},

	  	getGroupedMenu: function( activeDelivery, activeRestaurant, success, error ) {
	  		return $.ajax({
	  			url: "articles/groupedByCategory.json?activeDelivery=" + activeDelivery + "&activeRestaurant=" + activeRestaurant,
	  			dataType: "json article.models",
	  			success: success,
	  			error: error || Backoffice.errorHandler
	  		});
	  	}

	},

	/* @Prototype */
	{
	});
});

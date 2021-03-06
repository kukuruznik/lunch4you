steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view' ).then( './views/menuTable.ejs', './views/articleForm.ejs', function( $ ) {

	/**
	 * @class Backoffice.Menu.Edit
	 * @parent index
	 */
	$.Controller( 'Backoffice.Menu.Edit',

	/** @Static */
	{
	},

	/** @Prototype */
	{
		init: function() {
			
			this.actions = {
					"edit" : this._editArticle,
					"activateDelivery" : this._activateArticleDelivery,
					"deactivateDelivery" : this._deactivateArticleDelivery,
					"activateRestaurantWeekly" : this._activateArticleRestaurantWeekly,
					"deactivateRestaurantWeekly" : this._deactivateArticleRestaurantWeekly,
					"setIsNew" : this._deactivateArticleRestaurant
			};
			
			this._reloadMenu();
		},


		update: function() {
			this._reloadMenu();
		},
		
		"a[class='createArticleLink'] click": function( el, evt ) {
			var categoryId = el.attr("categoryId");
			this._createArticle( categoryId );
		},

		"a[class='action-link'] click": function( el, evt ) {
			var articleId = this._getArticleIdFromParent(el);
			var article = this._getArticleById( articleId );
			var action = el.attr("action");
			var actionHandler = this.actions[action];
			$(this.proxy(actionHandler, article) );
		},

		"input[class='daily-limit-input'] keypress": function( el, evt ) {
			if(evt.keyCode == 27){
				el.val(el.attr("initialValue"));
				el.blur();
				return;
			}
			if(evt.which == 13){
				var val = el.val();
				var articleId = this._getArticleIdFromParent(el);
				Backoffice.Models.Article.setLimit( articleId, val ).done( this.proxy( "_articleSaved" ) );
			}
		},
		
		
		"#flagCheckbox click": function( el, evt ) {
		
			var articleId = this._getArticleIdFromParent(el);
			var flagName = el.attr("flagName");
			var value = el.is(":checked");

			Backoffice.Models.Article.setFlag( articleId, flagName, value ).done( this.proxy( "_articleSaved" ) );
		},

		"#cancelButton click": function( el, evt ) {

			var articleId = $(el.parents( "tr" )[0]).attr("articleId");
			this._destroyArticleEditDialog(articleId);
		},

		"#submitButton click": function( el, evt ) {

			var formParams = $(el.parents( "form" )[0]).formParams();
			this._saveArticle(formParams);
		},

		_getArticleIdFromParent : function ( el ) {
			var articleRow = $($(el.parents("tr[class~='articleRow']"))[0]);
			return articleRow.attr("articleId");
		},

		_createArticle: function( categoryId ) {
			var categoryRow = this._getCategoryRow( categoryId );
			
			var article = new function(){};
			article.id = 0;
			article.category = new function(){};
			article.category.id = categoryId;
			
			this._showArticleEditDialog( article, categoryRow );
			//alert(categoryId);
		},

		_editArticle: function( article ) {
			var articleRow = this._getArticleRow( article.id );
			this._showArticleEditDialog( article, articleRow );
		},

		_saveArticle: function( fp ) {
			
			var article = new function(){};
			article.id = fp.id != "0" ? fp.id : null;
			article.code = fp.code;
			article.name_cz = fp.name_cz;
			article.name_en = fp.name_en;
			article.description_cz = fp.description_cz;
			article.description_en = fp.description_en;
			article.priceDelivery = fp.priceDelivery;
			article.priceRestaurant = fp.priceRestaurant;
			article.isNew = fp.isNew ? true : false;
			
			Backoffice.Models.Article.createOrUpdateArticle( article, fp.categoryId ).done( this.proxy( "_articleSaved" ) );
		},

		_articleSaved: function( article ) {
			this._reloadMenu();
		},

		_getCategoryRow : function ( categoryId ) {
			var categoryRow = $("tr[categoryId='" + categoryId + "']");
			return categoryRow;
		},

		_getArticleRow : function (articleId) {
			var articleRow = $("tr[class~='articleRow'][articleId='" + articleId + "']");
			return articleRow;
		},

		_getArticleEditRow : function (articleId) {
			var articleEditRow = $("tr[class='articleEditRow'][articleId='" + articleId + "']");
			if(articleEditRow.length == 0)
				return null;
			return articleEditRow;
		},

		_destroyArticleEditDialog : function( articleId ){
			var editRow = this._getArticleEditRow( articleId );
			editRow.remove();
		},

		_showArticleEditDialog: function( article, previousRow ) {
			var editRow = this._getArticleEditRow( article.id );
			if(editRow != null)
				return;
			previousRow.after("<tr class='articleEditRow' articleId=" + article.id + "></tr>");
			editRow = previousRow.next();
			editRow.html(this.view("articleForm", article));
		},

		
		_getArticleById: function(id){
			var groups = Backoffice.Menu.groupedMenu;
			for ( var i = 0; i < groups.length; i++ ) {
				var articles = groups[i].items;
				for ( var j = 0; j < articles.length; j++ ) {
					
					var article = articles[j];
					if(article.id == id)
						return article;
				}
			}
			return null;
		},

		_reloadMenu: function() {
			// create filter variables and leave them empty to show all articles
			var activeDelivery = null;
			var activeRestaruantWeekly = null;
			var activeRestaruantDaily = null;
			Backoffice.Models.Article.getGroupedMenu( activeDelivery, activeRestaruantWeekly, activeRestaruantDaily ).done( this.proxy( "_dataLoaded" ) );
		},

		_dataLoaded: function( groupedMenu ) {
			Backoffice.Menu.groupedMenu = groupedMenu;
			this._render( groupedMenu );
		},

		_render: function( groupedMenu ) {
			this.element.html( this.view( 'menuTable', groupedMenu ) );
		}
	} );

} );

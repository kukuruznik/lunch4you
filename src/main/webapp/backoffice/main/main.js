steal(
	'jquery/controller',
	'jquery/view/ejs', 
	'jquery/controller/view', 
	'shop/models/dictionary.js'
).then(	

	"./views/main.ejs",


	function( $ ) {

	/**
	 * @class Backoffice.Main.Main
	 * @parent index
	 * @inherits jQuery.Controller Main controller for the shop application.
	 */
	$.Controller( "Backoffice.Main",

	/** @Static */
	{
		_availableLocales: [ "cz", "en" ],
		
		getAvailableLocales : function() {
			return this._availableLocales;
		},
		
		getLocale : function() {
			return "cz";
		} 

	},

	/** @Prototype */
	{
		init: function() {
			
			this._render();
			
		},

		_render: function() {
			// render the main page structure
			$( "#content" ).html( this.view( "main", this ) );

			$( '#meals1' ).backoffice_order_list1();
			$( '#meals2' ).backoffice_order_list2();
			$( '#meals3' ).backoffice_order_list3();
			$( '#menuEdit' ).backoffice_menu_edit();
			$( '#menuNavigation' ).backoffice_menu_restaurant();

		},


		"div.tab-selector click" : function( el, evt ) {
			var tabContentEl = $("#tabContent" + el.index());
			var otherContents = tabContentEl.siblings();
			otherContents.hide();
			tabContentEl.show();
		},
		
		
		".main-navigation-selector click" : function( el, evt ) {
			var navIndex = el.attr("navigationIndex");
			var navItem = this.Class.getNavigationItems()[navIndex];
			this.Class.navigateTo( navItem );
		}

	});

	Backoffice.errorHandler = function( jqXHR, textStatus, error ) {
		steal.dev.log( "response error ", jqXHR.status );
		if ( jqXHR.status == 404 ) {
			$( '#overlay' ).common_login();
		} else {
			alert( "An error occured!\nStatus: " + textStatus + "\nDetails: " + error );
		}
	};
});


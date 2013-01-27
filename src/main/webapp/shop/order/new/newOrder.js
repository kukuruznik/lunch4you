steal( 
		'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view' 
).then( 
	
	"./views/orderForm.ejs",
	"./views/orderMessage.ejs",

	function( $ ) {

	/**
	 * @class Shop.Order.New
	 * @parent index
	 * @inherits jQuery.Controller Manages ordering (display and submission).
	 */
	$.Controller( 'Shop.Order.New',

	/** @Static */
	{
	},

	/** @Prototype */
	{
		init: function() {
			steal.dev.log( "Order creation controller initialized" );
			this._reloadData();
		},

		update: function() {
			this._reloadData();
		},

		"#submitButton click": function( el, evt ) {
			this._createOrder();
		},

		"#deliveryLocationsSelect change": function( el, evt ) {
			var selectedId = el.val();
			var ddlDiv = $( "#setDdlDiv" );
			var checkboxEl = $( "#setDefaultDeliveryLocation" );
			if(selectedId == -1){
				ddlDiv.hide();				
				return;
			}
			// TODO show some details about selected location
			var custDdlId = null;
			if(Shop.customer && Shop.customer.defaultDeliveryLocation)
				custDdlId = Shop.customer.defaultDeliveryLocation.id;
			if(!custDdlId || custDdlId != selectedId){
				ddlDiv.show();
				// make checkbox checked only if there is no DDL in profile. Otherwise the user probably just wants
				// to exceptionally deliver to a different location
				if(!custDdlId){
					checkboxEl.attr("checked","true");
				}
			}else{
				ddlDiv.hide();				
			}
			
		},

		_reloadData: function() {
			Shop.Models.Article.findOne( Shop.params.meal , this.proxy( "_onDataLoaded" ));
		},

		_onDataLoaded: function( article ) {
			this.article = article;
			this.deliveryLocations = Shop.deliveryLocations;
			this.customer = Shop.customer; // can be null!
			this.deliveryLocation = this.customer ? this.customer.defaultDeliveryLocation : null;

			var orderResult = new function(){};

			if ( !this.customer ){
				orderResult.resultCode = "NOT_AUTHENTICATED";
				this._renderMessage(orderResult);
				return;				
			}
			if ( !this.article ) {
				orderResult.resultCode = "NOT_EXISTS";
				this._renderMessage(orderResult);
				return;				
			} 

			// check if this meal is unavailable or sold out
			if(!article.isActive){
				orderResult.resultCode = "NOT_AVAILABLE";
				this._renderMessage(orderResult);
				return;
			}
			if(article.isSoldOut){
				orderResult.resultCode = "SOLD_OUT";
				this._renderMessage(orderResult);
				return;
			}

			this._renderForm();
		},

		_createOrder: function() {
			if ( ! this._validateOrderForm() )
				return;
			this._enableSubmit( false );
			var self = this;

			var custDdlId = null;
			if(Shop.customer && Shop.customer.defaultDeliveryLocation)
				custDdlId = Shop.customer.defaultDeliveryLocation.id;

			var selectedDlId = $( "#deliveryLocationsSelect" ).val();
			var setDefaultDeliveryLocation = $( "#setDefaultDeliveryLocation" ).attr("checked") ? true : false;
			
			// Set selected DL as default only if checkbox is checked and:
			// 1) customer has no Default Delivery location set in his profile or
			// 2) selected location is different from the one in the profile
			var setDdl = setDefaultDeliveryLocation && ( !custDdlId || custDdlId != selectedDlId);
			
			var note = $( "#note" ).val();
			
			Shop.Models.Order.create( this.article, this.customer, selectedDlId, setDdl, note, function( orderResult ) {
				self._renderMessage(orderResult);
			} );
		},

		_validateOrderForm : function() {
			var deliveryLocationId = $( "#deliveryLocationsSelect" ).val();

			if(deliveryLocationId == -1){
				alert( $.EJS.Helpers.prototype.msg( "order.detail.validation.emptyDeliveryLocation" ) );
				return false;				
			}
			
			return true;
		},

		_enableSubmit: function( enable ) {
			var button = $( "#submitButton" );
			Shop.Utils.enableInput( button, enable );
		},

		_renderMessage: function( orderResult ) {
			this.orderResult = orderResult;
			this.element.html( this.view( "orderMessage", this ) );
		},

		_renderForm: function() {
			this.element.html( this.view( "orderForm", this ) );
		}
	} );

} );

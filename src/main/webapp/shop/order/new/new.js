steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view', "common/register_customer" ).then( './views/page.ejs', function( $ ) {

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
			this.element.html( this.view( 'page', this ) );
			this._reloadData();
		},

		update: function() {
			this._reloadData();
		},

		"#order click": function( el, evt ) {
			this._createOrder(this.articleDfr);
			console.log( "ordered!" );
		},

		"#changeCustomerButton click": function( el, evt ) {
			console.log( "changeCustomerButton clicked!" );
			this._showCustomerForm();
		},

		"#customerForm close": function( el, evt ) {
			console.log( "closing form..." );
			this._hideCustomerForm();
		},
		
		"#customerForm register": function( el, evt, customerData ) {
			console.log( "registering customer ", customerData );

			var customerDfr = Shop.Models.Customer.create( customerData );
			customerDfr.done( this.proxy( "_onCustomerLoaded" ) );
		},

		"#deliveryLocationsSelect change": function( el, evt ) {
			this.deliveryLocation = el.val();
		},

		_reloadData: function() {
			var articleDfr = Shop.Models.Article.findOne( Shop.params.meal );
			var customerDfr = Shop.Models.Customer.findByToken( Shop.params.token );
			var deliveryLocationsDfr = Shop.Models.DeliveryLocation.findAll();

			$.when( articleDfr, customerDfr, deliveryLocationsDfr ).done( this.proxy( "_onDataLoaded" ) );
		},

		_onDataLoaded: function( articleResponse, customerResponse, deliveryLocationsResponse ) {
			this.article = articleResponse[ 0 ];
			this.deliveryLocations = deliveryLocationsResponse[ 0 ];
			this._onCustomerLoaded( customerResponse[ 0 ] );
		},

		_onCustomerLoaded: function( customer ) {
			this.customer = customer;
			this.deliveryLocation = customer ? customer.defaultDeliveryLocation : null;

			this._render();
		},

		_createOrder: function( ) {
			Shop.Models.Order.create( this.article, this.customer, function( order ) {
				alert( "Ordered!\n" +
						"Pay us " + order.getTotal() + " bucks!" );
				$( "#order" ).attr( "disabled", "disabled" );
			} );
		},

		_showCustomerForm: function() {
			$( "#order" ).attr( "disabled", "disabled" );
			$( "#currentCustomerDetails" ).hide();
			$( "#customerForm" ).show();
			this.element.find( "input[type=text]:first" ).focus();
		},
		
		_hideCustomerForm: function() {
			$( "#customerForm" ).hide();
			$( "#currentCustomerDetails" ).show();
			$( "#order" ).removeAttr( "disabled" );
		},

//		"#changeDeliveryLocationButton click": function( el, evt ) {
//			console.log( "changeDeliveryLocationButton clicked!" );
//			this._showDeliveryLocationForm();
//		},
//
//		"#submitDeliveryLocationButton click": function( el, evt ) {
//			console.log( "submitDeliveryLocationButton clicked!" );
//
//			var locIndex = $("#deliveryLocationsSelect :selected").index();
//			var location = this.deliveryLocations[locIndex];
//			console.log( "loc ",  location);
//			
//			this.deliveryLocation = location;
//
//			this._render();
//		},
//
//		"#cancelChangeDeliveryLocationButton click": function( el, evt ) {
//			console.log( "cancelChangeDeliveryLocationButton clicked!" );
//			this._render();
//		},
//
//		_showDeliveryLocationForm: function() {
//			$("#deliveryLocationDetails").attr("style", "display : none;");
//			var form = $("#deliveryLocationForm");
//			form.attr("style","display : block;");
//			//$("#customerName").focus();
//			$( "#order" ).attr( "disabled", "disabled" );
//		},

		_render: function() {
			console.log( "rendering..." );
			var detailElem = this.element.find( "#detail" );

			if ( !this.article ) {
				detailElem.html( this.view( "unknownMeal" ) );
			} else if ( !this.customer ) {
				detailElem.html( this.view( "unknownCustomer" ) );
			} else {
				detailElem.html( this.view( 'orderInfo', this ) );
				detailElem.find( "#customerForm" ).common_register_customer( { deliveryLocations: this.deliveryLocations } );
			}
		}
	} );

} );

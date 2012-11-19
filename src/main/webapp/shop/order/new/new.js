steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view', "common/register_customer" ).then( function( $ ) {

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

		"#order click": function( el, evt ) {
			this._createOrder();
		},

		"#changeCustomerButton click": function( el, evt ) {
			this._showCustomerForm();
		},

		"#customerForm close": function( el, evt ) {
			this._hideCustomerForm();
		},
		
		"#customerForm register": function( el, evt, customerData ) {
			steal.dev.log( "registering customer ", customerData );

			var customerDfr = Shop.Models.Customer.create( customerData );
			customerDfr.done( this.proxy( "_onCustomerLoaded" ) );
		},

		"#deliveryLocationsSelect change": function( el, evt ) {
			// TODO - tu je problem. Raz sa do tejto premennej odklada DeliveryLocatuion object, inokedy zas ID
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

		_createOrder: function() {
			this._enableOrder( false );
			var self = this;
			var deliveryLocationId = $( "#deliveryLocationsSelect" ).val();
			
			Shop.Models.Order.create( this.article, this.customer, deliveryLocationId, function( order ) {
				alert($.EJS.Helpers.prototype.msg("order.detail.action.orderedMsg"));
				self._enableOrder( true );
			} );
		},

		_enableOrder: function( yes ) {
			if ( yes )
				$( "#order" ).removeAttr( "disabled" );
			else
				$( "#order" ).attr( "disabled", "disabled" );
		},

		_showCustomerForm: function() {
			this._enableOrder( false );
			$( "#currentCustomerDetails" ).hide();
			$( "#customerForm" ).show();
			this.element.find( "input[type=text]:first" ).focus();
		},
		
		_hideCustomerForm: function() {
			$( "#customerForm" ).hide();
			$( "#currentCustomerDetails" ).show();
			this._enableOrder( true );
		},

		_render: function() {
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

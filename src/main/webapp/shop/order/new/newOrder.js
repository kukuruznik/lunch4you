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

		"#submitButton click": function( el, evt ) {
			this._createOrder();
		},

		"#deliveryLocationsSelect change": function( el, evt ) {
			this.deliveryLocation = el.val();
		},

		_reloadData: function() {
			var articleDfr = Shop.Models.Article.findOne( Shop.params.meal );
			var deliveryLocationsDfr = Shop.Models.DeliveryLocation.findAll();

			$.when( articleDfr, deliveryLocationsDfr ).done( this.proxy( "_onDataLoaded" ) );
		},

		_onDataLoaded: function( articleResponse, deliveryLocationsResponse ) {
			this.article = articleResponse[ 0 ];
			this.deliveryLocations = deliveryLocationsResponse[ 0 ];
			this.customer = Shop.customer; // can be null!
			this.deliveryLocation = this.customer ? this.customer.defaultDeliveryLocation : null;

			this._render();
		},

		_createOrder: function() {
			this._enableSubmit( false );
			var self = this;
			var deliveryLocationId = $( "#deliveryLocationsSelect" ).val();
			var note = $( "#note" ).val();
			
			Shop.Models.Order.create( this.article, this.customer, deliveryLocationId, note, function( order ) {
				self._renderConfirmation();
			} );
		},

		_enableSubmit: function( enable ) {
			var button = $( "#submitButton" );
			Shop.Utils.enableInput( button, enable );
		},

		_renderConfirmation: function() {
			this.element.html( this.view( "orderConfirmation", this ) );
		},

		_render: function() {
			// TODO show screenwith non authenticated user
			if ( !this.customer )
				this._enableSubmit( false );
			if ( !this.article ) {
				this.element.html( this.view( "unknownMeal" ) );
			} else {
				this.element.html( this.view( "orderForm", this ) );
			}
		}
	} );

} );

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
			this._enableOrder( false );
			var self = this;
			var deliveryLocationId = $( "#deliveryLocationsSelect" ).val();
			var note = $( "#note" ).val();
			
			Shop.Models.Order.create( this.article, this.customer, deliveryLocationId, note, function( order ) {
				alert($.EJS.Helpers.prototype.msg("order.detail.action.orderedMsg"));
				self._enableOrder( true );
			} );
		},

		_enableOrder: function( enable ) {
			var button = $( "#order" );
			Shop.Utils.enableInput( button, enable );
		},

		_render: function() {
			if ( !this.article ) {
				this.element.html( this.view( "unknownMeal" ) );
			} else {
				this.element.html( this.view( "orderInfo", this ) );
				if ( !this.customer )
					this._enableOrder( false );
			}
		}
	} );

} );

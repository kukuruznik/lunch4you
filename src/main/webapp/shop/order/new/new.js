steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view' ).then( './views/page.ejs', function( $ ) {

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
			this.articleDfr = Shop.Models.Article.findOne( { id: Shop.params.meal } );
			this.customerDfr = Shop.Models.Customer.findByToken( Shop.params.token );
			this._render();
		},

		"#order click": function( el, evt ) {
			this.articleDfr.done( this.proxy( "_createOrder" ) );
			console.log( "ordered!" );
		},

		_createOrder: function( article ) {
			Shop.Models.Order.create( article, Shop.params.token, function( order ) {
				alert( "Ordered!\n" +
						"Pay us " + order.getTotal() + " bucks!" );
				$( "#order" ).attr( "disabled", "disabled" );
			} );
		},

		_render: function() {
			this.element.html( this.view( 'page', { article: this.articleDfr, customer: this.customerDfr } ) );
		}
	} );

} );

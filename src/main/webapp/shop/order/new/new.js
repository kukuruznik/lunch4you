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
			var self = this;
			this.articleDfr = Shop.Models.Article.findOne( { id: Shop.params.meal } );
			this.articleDfr.done( function (article) {
					self.article = article;
				}
			);
			this.customerDfr = Shop.Models.Customer.findByToken( Shop.params.token );
			this.customerDfr.done( function (customer) {
					self.customer = customer;
				}
			);
			this._render();
		},

		"#order click": function( el, evt ) {
			this._createOrder(this.articleDfr);
			console.log( "ordered!" );
		},

		_createOrder: function( ) {
			Shop.Models.Order.create( this.article, this.customer, function( order ) {
				alert( "Ordered!\n" +
						"Pay us " + order.getTotal() + " bucks!" );
				$( "#order" ).attr( "disabled", "disabled" );
			} );
		},

		"#changeCustomerButton click": function( el, evt ) {
			console.log( "changeCustomerButton clicked!" );
			this._showCustomerForm();
		},
		
		
		"#submitCustomerDetailsButton click": function( el, evt ) {
			console.log( "submitCustomerDetailsButton clicked!" );
			
			// New customer object
			var cus = new Object();
			cus.firstName = $("#customerName").attr("value");
			cus.lastName = "";
			cus.email = $("#customerEmail").attr("value");
			cus.menuSubscription = $("#menuSubscription").attr("checked") == ("checked") ? true : false;
			
			console.log(cus);
			
			this.customerDfr = Shop.Models.Customer.create(cus);
			
			this._render();
		},

		"#cancelChangeCustomerButton click": function( el, evt ) {
			console.log( "cancelChangeCustomerButton clicked!" );
			this._render();
		},

		_showCustomerForm: function() {
			$("#currentCustomerDetails").attr("style", "display : none;");
			var form = $("#customerForm");
			form.attr("style","display : block;");
			$("#customerName").focus();
			$( "#order" ).attr( "disabled", "disabled" );
		},

		_render: function() {
			this.element.html( this.view( 'page', { article: this.articleDfr, customer: this.customerDfr } ) );
		}
	} );

} );

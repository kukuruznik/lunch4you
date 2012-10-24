steal( 'jquery/controller', 'jquery/view/ejs', 'jquery/controller/view', 'jquery/dom/form_params' ).then( './views/form.ejs', function( $ ) {

	/**
	 * @class Common.RegisterCustomer
	 * @parent index
	 * @inherits jQuery.Controller Manages customer registration form.
	 */
	$.Controller( 'Common.RegisterCustomer',

	/** @Static */
	{
	},

	/** @Prototype */
	{
		init: function() {
			steal.dev.log( "Customer registation form controller initialized" );
			this.deliveryLocations = this.options.deliveryLocations;
			this._render();
		},

		"input[type=submit] click": function( el, evt ) {
			var formParams = this.element.find( "form" ).formParams();
			this.element.trigger( "register", formParams );
			evt.preventDefault();
			return false;
		},
		
		"input[type=reset] click": function( el, evt ) {
			this._close();
			evt.preventDefault();
			return false;
		},

		_close: function() {
			this.element.trigger( "close" );
		},

		"{document} keypress": function( el, evt ) {
			if ( evt.keyCode == 27 )
				this._close();
		},

		_render: function() {
			this.element.html( this.view( 'form', this.deliveryLocations ) );
		}
	});
});

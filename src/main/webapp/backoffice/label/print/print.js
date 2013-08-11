steal( "jquery/controller", "jquery/view/ejs", "jquery/controller/view", "backoffice/models" ).then( "./views/layout.ejs", function() {

	/**
	 * @class Backoffice.Label.Print
	 * @parent index
	 */
	$.Controller( "Backoffice.Label.Print",

	/** @Static */
	{
	},

	/** @Prototype */
	{
		init: function() {
			this.rows = 5;
			this.cols = 2; // would be fine to externalize

			this._refresh();
		},

		update: function() {
			this._refresh();
		},

		".label click": function( el, evt ) {
			var innerElement = el.children().first();
			var pageElement = el.parent();
			var pageNum = pageElement.data().number;
			var coords = el.data();
			var newState = innerElement.hasClass( "not-used" ); // if the position is not used at the moment (false) then turn it to true

			this.pageTemplates[ pageNum ][ coords.row ][ coords.col ] = newState;

			this._doLayout();
		},

		_refresh: function() {
			var allOrders = window.opener.Backoffice.orders;
			var idsToPrint = window.opener.Backoffice.orderIdsToPrint;
			var orders = [];

			for ( var i = 0; i < allOrders.length; i++ )
				if ( idsToPrint.indexOf( allOrders[ i ].id ) != -1 )
					orders.push( allOrders[ i ] );
			this._ordersRead( orders );			
		},

		_ordersRead: function( orders ) {
			this.orders = orders;
			this._doLayout();
		},

		/**
		 * Creates an array of pages containing an array of rows containing values for that particular position.
		 * The value is one of:
		 * Object (the order object)
		 * false (the position must be skipped for printing)
		 * undefined (the position is behind the last printed label and thus it is not displayed in the preview)
		 * When the layout is finished the _render method is called.
		 * This method expects the orders to be laid out in the 'orders' property,
		 * maintains the page masks in the pageTemplates property and fills the 'pages' property with the layout structure.
		 */
		_doLayout: function() {
			var
				self = this,

				// number of rows and columns on one page
				rows = this.rows,
				cols = this.cols,

				// the index of current order
				orderIndex = 0,

				// page traversal indexes
				pageNum = 0,
				row = 0,
				col = 0;

			var
				createNewPageTemplate = function() {
					var pageTemplate = [];

					for ( var i = 0; i < rows; i++ ) {
						var row = [];
						for ( var j = 0; j < cols; j++ ) {
							row.push( true );
						}
						pageTemplate.push( row );
					}

					return pageTemplate;
				},

				addPageTemplateIfMissing = function() {
					// the number of page templates is always greater or equal to the page index
					if ( self.pageTemplates.length == pageNum ) {
						self.pageTemplates.push( createNewPageTemplate() );
					}
				},

				addPageIfMissing = function() {
					// the number of pages is always greater or equal to the page index
					if ( self.pages.length == pageNum ) {
						self.pages.push( [] );
					}
				},

				addRowIfMissing = function() {
					var lastPage = self.pages[ pageNum ];

					// the number of rows is always greater or equal to the row index
					if ( lastPage.length == row ) {
						lastPage.push( [] );
					}
				},

				nextPosition = function() {
					col++;
					if ( col >= cols ) { // next row
						col = 0;
						row++;
						if ( row >= rows ) { // next page
							row = 0;
							pageNum++;
						}
					}
				};

			// initializing the templates (masks)
			this.pageTemplates = this.pageTemplates || [];

			if ( Backoffice.lastPage )
				this.pageTemplates.push( Backoffice.lastPage );

			// building the pages from scratch
			this.pages = [];

			// let's put the orders into the printable positions and false to non-printable
			while ( orderIndex < this.orders.length ) {
				addPageTemplateIfMissing();
				addPageIfMissing();
				addRowIfMissing();

				var isPositionUsable = this.pageTemplates[ pageNum ][ row ][ col ];

				if ( isPositionUsable ) {
					this.pages[ pageNum ][ row ][ col ] = this.orders[ orderIndex++ ];
				} else {
					this.pages[ pageNum ][ row ][ col ] = false;
				}

				nextPosition();
			}

			// let's finish the page: put null to printable positions and false to non-printable
			while ( pageNum < this.pages.length ) {
				addRowIfMissing();

				var isPositionUsable = this.pageTemplates[ pageNum ][ row ][ col ];

				if ( isPositionUsable ) {
					this.pages[ pageNum ][ row ][ col ] = null;
				} else {
					this.pages[ pageNum ][ row ][ col ] = false;
				}

				nextPosition();
			}

			this._render();
		},

		"input[type=button] click": function( el, evt ) {
			console.log( "Orders to update: ", this.orders );
		},

		_render: function() {
			this.element.html( this.view( "layout", this.pages ) );
		}
	} );

} );

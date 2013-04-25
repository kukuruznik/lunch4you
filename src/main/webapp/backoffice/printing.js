steal(
	'./printing.css', 		// label printing CSS file
	'backoffice/label/print'
).then( function() {
	// setting up the label printing controller as the first thing
	$("#content").backoffice_label_print();
});

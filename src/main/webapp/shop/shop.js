steal(
	"jquery/controller" 
	,"jquery/view/ejs" 
	,"jquery/controller/view" 
	,"jquery/event/bbq"
	,"jquery/dom/cookie"
	,"shop/utils/utils.js"
	,"common/register_customer/register_customer.js"
	,"shop/menu/list/list.js"
	,"shop/order/new/newOrder.js"
	,"shop/home/home.js"
	,"shop/contacts/contacts.js"
	,"shop/ordering/ordering.js"
	,"shop/referral/new/newReferral.js"
	,"shop/profile/edit/editProfile.js"
	,"shop/shop.css" 			// application CSS file
	,"common/form.css" 			// forms CSS file
	,"shop/models/models.js"		// steals all your models
).then(
	"shop/main",				// main controller...

	function() {

		// setting up the main controller as the first thing
		$( "#content" ).shop_main();
	}
);

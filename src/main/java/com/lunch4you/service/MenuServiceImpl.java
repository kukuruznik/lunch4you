package com.lunch4you.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunch4you.dao.ArticleDao;
import com.lunch4you.dao.CategoryDao;
import com.lunch4you.dao.CustomerDao;
import com.lunch4you.dao.DeliveryLocationDao;
import com.lunch4you.dao.OrderDao;
import com.lunch4you.dao.OrderItemDao;
import com.lunch4you.dao.ReferralDao;
import com.lunch4you.dao.filter.ArticleFilter;
import com.lunch4you.dao.filter.CustomerFilter;
import com.lunch4you.dao.filter.OrderFilter;
import com.lunch4you.domain.Article;
import com.lunch4you.domain.ArticleWithOrders;
import com.lunch4you.domain.Category;
import com.lunch4you.domain.CategoryWithArticles;
import com.lunch4you.domain.Customer;
import com.lunch4you.domain.DeliveryLocation;
import com.lunch4you.domain.DeliveryLocationWithArticles;
import com.lunch4you.domain.Order;
import com.lunch4you.domain.OrderItem;
import com.lunch4you.domain.OrderResult;
import com.lunch4you.domain.Referral;

@Transactional
@Service
public final class MenuServiceImpl implements MenuService {

	private static final Logger logger = LoggerFactory.getLogger( MenuServiceImpl.class );

	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private DeliveryLocationDao deliveryLocationDao;

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderItemDao orderItemDao;

	@Autowired
	private ReferralDao referralDao;

	@Autowired
	private MailingService mailingService;

	@Override
	public Article findArticleById( Long id ) {
		return articleDao.load( id );
	}

	@Override
	public Customer getCustomer( Long id ) {
		return customerDao.load( id );
	}

	@Override
	public Customer findCustomerByToken( String token ) {
		CustomerFilter filter = new CustomerFilter();
		filter.token = token;

		List<Customer> foundCustomers = customerDao.find( filter );
		return foundCustomers.size() == 0 ? null : foundCustomers.get( 0 );
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerDao.loadAll();
	}

	@Override
	public List<Customer> getSubscribedCustomers(Boolean menuWeekly, Boolean news) {
		CustomerFilter filter = new CustomerFilter();
		filter.isActive = true;
		filter.isSubscribedMenuWeekly = menuWeekly;
		filter.isSubscribedNews = news;
		return customerDao.find(filter );
	}

	@Override
	public Customer registerCustomer( String firstName, String lastName, String email, Long defaultDeliveryLocationId, boolean wantsToReceiveMenu ) {
		
		DeliveryLocation ddl = deliveryLocationDao.load( defaultDeliveryLocationId );
		
		Customer newCustomer = new Customer();

		String token = createToken( email );
		newCustomer.setCredit( 0 );
		newCustomer.setEmail( email );
		newCustomer.setFirstName( firstName );
		newCustomer.setLastName( lastName );
		newCustomer.setToken( token );
		newCustomer.setIsActive( wantsToReceiveMenu );
		newCustomer.setDefaultDeliveryLocation( ddl );
		return customerDao.insert( newCustomer );
	}

	@Override
	public Customer updateCustomer( Customer customer ) {
		return customerDao.update( customer );
	}

	/**
	 * Updates customer object with data provided in @customerProfile object
	 */
	@Override
	public Customer updateCustomerProfile(Customer profile,
			long defaultDeliveryLocationId) {
		
		Customer target = customerDao.load( profile.getId());
		DeliveryLocation ddl = deliveryLocationDao.load( defaultDeliveryLocationId );

		target.setFirstName( profile.getFirstName() );
		target.setLastName( profile.getLastName() );
		target.setEmail( profile.getEmail() );
		target.setDefaultDeliveryLocation( ddl );
		target.setIsSubscribedMenuWeekly( profile.getIsSubscribedMenuWeekly() );
		target.setIsSubscribedNews( profile.getIsSubscribedNews() );
		
		return customerDao.update( target );
		
	}

	@Override
	public void setDefaultDeliveryLocation(long customerId,
			long deliveryLocationId) {
		Customer target = customerDao.load( customerId );
		DeliveryLocation dl = deliveryLocationDao.load( deliveryLocationId );
		target.setDefaultDeliveryLocation( dl );
		customerDao.update( target );
	}

	@Override
	public DeliveryLocation getDeliveryLocation( Long id ) {
		return deliveryLocationDao.load( id );
	}

	@Override
	public List<DeliveryLocation> getAllDeliveryLocations() {
		return deliveryLocationDao.loadAll();
	}

	@Override
	public OrderResult createOrder( Long articleId, Long customerId, Long deliveryLocationId, String note ) {

		OrderResult result = new OrderResult();
		
		OrderItem item = new OrderItem();
		Article article = articleDao.load( articleId );

		if( !article.getIsActive() ){
			result.setResultCode( OrderResult.ResultCode.NOT_AVAILABLE );
			return result;
		}

		Integer dailyLimit = article.getDailyLimit();

		if( dailyLimit != null ){
			if( article.isSoldOut() ){
				result.setResultCode( OrderResult.ResultCode.SOLD_OUT );
				return result;
			} else {				
				dailyLimit --;
				article.setDailyLimit( dailyLimit );
			}
		}

		Customer customer = getCustomer( customerId );
		DeliveryLocation deliveryLocation = deliveryLocationDao.load( deliveryLocationId );
		item.setAmount( 1 );
		item.setArticle( article );

		Order newOrder = new Order();

		newOrder.setOwner( customer );
		newOrder.setDeliveryLocation( deliveryLocation );
		newOrder.setNote( note );
		newOrder.setStatus( Order.Status.OPEN );
		newOrder.setItems( Collections.singletonList( item ) );


		Order order = orderDao.insert( newOrder );
		

		try {
			mailingService.sendOrderConfirmation( order );
		} catch ( MailSendException e ) {
			logger.error( "Confirmation e-mail sending failed!", e );
		}
		
		result.setResultCode( OrderResult.ResultCode.OK );
		result.setOrder(order);
		
		return result;
	}

	@Override
	public List<Article> getMenu() {
		return articleDao.loadAll();
	}

	@Override
	public List<Category> getCategories() {
		return categoryDao.loadAll();
	}

	@Override
	public List<Order> getActiveOrders() {
		OrderFilter filter = new OrderFilter();
		filter.status = Order.Status.OPEN;

		List<Order> orders = orderDao.find( filter );

		return orders;
	}

	@Override
	public List<OrderItem> getActiveOrderItems() {
		OrderFilter filter = new OrderFilter();
		filter.status = Order.Status.OPEN;

		List<OrderItem> orderItems = orderItemDao.find( filter );

		return orderItems;
	}

	/* (non-Javadoc)
	 * @see com.lunch4you.service.MenuService#getArticlesByCategories()
	 * Returns all Articles grouped by Categories. Used for generating menu.
	 */
	@Override
	public LinkedHashMap<Long, CategoryWithArticles> getArticlesByCategories(boolean activeOnly) {

		ArticleFilter filter = new ArticleFilter();
		if(activeOnly)
			filter.isActive = true;
		List<Article> articles = articleDao.find(filter );
		LinkedHashMap<Long, CategoryWithArticles> categoriesWithArticles = new LinkedHashMap<Long, CategoryWithArticles>();
		for(Article article : articles){
			Category category = article.getCategory();
			
			CategoryWithArticles categoryWithArticles = categoriesWithArticles.get(category.getId());
			if(categoryWithArticles == null){
				categoryWithArticles =  new CategoryWithArticles(); 
				categoryWithArticles.entity = category;
				categoriesWithArticles.put(category.getId(), categoryWithArticles);
			}
			categoryWithArticles.items.put(article.getId(), article);
		}			
		return categoriesWithArticles;
	}

	@Override
	public Article createOrUpdateArticle(Article article, long categoryId) {
		Long articleId = article.getId();
		Article target;
		if(articleId == null){
			target = new Article();
		} else {
			target = articleDao.load( article.getId());
		}
		Category category = categoryDao.load( categoryId );

		target.setName_cz( article.getName_cz());
		target.setName_en( article.getName_en());
		target.setDescription_cz( article.getDescription_cz());
		target.setDescription_en( article.getDescription_en());
		target.setPrice( article.getPrice() );
		target.setCategory( category );
		
		if(articleId == null){
			articleDao.insert( target );
		} else {
			articleDao.update( target );
		}
		
		return target;
		
	}

	@Override
	public Article setArticleActive(Long articleId, boolean active) {
		Article target = articleDao.load( articleId );
		
		target.setIsActive( active );
		
		articleDao.update( target );
		
		return target;
	}

	@Override
	public Article setArticleLimit(Long articleId, Integer limit) {
		Article target = articleDao.load( articleId );
		
		target.setDailyLimit( limit );
		
		articleDao.update( target );
		
		return target;
	}

	/* (non-Javadoc)
	 * @see com.lunch4you.service.MenuService#getActiveOrdersByArticle()
	 * 
	 * This method populates a list of Orders grouped into groups by Articles.
	 * Ordering is by Article's category, article name (CZ) and then by individual orders
	 */
	@Override
	public LinkedHashMap<Long,ArticleWithOrders> getActiveOrdersByArticle() {

		/*
		 * The implementation is quite complicated, because of ordering requirements.
		 */

		List<Article> allArticles = articleDao.loadAll();
		LinkedHashMap<Long, ArticleWithOrders> articlesWithOrders = new LinkedHashMap<Long, ArticleWithOrders>();
		
		// first populate final map with all articles (ordered by category), so that final ordering is by category
		for(Article article : allArticles){
			
			ArticleWithOrders articleWithOrders = articlesWithOrders.get(article.getId());
			if(articleWithOrders == null){
				articleWithOrders =  new ArticleWithOrders(); 
				articleWithOrders.entity = article;
				articlesWithOrders.put(article.getId(), articleWithOrders);
			}
		}
		
		// populate the structure with the actual orders
		List<Order> orders = getActiveOrders();
		for(Order order : orders){
			Article article = order.getItems().get(0).getArticle();
			
			ArticleWithOrders articleWithOrders = articlesWithOrders.get(article.getId());
			articleWithOrders.items.put(order.getId(), order);
		}
		
		// remove any empty Article groups (with no orders)
		Collection<ArticleWithOrders> temp = new ArrayList<ArticleWithOrders>();
		// need to create a new temporary collection for future iteration to prevent concurrent modification of linked hash set
		temp.addAll(articlesWithOrders.values());
		for(ArticleWithOrders articleWithOrders : temp){
			
			boolean empty = articleWithOrders.getItems().isEmpty();
			if(empty){
				articlesWithOrders.remove(articleWithOrders.entity.getId());
			}
		}

		return articlesWithOrders;
	}

	@Override
	public LinkedHashMap<Long,DeliveryLocationWithArticles> getActiveOrdersByDeliveryLocation() {

		List<OrderItem> orderItems = getActiveOrderItems();
		
		/*
		 * Creates a compelte structure of groups. Structure is being formed during iteration
		 * over all orders and creating Location or Article groups on-the-fly
		 */
		LinkedHashMap<Long, DeliveryLocationWithArticles> locationsWithArticles = new LinkedHashMap<Long, DeliveryLocationWithArticles>();
		for(OrderItem orderItem : orderItems){
			Order order = orderItem.getOrder();
			DeliveryLocation location = order.getDeliveryLocation();
			Article article = orderItem.getArticle();
			
			DeliveryLocationWithArticles locationWithArticles = locationsWithArticles.get(location.getId());
			if(locationWithArticles == null){
				locationWithArticles =  new DeliveryLocationWithArticles(); 
				locationsWithArticles.put(location.getId(), locationWithArticles);
			}
			locationWithArticles.entity = location;
			
			// increase count of OrderItems in this group
			locationWithArticles.countOrderItems ++;
			
			ArticleWithOrders articleWithOrders = locationWithArticles.items.get(article.getId());
			if(articleWithOrders == null){
				articleWithOrders =  new ArticleWithOrders(); 
				locationWithArticles.items.put(article.getId(), articleWithOrders);
			}
			articleWithOrders.entity = article;
			
			articleWithOrders.items.put(order.getId(), order);			
		}
		
		return locationsWithArticles;
	}

	@Override
	public List<Map<String,Object>> sendMenu( ) {

		LinkedHashMap<Long,CategoryWithArticles> groupedMenu = getArticlesByCategories(true);

		List<Customer> customers = getSubscribedCustomers(true, null);
		
		List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();

		for ( Customer customer : customers ) {
			Map<String, Object> resultElement = new HashMap<String, Object>();
			results.add(resultElement);
			resultElement.put("customer", customer);
			try{
				mailingService.sendMenu( customer, groupedMenu );
				resultElement.put("result", "success" );
			}catch (Exception e) {
				e.printStackTrace();
				resultElement.put("result", e.toString() );
			}
		}
		
		return results;
	}

	@Override
	public Referral createReferral(long senderId, long deliveryLocationId,
			String recipientEmail, String referralMessage) {
		
		Customer sender = getCustomer(senderId);
		
		String recipientName = recipientEmail.substring(0, recipientEmail.indexOf("@"));
		// Find out if customer with this email already exists
		CustomerFilter customerFilter = new CustomerFilter();
		customerFilter.email = recipientEmail;
		List<Customer> recipients = customerDao.find(customerFilter);
		Customer recipient;
		if(recipients.size() == 0){
			// Customer does not exist yet, create a new one
			recipient = registerCustomer(recipientName, "", recipientEmail, deliveryLocationId, true);
		}else{
			recipient = recipients.get(0);
		}


		Referral referral = new Referral();
		referral.setSender(sender);
		referral.setRecipient(recipient);
		referral.setReferralMessage( referralMessage );
		
		referralDao.insert( referral );
		
		mailingService.sendReferral(referral);
		
		mailingService.sendMenu(recipient, getArticlesByCategories(true));

		// TODO subscribe new customer
		
		return referral;
	}

	@Override
	public List<Long> closeOrders( List<Long> ids ) {
		OrderFilter filter = new OrderFilter();
		filter.status = Order.Status.OPEN;
		filter.ids = ids;
		List<Order> orders = orderDao.find( filter );

		for ( Order o : orders ) {
			ids.remove( o.getId() );
			o.setStatus( Order.Status.CLOSED );
		}
		return ids;
	}

	@Override
	public void deleteOrders( List<Long> ids ) {
		OrderFilter filter = new OrderFilter();
		filter.ids = ids;
		List<Order> orders = orderDao.find( filter );

		for ( Order o : orders ) {
			orderDao.delete(o);
		}
	}

	private String createToken(String base) {
		
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			byte[] data = base.getBytes(); 
		    m.update(data,0,data.length);
		    return new BigInteger(1,m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
	}

}

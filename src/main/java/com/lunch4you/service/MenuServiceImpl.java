package com.lunch4you.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.security.core.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunch4you.dao.ArticleDao;
import com.lunch4you.dao.CategoryDao;
import com.lunch4you.dao.CustomerDao;
import com.lunch4you.dao.DeliveryLocationDao;
import com.lunch4you.dao.OrderDao;
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
	public Customer registerCustomer( String firstName, String lastName, String email, Long defaultDeliveryLocationId ) {
		
		DeliveryLocation ddl = deliveryLocationDao.load( defaultDeliveryLocationId );
		
		Customer newCustomer = new Customer();
		String token = createToken();

		newCustomer.setCredit( 0 );
		newCustomer.setEmail( email );
		newCustomer.setFirstName( firstName );
		newCustomer.setLastName( lastName );
		newCustomer.setToken( token );
		newCustomer.setDefaultDeliveryLocation( ddl );
		return customerDao.insert( newCustomer );
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
	public Order createOrder( Long articleId, Long customerId, Long deliveryLocationId ) {
		OrderItem item = new OrderItem();
		Article article = articleDao.load( articleId );
		Customer customer = getCustomer( customerId );
		DeliveryLocation deliveryLocation = deliveryLocationDao.load( deliveryLocationId );
		item.setAmount( 1 );
		item.setArticle( article );

		Order newOrder = new Order();

		newOrder.setOwner( customer );
		newOrder.setDeliveryLocation( deliveryLocation );
		newOrder.setStatus( Order.Status.OPEN );
		newOrder.setItems( Collections.singletonList( item ) );

		Order order = orderDao.insert( newOrder );

		try {
			mailingService.sendOrderConfirmation( order );
		} catch ( MailSendException e ) {
			logger.error( "Confirmation e-mail sending failed!", e );
		}

		return order;
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

	/* (non-Javadoc)
	 * @see com.lunch4you.service.MenuService#getArticlesByCategories()
	 * Returns all Articles grouped by Categories. Used for generating menu.
	 */
	@Override
	public LinkedHashMap<Long, CategoryWithArticles> getArticlesByCategories() {

		List<Article> articles = articleDao.loadAll();
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
	public LinkedHashMap<Long,ArticleWithOrders> getActiveOrdersByArticle() {
		
		List<Order> orders = getActiveOrders();
		LinkedHashMap<Long, ArticleWithOrders> articlesWithOrders = new LinkedHashMap<Long, ArticleWithOrders>();
		for(Order order : orders){
			Article article = order.getItems().get(0).getArticle();
			
			ArticleWithOrders articleWithOrders = articlesWithOrders.get(article.getId());
			if(articleWithOrders == null){
				articleWithOrders =  new ArticleWithOrders(); 
				articleWithOrders.entity = article;
				articlesWithOrders.put(article.getId(), articleWithOrders);
			}
			articleWithOrders.items.put(order.getId(), order);
		}
						
		return articlesWithOrders;
	}

	@Override
	public LinkedHashMap<Long,DeliveryLocationWithArticles> getActiveOrdersByDeliveryLocation() {

		List<Order> orders = getActiveOrders();
		
		/*
		 * Creates a compelte structure of groups. Structure is being formed during iteration
		 * over all orders and creating Location or Article groups on-the-fly
		 */
		LinkedHashMap<Long, DeliveryLocationWithArticles> locationsWithArticles = new LinkedHashMap<Long, DeliveryLocationWithArticles>();
		for(Order order : orders){
			DeliveryLocation location = order.getDeliveryLocation();
			Article article = order.getItems().get(0).getArticle();
			
			DeliveryLocationWithArticles locationWithArticles = locationsWithArticles.get(location.getId());
			if(locationWithArticles == null){
				locationWithArticles =  new DeliveryLocationWithArticles(); 
				locationsWithArticles.put(location.getId(), locationWithArticles);
			}
			locationWithArticles.entity = location;
			
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

	private String createToken() {
		byte[] random = new byte[ 12 ];

		for ( int i = 0; i < random.length; i++ ) {
			random[ i ] = (byte) (Math.random() * 256 - 128);
		}

		byte[] encoded = Base64.encode( random  );

		return new String( encoded );
	}
}

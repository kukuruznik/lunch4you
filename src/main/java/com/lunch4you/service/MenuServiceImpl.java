package com.lunch4you.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.lunch4you.domain.Customer;
import com.lunch4you.domain.DeliveryLocation;
import com.lunch4you.domain.DeliveryLocationWithArticles;
import com.lunch4you.domain.Group;
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
	public List<Map<String, Object>> getGroupedMenu() {
		
		// 1st step - split all articles into lists by categoryId (one list per category)
		List<Article> articles = articleDao.loadAll();
		Map<Long, List<Article>> groups = new HashMap<Long, List<Article>>();
		for(Article article : articles){
			Long catId = article.getCategory().getId();
			List<Article> list = groups.get(catId);
			if(list == null){
				list = new ArrayList<Article>();
				groups.put(catId, list);
			}
			list.add(article);
		}

		// 2nd step - create pairs (tuples) for category and respective articles list		
		List<Map<String,Object>> grMenu = new ArrayList<Map<String, Object>>();
		List<Category> categories = categoryDao.loadAll();
		for(Category category : categories){
			Map<String, Object> groupMap = new HashMap<String, Object>();
			groupMap.put("category", category);
			groupMap.put("articles", groups.get(category.getId()));
			grMenu.add(groupMap);
		}
				
		return grMenu;
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
	public List<ArticleWithOrders> getActiveOrdersByArticle() {
		
		// 1st step - split all orders into lists by ArticleId (one list per article)
		List<Order> orders = getActiveOrders();
		Map<Long, List<Order>> groups = new HashMap<Long, List<Order>>();
		for(Order order : orders){
			Long artId = order.getItems().get(0).getArticle().getId();
			List<Order> list = groups.get(artId);
			if(list == null){
				list = new ArrayList<Order>();
				groups.put(artId, list);
			}
			list.add(order);
		}

		// 2nd step - create pairs (tuples) for Article and respective orders list		
		List<ArticleWithOrders> articlesWithOrders = new ArrayList<ArticleWithOrders>();
		List<Article> articles = articleDao.loadAll();
		for(Article article : articles){
			ArticleWithOrders group = new ArticleWithOrders();
			group.entity = article;
			//group.items.addAll( groups.get(article.getId()) );
			articlesWithOrders.add(group);
		}
				
		return articlesWithOrders;
	}

	@Override
	public List<DeliveryLocationWithArticles> getActiveOrdersByDeliveryLocation() {

		List<Order> allOrders = getActiveOrders();
		
		// prepare map of all Articles searchable by Id
		List<Article> allArticles = articleDao.loadAll();
		Map<Long, Article> allArticlesById = new HashMap<Long, Article>();
		for(Article article : allArticles){
			allArticlesById.put(article.getId(), article);
		}

		// prepare map of all DeliveryLocations searchable by Id
		List<DeliveryLocation> allLocations = deliveryLocationDao.loadAll();
		Map<Long, DeliveryLocation> allLocationsById = new HashMap<Long, DeliveryLocation>();
		for(DeliveryLocation location : allLocations){
			allLocationsById.put(location.getId(), location);
		}

		// 1st step - split all orders into lists by LocationId (one list per DeliveryLocation)
		Map<Long, List<Order>> ordersByLocationId = new HashMap<Long, List<Order>>();
		for(Order order : allOrders){
			Long locId = order.getDeliveryLocation().getId();
			List<Order> list = ordersByLocationId.get(locId);
			if(list == null){
				list = new ArrayList<Order>();
				ordersByLocationId.put(locId, list);
			}
			list.add(order);
		}

		// this map will contain further maps of oreders split by ArticleId
		Map<Long, Map<Long, List<Order>>> ordersByLocationId2 = new HashMap<Long, Map<Long,List<Order>>>();
		for(Long locationId : ordersByLocationId.keySet()){
			List<Order> ordersInLocation = ordersByLocationId.get(locationId);
			Map<Long, List<Order>> ordersByArticleId = new HashMap<Long, List<Order>>();
			for(Order order : ordersInLocation){
				Long artId = order.getItems().get(0).getArticle().getId();
				// select appropriate list for current article,if not exist, then create one
				List<Order> list = ordersByArticleId.get(artId);
				if(list == null){
					list = new ArrayList<Order>();
					ordersByArticleId.put(artId, list);
				}
				list.add(order);
			}
			ordersByLocationId2.put(locationId, ordersByArticleId);
		}
		
		// simple printing
		String out = "";
		List<DeliveryLocationWithArticles> locationsWithArticles = new ArrayList<DeliveryLocationWithArticles>();
		for(Long locationId : ordersByLocationId2.keySet()){
			out += "\n LocationId: " + locationId;
			DeliveryLocationWithArticles locationWithArticles = new DeliveryLocationWithArticles();
			locationWithArticles.entity = allLocationsById.get(locationId);
			Map<Long, List<Order>> ordersInLocation = ordersByLocationId2.get(locationId);
			for(Long articleId : ordersInLocation.keySet()){
				out += "\n  ArticleId: " + articleId;
				List<Order> orders = ordersInLocation.get(articleId);
				ArticleWithOrders articleWithOrders = new ArticleWithOrders();
				articleWithOrders.entity = allArticlesById.get(articleId);
				for(Order order : orders){
					out += "\n   OrderId: " + order.getId();
					articleWithOrders.items.put(order.getId(), order);
				}
				locationWithArticles.items.put(articleId, articleWithOrders);
			}
			locationsWithArticles.add(locationWithArticles);
		}

		// 2nd step - create pairs (tuples) for Article and respective orders list		
		
//		List<Article> articles = articleDao.loadAll();
//		for(Article article : articles){
//			ArticleWithOrders group = new ArticleWithOrders();
//			group.entity = article;
//			group.items.addAll( groups.get(article.getId()) );
//			locationsWithArticles.add(group);
//		}

		
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

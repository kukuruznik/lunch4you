package com.lunch4you.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lunch4you.dao.CustomerDao;
import com.lunch4you.domain.Customer;

@Service
public class AuthenticationServiceImpl implements UserDetailsService {

	@Autowired
	CustomerDao customerDao;

	@Override
	public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Long customerId = null;

		try {
			customerId = Long.parseLong( username );
			Customer customer = customerDao.load( customerId );

			if ( customer == null )
				throw new UsernameNotFoundException( "Client with ID = " + username + " does not exist!" );

			authorities.add( new GrantedAuthorityImpl( "ROLE_USER" ) );
			return new User( username, customer.getToken(), authorities );

		} catch ( NumberFormatException e ) {

			// not a client: continue with authentication of an administrator
			if ( !"kuchar".equals( username ) )
				throw new UsernameNotFoundException( "Unknown user '" + username + "'!" );
			
			authorities.add( new GrantedAuthorityImpl( "ROLE_ADMIN" ) );
			return new User( "kuchar", "szakacs", authorities );
		}
	}
}

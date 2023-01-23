package com.jane.buyherefrontend.security;


import com.jane.buyherecommon.entity.AuthenticationType;
import com.jane.buyherecommon.entity.Customer;
import com.jane.buyherefrontend.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DatabaseLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseLoginSuccessHandler.class);
	
	@Autowired 
	private CustomerService customerService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		CustomerUserDetails userDetails = (CustomerUserDetails) authentication.getPrincipal();
		Customer customer = userDetails.getCustomer();
		
		LOGGER.info("DatabaseLoginSuccessHandler | onAuthenticationSuccess |  customer : " + customer.toString());

		customerService.updateAuthenticationType(customer, AuthenticationType.DATABASE);

		super.onAuthenticationSuccess(request, response, authentication);
	}

}

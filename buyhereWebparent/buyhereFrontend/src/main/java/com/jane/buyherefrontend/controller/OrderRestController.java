package com.jane.buyherefrontend.controller;


import com.jane.buyherecommon.entity.Customer;
import com.jane.buyherefrontend.error.OrderNotFoundException;
import com.jane.buyherefrontend.service.CustomerService;
import com.jane.buyherefrontend.service.OrderService;
import com.jane.buyherefrontend.util.AuthenticationControllerHelperUtil;
import com.jane.buyherefrontend.util.OrderReturnRequest;
import com.jane.buyherefrontend.util.OrderReturnResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class OrderRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderRestController.class);

	private OrderService orderService;
	private AuthenticationControllerHelperUtil authenticationControllerHelperUtil;
	
	@Autowired 
	public OrderRestController(OrderService orderService, 
			                   CustomerService customerService,
			                   AuthenticationControllerHelperUtil authenticationControllerHelperUtil) {
		super();
		this.orderService = orderService;
		this.authenticationControllerHelperUtil = authenticationControllerHelperUtil;
	}

	@PostMapping("/orders/return")
	public ResponseEntity<?> handleOrderReturnRequest(@RequestBody OrderReturnRequest returnRequest,
			HttpServletRequest servletRequest) {
		
		LOGGER.info("OrderService | handleOrderReturnRequest is called");
		
		LOGGER.info("OrderService | handleOrderReturnRequest | Order ID: " + returnRequest.getOrderId());
		LOGGER.info("OrderService | handleOrderReturnRequest | Reason : " + returnRequest.getReason());
		LOGGER.info("OrderService | handleOrderReturnRequest | Note : " + returnRequest.getNote());

		
		Customer customer = authenticationControllerHelperUtil.getAuthenticatedCustomer(servletRequest);
		
		if(customer != null) {
			LOGGER.info("OrderService | handleOrderReturnRequest | customer : " + customer.toString());
		}else {
			
			LOGGER.info("OrderService | handleOrderReturnRequest | CustomerNotFoundException , Authentication required");
			
			return new ResponseEntity<>("Authentication required", HttpStatus.BAD_REQUEST);
		}

		
		try {
			
			orderService.setOrderReturnRequested(returnRequest, customer);
			
			LOGGER.info("OrderService | handleOrderReturnRequest | orderService.setOrderReturnRequested implemented");
			
		} catch (OrderNotFoundException ex) {
			
			LOGGER.info("OrderService | handleOrderReturnRequest | OrderNotFoundException : error : " + ex.getMessage());
			
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		LOGGER.info("OrderService | handleOrderReturnRequest | returnRequest.getOrderId() : " + returnRequest.getOrderId());

		return new ResponseEntity<>(new OrderReturnResponse(returnRequest.getOrderId()), HttpStatus.OK);
	}

}

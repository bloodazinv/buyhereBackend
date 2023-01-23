package com.jane.buyherebackend.controller;


import com.jane.buyherebackend.dto.OrderResponseDTO;
import com.jane.buyherebackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {

	@Autowired
	private OrderService service;

	@PostMapping("/orders_shipper/update/{id}/{status}")
	public OrderResponseDTO updateOrderStatus(@PathVariable("id") Integer orderId,
											  @PathVariable("status") String status) {
		service.updateStatus(orderId, status);
		return new OrderResponseDTO(orderId, status);
	}
}
package com.jane.buyherecommon.entity;


import com.jane.buyherecommon.entity.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart_items")
@Data
@NoArgsConstructor
public class CartItem extends IdBasedEntity implements Serializable{

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "product_id")	
	private Product product;

	private int quantity;
	
	@Transient
	private float shippingCost;
	
	@Transient
	public float getSubtotal() {
		return product.getDiscountPrice() * quantity;
	}
}

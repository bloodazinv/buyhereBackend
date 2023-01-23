package com.jane.buyherefrontend.util;



import com.jane.buyherecommon.entity.CartItem;
import com.jane.buyherecommon.entity.ShippingRate;
import com.jane.buyherecommon.entity.product.Product;

import java.util.List;

public class CheckoutUtil {

	public static float calculateShippingCost(List<CartItem> cartItems, ShippingRate shippingRate, int division) {
		float shippingCostTotal = 0.0f;

		for (CartItem item : cartItems) {
			Product product = item.getProduct();
			float dimWeight = (product.getLength() * product.getWidth() * product.getHeight()) / division;
			float finalWeight = product.getWeight() > dimWeight ? product.getWeight() : dimWeight;
			float shippingCost = finalWeight * item.getQuantity() * shippingRate.getRate();

			item.setShippingCost(shippingCost);

			shippingCostTotal += shippingCost;
		}

		return shippingCostTotal;
	}

	public static float calculateProductTotal(List<CartItem> cartItems) {
		float total = 0.0f;

		for (CartItem item : cartItems) {
			total += item.getSubtotal();
		}

		return total;
	}

	public static float calculateProductCost(List<CartItem> cartItems) {
		float cost = 0.0f;

		for (CartItem item : cartItems) {
			cost += item.getQuantity() * item.getProduct().getCost();
		}

		return cost;
	}
}

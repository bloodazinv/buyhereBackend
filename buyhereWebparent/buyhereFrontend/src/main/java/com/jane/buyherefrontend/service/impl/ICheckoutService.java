/**
 * FileName: ICheckoutService
 * Author: jane
 * Date: 2023/1/21 15:43
 * Description:
 * Version:
 */
package com.jane.buyherefrontend.service.impl;

import com.jane.buyherecommon.entity.CartItem;
import com.jane.buyherecommon.entity.ShippingRate;
import com.jane.buyherefrontend.checkout.CheckoutInfo;

import java.util.List;

public interface ICheckoutService {
    public CheckoutInfo prepareCheckout(List<CartItem> cartItems, ShippingRate shippingRate);
}

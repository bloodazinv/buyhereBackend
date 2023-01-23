/**
 * FileName: IShoppingCartService
 * Author: jane
 * Date: 2023/1/18 12:12
 * Description:
 * Version:
 */
package com.jane.buyherefrontend.service.impl;

import com.jane.buyherecommon.entity.CartItem;
import com.jane.buyherecommon.entity.Customer;
import com.jane.buyherecommon.exception.ShoppingCartException;

import java.util.List;

public interface IShoppingCartService {

    public Integer addProduct(Integer productId, Integer quantity, Customer customer)
            throws ShoppingCartException;

    public List<CartItem> listCartItems(Customer customer);

    public float updateQuantity(Integer productId, Integer quantity, Customer customer);

    public void removeProduct(Integer productId, Customer customer);

    public void deleteByCustomer(Customer customer);
}

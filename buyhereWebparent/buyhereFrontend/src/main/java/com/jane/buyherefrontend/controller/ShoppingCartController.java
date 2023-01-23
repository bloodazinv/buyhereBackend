/**
 * FileName: ShoppingCartController
 * Author: jane
 * Date: 2023/1/18 12:11
 * Description:
 * Version:
 */

package com.jane.buyherefrontend.controller;

import com.jane.buyherecommon.entity.Address;
import com.jane.buyherecommon.entity.CartItem;
import com.jane.buyherecommon.entity.Customer;
import com.jane.buyherecommon.entity.ShippingRate;
import com.jane.buyherecommon.exception.CustomerNotFoundException;
import com.jane.buyherefrontend.service.AddressService;
import com.jane.buyherefrontend.service.CustomerService;
import com.jane.buyherefrontend.service.ShippingRateService;
import com.jane.buyherefrontend.service.ShoppingCartService;
import com.jane.buyherefrontend.util.AuthenticationControllerHelperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ShoppingCartController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartController.class);

    private ShoppingCartService cartService;

    private AddressService addressService;

    private ShippingRateService shipService;

    private AuthenticationControllerHelperUtil authenticationControllerHelperUtil;

    @Autowired
    public ShoppingCartController(CustomerService customerService, ShoppingCartService cartService,
                                  AddressService addressService, ShippingRateService shipService,
                                  AuthenticationControllerHelperUtil authenticationControllerHelperUtil) {

        super();
        this.cartService = cartService;
        this.addressService = addressService;
        this.shipService = shipService;
        this.authenticationControllerHelperUtil = authenticationControllerHelperUtil;
    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpServletRequest request) throws CustomerNotFoundException {

        LOGGER.info("ShoppingCartController | viewCart is called");

        Customer customer = authenticationControllerHelperUtil.getAuthenticatedCustomer(request);
        List<CartItem> cartItems = cartService.listCartItems(customer);

        LOGGER.info("ShoppingCartController | viewCart | customer : " + customer.toString());
        LOGGER.info("ShoppingCartController | viewCart | cartItems : " + cartItems.size());

        float estimatedTotal = 0.0F;

        for (CartItem item : cartItems) {
            LOGGER.info("ShoppingCartController | viewCart | item.getSubtotal() : " + item.getSubtotal());
            estimatedTotal += item.getSubtotal();
        }

        Address defaultAddress = addressService.getDefaultAddress(customer);

        ShippingRate shippingRate = null;
        boolean usePrimaryAddressAsDefault = false;

        if (defaultAddress != null) {
            shippingRate = shipService.getShippingRateForAddress(defaultAddress);
        } else {
            usePrimaryAddressAsDefault = true;
            shippingRate = shipService.getShippingRateForCustomer(customer);
        }

        LOGGER.info("ShoppingCartController | viewCart | usePrimaryAddressAsDefault : " + usePrimaryAddressAsDefault);
        LOGGER.info("ShoppingCartController | viewCart | shippingSupported : " + (shippingRate != null));

        model.addAttribute("usePrimaryAddressAsDefault", usePrimaryAddressAsDefault);
        model.addAttribute("shippingSupported", shippingRate != null);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("estimatedTotal", estimatedTotal);

        LOGGER.info("ShoppingCartController | viewCart | estimatedTotal : " + estimatedTotal);

        return "cart/shopping_cart";
    }


}

/**
 * FileName: AuthenticationControllerHelperUtil
 * Author: jane
 * Date: 2023/1/18 12:30
 * Description:
 * Version:
 */

package com.jane.buyherefrontend.util;

import com.jane.buyherecommon.entity.Customer;
import com.jane.buyherefrontend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthenticationControllerHelperUtil {

    @Autowired
    private CustomerService customerService;

    public Customer getAuthenticatedCustomer(HttpServletRequest request) {
        String email = "janemark@gmail.com";
        return customerService.getCustomerByEmail(email);
    }
}

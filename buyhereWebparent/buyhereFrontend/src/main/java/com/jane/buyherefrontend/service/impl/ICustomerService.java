/**
 * FileName: ICustomerService
 * Author: jane
 * Date: 2023/1/17 13:40
 * Description:
 * Version:
 */
package com.jane.buyherefrontend.service.impl;

import com.jane.buyherecommon.entity.AuthenticationType;
import com.jane.buyherecommon.entity.Country;
import com.jane.buyherecommon.entity.Customer;
import com.jane.buyherecommon.exception.CustomerNotFoundException;

import java.util.List;

public interface ICustomerService {
    public List<Country> listAllCountries();
    public boolean isEmailUnique(String email);
    public void registerCustomer(Customer customer);
    public boolean verify(String verificationCode);
    public Customer getCustomerByEmail(String email);
    public void updateAuthenticationType(Customer customer, AuthenticationType type);
    public void addNewCustomerUponOAuthLogin(String name, String email, String countryCode,
                                             AuthenticationType authenticationType);
    public void update(Customer customerInForm);
    public String updateResetPasswordToken(String email) throws CustomerNotFoundException;
    public Customer getByResetPasswordToken(String token);
    public void updatePassword(String token, String newPassword) throws CustomerNotFoundException;
}

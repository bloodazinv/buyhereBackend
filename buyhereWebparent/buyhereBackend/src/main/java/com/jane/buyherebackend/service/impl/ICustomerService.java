/**
 * FileName: ICustomerService
 * Author: jane
 * Date: 2023/1/17 22:18
 * Description:
 * Version:
 */
package com.jane.buyherebackend.service.impl;

import com.jane.buyherebackend.paging.PagingAndSortingHelper;
import com.jane.buyherecommon.entity.Country;
import com.jane.buyherecommon.entity.Customer;
import com.jane.buyherecommon.exception.CustomerNotFoundException;

import java.util.List;

public interface ICustomerService {
    public void listByPage(int pageNum, PagingAndSortingHelper helper);
    public void updateCustomerEnabledStatus(Integer id, boolean enabled);
    public List<Country> listAllCountries();
    public boolean isEmailUnique(Integer id, String email);
    public void save(Customer customerInForm);
    public void delete(Integer id) throws CustomerNotFoundException;
    public Customer get(Integer id) throws CustomerNotFoundException;
    List<Customer> listAll();
}

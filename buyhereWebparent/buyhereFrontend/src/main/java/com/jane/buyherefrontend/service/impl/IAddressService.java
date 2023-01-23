/**
 * FileName: IAddressService
 * Author: jane
 * Date: 2023/1/18 12:48
 * Description:
 * Version:
 */
package com.jane.buyherefrontend.service.impl;

import com.jane.buyherecommon.entity.Address;
import com.jane.buyherecommon.entity.Customer;

import java.util.List;

public interface IAddressService {

    public List<Address> listAddressBook(Customer customer);

    public void save(Address address);

    public Address get(Integer addressId, Integer customerId);

    public void delete(Integer addressId, Integer customerId);

    public void setDefaultAddress(Integer defaultAddressId, Integer customerId) ;

    public Address getDefaultAddress(Customer customer);
}

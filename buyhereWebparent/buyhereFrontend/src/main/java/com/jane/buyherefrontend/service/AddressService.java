/**
 * FileName: AddressService
 * Author: jane
 * Date: 2023/1/18 12:47
 * Description:
 * Version:
 */

package com.jane.buyherefrontend.service;

import com.jane.buyherecommon.entity.Address;
import com.jane.buyherecommon.entity.Customer;
import com.jane.buyherefrontend.repository.AddressRepository;
import com.jane.buyherefrontend.service.impl.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AddressService implements IAddressService {

    @Autowired
    private AddressRepository repo;

    @Override
    public List<Address> listAddressBook(Customer customer) {
        // TODO Auto-generated method stub
        return repo.findByCustomer(customer);
    }

    @Override
    public void save(Address address) {
        repo.save(address);
    }

    @Override
    public Address get(Integer addressId, Integer customerId) {
        return repo.findByIdAndCustomer(addressId, customerId);
    }

    @Override
    public void delete(Integer addressId, Integer customerId) {
        repo.deleteByIdAndCustomer(addressId, customerId);
    }

    @Override
    public void setDefaultAddress(Integer defaultAddressId, Integer customerId) {
        if (defaultAddressId > 0) {
            repo.setDefaultAddress(defaultAddressId);
        }

        repo.setNonDefaultForOthers(defaultAddressId, customerId);
    }

    @Override
    public Address getDefaultAddress(Customer customer) {
        return repo.findDefaultByCustomer(customer.getId());
    }

}

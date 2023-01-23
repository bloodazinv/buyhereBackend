/**
 * FileName: ShippingRateService
 * Author: jane
 * Date: 2023/1/18 12:49
 * Description:
 * Version:
 */

package com.jane.buyherefrontend.service;

import com.jane.buyherecommon.entity.Address;
import com.jane.buyherecommon.entity.Customer;
import com.jane.buyherecommon.entity.ShippingRate;
import com.jane.buyherefrontend.repository.ShippingRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ShippingRateService {

    @Autowired
    private ShippingRateRepository repo;

    public ShippingRate getShippingRateForCustomer(Customer customer) {
        String state = customer.getState();
        if (state == null || state.isEmpty()) {
            state = customer.getCity();
        }

        return repo.findByCountryAndState(customer.getCountry(), state);
    }

    public ShippingRate getShippingRateForAddress(Address address) {
        String state = address.getState();
        if (state == null || state.isEmpty()) {
            state = address.getCity();
        }

        return repo.findByCountryAndState(address.getCountry(), state);
    }
}

/**
 * FileName: ShippingRateRepository
 * Author: jane
 * Date: 2023/1/18 12:53
 * Description:
 * Version:
 */
package com.jane.buyherefrontend.repository;

import com.jane.buyherecommon.entity.Country;
import com.jane.buyherecommon.entity.ShippingRate;
import org.springframework.data.repository.CrudRepository;

public interface ShippingRateRepository extends CrudRepository<ShippingRate, Integer> {

    public ShippingRate findByCountryAndState(Country country, String state);

}

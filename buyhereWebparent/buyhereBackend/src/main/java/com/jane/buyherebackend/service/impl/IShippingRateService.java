/**
 * FileName: IShippingRateService
 * Author: jane
 * Date: 2023/1/20 17:18
 * Description:
 * Version:
 */
package com.jane.buyherebackend.service.impl;

import com.jane.buyherebackend.paging.PagingAndSortingHelper;
import com.jane.buyherecommon.entity.Country;
import com.jane.buyherecommon.entity.ShippingRate;
import com.jane.buyherecommon.exception.ShippingRateAlreadyExistsException;
import com.jane.buyherecommon.exception.ShippingRateNotFoundException;

import java.util.List;

public interface IShippingRateService {

    public void listByPage(int pageNum, PagingAndSortingHelper helper);
    public List<Country> listAllCountries();
    public void save(ShippingRate rateInForm) throws ShippingRateAlreadyExistsException;
    public ShippingRate get(Integer id) throws ShippingRateNotFoundException;
    public void updateCODSupport(Integer id, boolean codSupported) throws ShippingRateNotFoundException;
    public void delete(Integer id) throws ShippingRateNotFoundException;
}

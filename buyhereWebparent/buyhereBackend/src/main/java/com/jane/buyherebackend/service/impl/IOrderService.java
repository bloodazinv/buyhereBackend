/**
 * FileName: IOrderService
 * Author: jane
 * Date: 2023/1/21 13:31
 * Description:
 * Version:
 */
package com.jane.buyherebackend.service.impl;

import com.jane.buyherebackend.error.OrderNotFoundException;
import com.jane.buyherebackend.paging.PagingAndSortingHelper;
import com.jane.buyherecommon.entity.order.Order;

public interface IOrderService {
    public void listByPage(int pageNum, PagingAndSortingHelper helper);

    public Order get(Integer id) throws OrderNotFoundException;

    public void delete(Integer id) throws OrderNotFoundException;
}

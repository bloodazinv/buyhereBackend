/**
 * FileName: IProductService
 * Author: jane
 * Date: 2023/1/11 14:51
 * Description:
 * Version:
 */
package com.jane.buyherebackend.service.impl;

import com.jane.buyherebackend.paging.PagingAndSortingHelper;
import com.jane.buyherecommon.entity.product.Product;
import com.jane.buyherecommon.exception.ProductNotFoundException;

import java.util.List;

public interface IProductService {

    public List<Product> listAll();

    public Product save(Product product);

    public String checkUnique(Integer id, String name);

    public void updateProductEnabledStatus(Integer id, boolean enabled);

    public void delete(Integer id) throws ProductNotFoundException;

    public Product get(Integer id) throws ProductNotFoundException;

    public void listByPage(int pageNum, PagingAndSortingHelper helper, Integer categoryId);

    public void saveProductPrice(Product productInForm);

}

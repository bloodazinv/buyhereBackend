/**
 * FileName: IProductService
 * Author: jane
 * Date: 2023/1/15 15:18
 * Description:
 * Version:
 */
package com.jane.buyherefrontend.service.impl;

import com.jane.buyherecommon.entity.product.Product;
import com.jane.buyherecommon.exception.ProductNotFoundException;
import org.springframework.data.domain.Page;

public interface IProductService {
    public Page<Product> listByCategory(int pageNum, Integer categoryId);

    public Product getProduct(String alias) throws ProductNotFoundException;

    public Page<Product> search(String keyword, int pageNum);

}

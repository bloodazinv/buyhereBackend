/**
 * FileName: IBrandService
 * Author: jane
 * Date: 2023/1/11 13:33
 * Description:
 * Version:
 */
package com.jane.buyherebackend.service.impl;

import com.jane.buyherebackend.error.BrandNotFoundException;
import com.jane.buyherebackend.paging.PagingAndSortingHelper;
import com.jane.buyherecommon.entity.Brand;

import java.util.List;

public interface IBrandService {
    public List<Brand> listAll();
    public Brand save(Brand brand);
    public Brand get(Integer id) throws BrandNotFoundException;
    public void delete(Integer id) throws BrandNotFoundException;
    public String checkUnique(Integer id, String name);
    public void listByPage(int pageNum, PagingAndSortingHelper helper);
}

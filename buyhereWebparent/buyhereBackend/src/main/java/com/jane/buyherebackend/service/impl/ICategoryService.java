/**
 * FileName: ICategoryService
 * Author: jane
 * Date: 2023/1/10 17:05
 * Description:
 * Version:
 */
package com.jane.buyherebackend.service.impl;

import com.jane.buyherebackend.util.CategoryPageInfo;
import com.jane.buyherecommon.entity.Category;
import com.jane.buyherecommon.exception.CategoryNotFoundException;

import java.util.List;

public interface ICategoryService {

    public List<Category> listByPage(CategoryPageInfo pageInfo, int pageNum, String sortDir,
                                     String keyword);

    public List<Category> listCategoriesUsedInForm();

    public Category save(Category category);

    public Category get(Integer id) throws CategoryNotFoundException;

    public String checkUnique(Integer id, String name, String alias);

    public void updateCategoryEnabledStatus(Integer id, boolean enabled);

    public void delete(Integer id) throws CategoryNotFoundException;
}

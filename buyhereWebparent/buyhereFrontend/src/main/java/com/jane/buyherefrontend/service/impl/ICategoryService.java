/**
 * FileName: ICategoryService
 * Author: jane
 * Date: 2023/1/14 16:10
 * Description:
 * Version:
 */
package com.jane.buyherefrontend.service.impl;

import com.jane.buyherecommon.entity.Category;
import com.jane.buyherecommon.exception.CategoryNotFoundException;

import java.util.List;

public interface ICategoryService {

    public List<Category> listNoChildrenCategories();

    public Category getCategory(String alias) throws CategoryNotFoundException;

    public List<Category> getCategoryParents(Category child);
}


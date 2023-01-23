/**
 * FileName: CategoryService
 * Author: jane
 * Date: 2023/1/14 16:08
 * Description:
 * Version:
 */

package com.jane.buyherefrontend.service;

import com.jane.buyherecommon.entity.Category;
import com.jane.buyherecommon.exception.CategoryNotFoundException;
import com.jane.buyherefrontend.repository.CategoryRepository;
import com.jane.buyherefrontend.service.impl.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        super();
        this.repo = repo;
    }

    @Override
    public List<Category> listNoChildrenCategories() {

        List<Category> listNoChildrenCategories = new ArrayList<>();

        List<Category> listEnabledCategories = repo.findAllEnabled();

        listEnabledCategories.forEach(category -> {
            Set<Category> children = category.getChildren();
            if (children == null || children.size() == 0) {
                listNoChildrenCategories.add(category);
            }
        });

        return listNoChildrenCategories;
    }

    @Override
    public Category getCategory(String alias) throws CategoryNotFoundException {
        Category category = repo.findByAliasEnabled(alias);
        if (category == null) {
            throw new CategoryNotFoundException("Could not find any categories with alias " + alias);
        }

        return category;
    }

    @Override
    public List<Category> getCategoryParents(Category child) {
        List<Category> listParents = new ArrayList<>();

        Category parent = child.getParent();

        while (parent != null) {
            listParents.add(0, parent);
            parent = parent.getParent();
        }

        listParents.add(child);

        return listParents;
    }

}


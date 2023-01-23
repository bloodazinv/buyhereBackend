/**
 * FileName: CategoryRestController
 * Author: jane
 * Date: 2023/1/10 17:16
 * Description:
 * Version:
 */

package com.jane.buyherebackend.controller;

import com.jane.buyherebackend.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CategoryRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService service;

    @PostMapping("/categories/check_unique")
    public String checkUnique(Integer id, String name,
                              String alias) {
        LOGGER.info("CategoryRestController | checkUnique is started");
        return service.checkUnique(id, name, alias);
    }
}


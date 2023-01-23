/**
 * FileName: MainController
 * Author: jane
 * Date: 2023/1/7 13:07
 * Description:
 * Version:
 */

package com.jane.buyherefrontend.controller;

import com.jane.buyherecommon.entity.Category;
import com.jane.buyherecommon.entity.section.Section;
import com.jane.buyherefrontend.service.CategoryService;
import com.jane.buyherefrontend.service.SectionService;
import com.jane.buyherefrontend.util.SectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private CategoryService categoryService;

    // @Autowired
    // private SectionService sectionService;


    @GetMapping("")
    public String viewHomePage(Model model) {

        LOGGER.info("MainController | viewHomePage is called");
        List<Category> categoryList = categoryService.listNoChildrenCategories();
        model.addAttribute("listCategories", categoryList);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LOGGER.info("MainController | viewLoginPage | authentication : " + auth.getName());

        return "index";
    }

    @GetMapping("/login")
    public String viewLoginPage() {

        LOGGER.info("MainController | viewLoginPage is called");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        LOGGER.info("MainController | viewLoginPage | authentication : " + authentication.toString());
        LOGGER.info("MainController | viewLoginPage | authentication instanceof AnonymousAuthenticationToken : " + (authentication instanceof AnonymousAuthenticationToken));

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }

        return "redirect:/";
    }



}

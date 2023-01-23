/**
 * FileName: CategorySectionController
 * Author: jane
 * Date: 2023/1/10 17:17
 * Description:
 * Version:
 */

package com.jane.buyherebackend.controller;

import com.jane.buyherebackend.service.CategoryService;
import com.jane.buyherecommon.entity.Category;
import com.jane.buyherecommon.exception.SectionNotFoundException;
import com.microsoft.schemas.office.visio.x2012.main.SectionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CategorySectionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategorySectionController.class);

    // private SectionService sectionService;
    //
    // private CategoryService categoryService;
    //
    // @Autowired
    // public CategorySectionController(SectionService sectionService, CategoryService categoryService) {
    //     super();
    //     this.sectionService = sectionService;
    //     this.categoryService = categoryService;
    // }
    //
    // @GetMapping("/sections/new/category")
    // public String showForm(Model model) {
    //
    //     LOGGER.info("CategorySectionController | showForm is called");
    //
    //     Section section = new Section(true, SectionType.CATEGORY);
    //
    //     List<Category> listCategories = categoryService.listAll();
    //
    //     LOGGER.info("CategorySectionController | showForm | listCategories size : " + listCategories.size());
    //     LOGGER.info("CategorySectionController | showForm | section : " + section.toString());
    //     LOGGER.info("CategorySectionController | showForm | pageTitle : " + "Add Category Section");
    //
    //     model.addAttribute("listCategories", listCategories);
    //     model.addAttribute("section", section);
    //     model.addAttribute("pageTitle", "Add Category Section");
    //
    //     return "sections/category_section_form";
    // }
    //
    // @PostMapping("/sections/save/category")
    // public String saveSection(Section section, HttpServletRequest request, RedirectAttributes ra) {
    //
    //     LOGGER.info("CategorySectionController | saveSection is called");
    //
    //     SectionUtil.addCategoriesToSection(section, request);
    //     sectionService.saveSection(section);
    //
    //     LOGGER.info("CategorySectionController | saveSection | messageSuccess : " + "The section of type Category has been saved successfully.");
    //
    //     ra.addFlashAttribute("messageSuccess", "The section of type Category has been saved successfully.");
    //
    //     return "redirect:/sections";
    // }
    //
    // @GetMapping("/sections/edit/Category/{id}")
    // public String editSection(@PathVariable(name = "id") Integer id, RedirectAttributes ra,
    //                           Model model) {
    //
    //     LOGGER.info("CategorySectionController | editSection is called");
    //
    //     try {
    //         Section section = sectionService.getSection(id);
    //         List<Category> listCategories = categoryService.listAll();
    //
    //         LOGGER.info("CategorySectionController | editSection | listCategories size : " + listCategories.size());
    //         LOGGER.info("CategorySectionController | editSection | section : " + section.toString());
    //         LOGGER.info("CategorySectionController | editSection | pageTitle : " + "Edit Category Section (ID: " + id + ")");
    //
    //         model.addAttribute("listCategories", listCategories);
    //         model.addAttribute("section", section);
    //         model.addAttribute("pageTitle", "Edit Category Section (ID: " + id + ")");
    //
    //         return "sections/category_section_form";
    //
    //     } catch (SectionNotFoundException ex) {
    //         LOGGER.info("CategorySectionController | editSection | messageError : " + ex.getMessage());
    //         ra.addFlashAttribute("messageError", ex.getMessage());
    //         return "redirect:/sections";
    //     }
    //
    // }
}

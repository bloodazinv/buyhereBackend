/**
 * FileName: CustomerController
 * Author: jane
 * Date: 2023/1/17 13:39
 * Description:
 * Version:
 */

package com.jane.buyherefrontend.controller;

import com.jane.buyherecommon.entity.Country;
import com.jane.buyherecommon.entity.Customer;
import com.jane.buyherefrontend.service.CustomerService;
import com.jane.buyherefrontend.service.SettingService;

import com.jane.buyherefrontend.util.CustomerAccountUtil;
import com.jane.buyherefrontend.util.CustomerRegisterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SettingService settingService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {

        LOGGER.info("CustomerController | showRegisterForm is called");

        List<Country> listCountries = customerService.listAllCountries();

        LOGGER.info("CustomerController | listCountries : " + listCountries.toString());

        model.addAttribute("listCountries", listCountries);
        model.addAttribute("pageTitle", "Customer Registration");
        model.addAttribute("customer", new Customer());

        return "register/register_form";
    }

    @PostMapping("/create_customer")
    public String createCustomer(Customer customer, Model model,
                                 HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {

        LOGGER.info("CustomerController | createCustomer is called");

        customerService.registerCustomer(customer);

        LOGGER.info("CustomerController | createCustomer | customer : " + customer);

        CustomerRegisterUtil.sendVerificationEmail(request, customer, settingService);

        model.addAttribute("pageTitle", "Registration Succeeded!");

        return "register/register_success";
    }

    @GetMapping("/verify")
    public String verifyAccount(@RequestParam("code") String code, Model model) {

        LOGGER.info("CustomerController | verifyAccount is called");

        boolean verified = customerService.verify(code);

        LOGGER.info("CustomerController | verifyAccount | verified : " + verified);

        return "register/" + (verified ? "verify_success" : "verify_fail");
    }

    @GetMapping("/account_details")
    public String viewAccountDetails(Model model, HttpServletRequest request) {

        LOGGER.info("CustomerController | viewAccountDetails is called");

        String email = CustomerAccountUtil.getEmailOfAuthenticatedCustomer(request);

        LOGGER.info("CustomerController | viewAccountDetails | email : " + email);

        Customer customer = customerService.getCustomerByEmail(email);

        LOGGER.info("CustomerController | viewAccountDetails | customer : " + customer.toString());

        List<Country> listCountries = customerService.listAllCountries();

        LOGGER.info("CustomerController | viewAccountDetails | listCountries : " + listCountries.size());

        model.addAttribute("customer", customer);
        model.addAttribute("listCountries", listCountries);

        return "customer/account_form";
    }

    @PostMapping("/update_account_details")
    public String updateAccountDetails(Model model, Customer customer, RedirectAttributes ra,
                                       HttpServletRequest request) {

        LOGGER.info("CustomerController | updateAccountDetails is called");
        LOGGER.info("CustomerController |" + customer.toString());
        customerService.update(customer);

        ra.addFlashAttribute("message", "Your account details have been updated.");

        CustomerAccountUtil.updateNameForAuthenticatedCustomer(customer, request);

        String redirectOption = request.getParameter("redirect");

        LOGGER.info("CustomerController | updateAccountDetails | redirectOption : " + redirectOption);

        String redirectURL = "redirect:/account_details";

        if ("address_book".equals(redirectOption)) {
            redirectURL = "redirect:/address_book";
        }else if ("cart".equals(redirectOption)) {
            redirectURL = "redirect:/cart";
        }else if ("checkout".equals(redirectOption)) {
            redirectURL = "redirect:/address_book?redirect=checkout";
        }

        LOGGER.info("CustomerController | updateAccountDetails | redirectURL : " + redirectURL);

        return redirectURL;
    }
}

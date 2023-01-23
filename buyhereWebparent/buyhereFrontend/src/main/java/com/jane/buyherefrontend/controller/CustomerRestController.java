/**
 * FileName: CustomerRestController
 * Author: jane
 * Date: 2023/1/17 13:50
 * Description:
 * Version:
 */

package com.jane.buyherefrontend.controller;

import com.jane.buyherefrontend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {

    @Autowired
    private CustomerService service;

    @PostMapping("/customers/check_unique_email")
    public String checkDuplicateEmail(@RequestParam("email") String email) {
        return service.isEmailUnique(email) ? "OK" : "Duplicated";
    }
}

/**
 * FileName: UserRestController
 * Author: jane
 * Date: 2023/1/8 18:52
 * Description:
 * Version:
 */

package com.jane.buyherebackend.controller;

import com.jane.buyherebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    private UserService service;

    @PostMapping("/users/check_email")
    public String checkDuplicateEmail(Integer id, String email) {
        return service.isEmailUnique(id, email) ? "OK" : "Duplicated";
    }
}

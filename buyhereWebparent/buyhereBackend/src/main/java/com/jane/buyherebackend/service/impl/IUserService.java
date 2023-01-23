/**
 * FileName: IUserService
 * Author: jane
 * Date: 2023/1/8 12:43
 * Description:
 * Version:
 */
package com.jane.buyherebackend.service.impl;

import com.jane.buyherebackend.error.UserNotFoundException;
import com.jane.buyherebackend.paging.PagingAndSortingHelper;
import com.jane.buyherecommon.entity.Role;
import com.jane.buyherecommon.entity.User;

import java.util.List;

public interface IUserService {
    public List<User> listAll();

    public List<Role> listRoles();

    public User save(User user);

    public boolean isEmailUnique(Integer id, String email);

    public User get(Integer id) throws UserNotFoundException;

    public void delete(Integer id) throws UserNotFoundException;

    public void updateUserEnabledStatus(Integer id, boolean enabled);

    public void listByPage(int pageNum, PagingAndSortingHelper helper);

    public User getByEmail(String email);

    public User updateAccount(User userInForm);
}

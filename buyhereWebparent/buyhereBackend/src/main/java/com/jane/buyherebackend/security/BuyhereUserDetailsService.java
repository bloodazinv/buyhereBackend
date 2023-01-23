/**
 * FileName: BuyhereUserDetailsService
 * Author: jane
 * Date: 2023/1/10 12:48
 * Description:
 * Version:
 */

package com.jane.buyherebackend.security;

import com.jane.buyherebackend.repository.UserRepository;
import com.jane.buyherecommon.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class BuyhereUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = userRepo.getUserByEmail(email);
        if (user != null) {
            return new BuyhereUserDetails(user);
        }

        throw new UsernameNotFoundException("Could not find user with email: " + email);
    }

}

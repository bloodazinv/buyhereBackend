/**
 * FileName: BuyhereUserDetails
 * Author: jane
 * Date: 2023/1/10 12:46
 * Description:
 * Version:
 */

package com.jane.buyherebackend.security;

import com.jane.buyherecommon.entity.Role;
import com.jane.buyherecommon.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class BuyhereUserDetails implements UserDetails {


    private static final long serialVersionUID = 1L;

    private User user;


    public BuyhereUserDetails(User user) {
        super();
        this.user = user;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        Set<Role> roles = user.getRoles();

        List<SimpleGrantedAuthority> authories = new ArrayList<>();

        for (Role role : roles) {
            authories.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authories;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return user.getPassword();
    }


    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    public String getFullname() {
        return this.user.getFirstName() + " " + this.user.getLastName();
    }

    public void setFirstName(String firstName) {
        this.user.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        this.user.setLastName(lastName);
    }

    public boolean hasRole(String roleName) {
        return user.hasRole(roleName);
    }

    @Override
    public String toString() {
        return "ShopmeUserDetails [user=" + user + "]";
    }

    public User getUser() {
        return user;
    }
}

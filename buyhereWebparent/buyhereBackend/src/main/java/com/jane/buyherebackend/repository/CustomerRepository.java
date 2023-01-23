/**
 * FileName: CustomerRepository
 * Author: jane
 * Date: 2023/1/17 22:19
 * Description:
 * Version:
 */
package com.jane.buyherebackend.repository;

import com.jane.buyherebackend.paging.SearchRepository;
import com.jane.buyherecommon.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends SearchRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE CONCAT(c.email, ' ', c.firstName, ' ', c.lastName, ' ', "
            + "c.addressLine1, ' ', c.addressLine2, ' ', c.city, ' ', c.state, "
            + "' ', c.postalCode, ' ', c.country.name) LIKE %?1%")
    public Page<Customer> findAll(String keyword, Pageable pageable);

    @Query("UPDATE Customer c SET c.enabled = ?2 WHERE c.id = ?1")
    @Modifying
    public void updateEnabledStatus(Integer id, boolean enabled);

    @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    public Customer findByEmail(String email);

    public Long countById(Integer id);
}


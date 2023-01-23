/**
 * FileName: CountryRepository
 * Author: jane
 * Date: 2023/1/16 23:14
 * Description:
 * Version:
 */
package com.jane.buyherebackend.repository;

import com.jane.buyherecommon.entity.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, Integer> {

    public List<Country> findAllByOrderByNameAsc();

    @Query("SELECT c FROM Country c WHERE c.name = :name")
    public Country findByName(String name);
}
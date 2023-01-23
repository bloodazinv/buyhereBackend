/**
 * FileName: StateRepository
 * Author: jane
 * Date: 2023/1/16 23:18
 * Description:
 * Version:
 */
package com.jane.buyherebackend.repository;

import com.jane.buyherecommon.entity.Country;
import com.jane.buyherecommon.entity.State;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StateRepository extends CrudRepository<State, Integer> {

    public List<State> findByCountryOrderByNameAsc(Country country);

    @Query("SELECT s FROM State s LEFT JOIN s.country ON s.country.id = s.id WHERE s.name = :name")
    public State findByName(String name);
}

/**
 * FileName: StateRepository
 * Author: jane
 * Date: 2023/1/17 14:22
 * Description:
 * Version:
 */
package com.jane.buyherefrontend.repository;

import com.jane.buyherecommon.entity.Country;
import com.jane.buyherecommon.entity.State;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StateRepository extends CrudRepository<State, Integer> {

    public List<State> findByCountryOrderByNameAsc(Country country);
}

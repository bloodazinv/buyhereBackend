/**
 * FileName: CurrencyRepository
 * Author: jane
 * Date: 2023/1/16 11:23
 * Description:
 * Version:
 */
package com.jane.buyherebackend.repository;

import com.jane.buyherecommon.entity.Currency;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CurrencyRepository extends CrudRepository<Currency, Integer> {

    public List<Currency> findAllByOrderByNameAsc();
}

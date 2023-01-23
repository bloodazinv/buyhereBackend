/**
 * FileName: CurrencyRepository
 * Author: jane
 * Date: 2023/1/17 13:55
 * Description:
 * Version:
 */
package com.jane.buyherefrontend.repository;

import com.jane.buyherecommon.entity.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, Integer> {

}

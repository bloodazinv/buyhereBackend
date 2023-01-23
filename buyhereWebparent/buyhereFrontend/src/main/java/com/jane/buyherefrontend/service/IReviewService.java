/**
 * FileName: IReviewService
 * Author: jane
 * Date: 2023/1/22 15:34
 * Description:
 * Version:
 */
package com.jane.buyherefrontend.service;

import com.jane.buyherecommon.entity.Customer;
import com.jane.buyherecommon.entity.Review;
import com.jane.buyherecommon.exception.ReviewNotFoundException;
import org.springframework.data.domain.Page;

public interface IReviewService {

    public Page<Review> listByCustomerByPage(Customer customer, String keyword, int pageNum,
                                             String sortField, String sortDir);

    public Review getByCustomerAndId(Customer customer, Integer reviewId) throws ReviewNotFoundException;
}

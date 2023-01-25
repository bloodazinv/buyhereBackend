/**
 * FileName: ReviewRepository
 * Author: jane
 * Date: 2023/1/25 12:46
 * Description:
 * Version:
 */
package com.jane.buyherebackend.repository;

import com.jane.buyherebackend.paging.SearchRepository;
import com.jane.buyherecommon.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends SearchRepository<Review, Integer> {

    @Query("SELECT r FROM Review r WHERE "
            + "r.headline LIKE %?1% OR "
            + "r.comment LIKE %?1% OR r.product.name LIKE %?1% OR "
            + "CONCAT(r.customer.firstName, ' ', r.customer.lastName) LIKE %?1%")
    public Page<Review> findAll(String keyword, Pageable pageable);

    public List<Review> findAll();
}

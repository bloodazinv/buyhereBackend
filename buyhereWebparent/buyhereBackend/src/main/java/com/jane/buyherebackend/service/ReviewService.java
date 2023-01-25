/**
 * FileName: ReviewService
 * Author: jane
 * Date: 2023/1/25 12:45
 * Description:
 * Version:
 */

package com.jane.buyherebackend.service;

import com.jane.buyherebackend.paging.PagingAndSortingHelper;
import com.jane.buyherebackend.repository.ProductRepository;
import com.jane.buyherebackend.repository.ReviewRepository;
import com.jane.buyherebackend.service.impl.IReviewService;
import com.jane.buyherecommon.entity.Review;
import com.jane.buyherecommon.exception.ReviewNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ReviewService implements IReviewService {

    public static final int REVIEWS_PER_PAGE = 5;


    private ReviewRepository reviewRepo;

    private ProductRepository productRepo;

    @Autowired
    public ReviewService(ReviewRepository reviewRepo, ProductRepository productRepo) {
        super();
        this.reviewRepo = reviewRepo;
        this.productRepo = productRepo;
    }

    public void listByPage(int pageNum, PagingAndSortingHelper helper) {
        helper.listEntities(pageNum, REVIEWS_PER_PAGE, reviewRepo);
    }

    @Override
    public Review get(Integer id) throws ReviewNotFoundException {
        // TODO Auto-generated method stub
        try {
            return reviewRepo.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new ReviewNotFoundException("Could not find any reviews with ID " + id);
        }
    }

    @Override
    public void save(Review reviewInForm) {
        // TODO Auto-generated method stub
        Review reviewInDB = reviewRepo.findById(reviewInForm.getId()).get();
        reviewInDB.setHeadline(reviewInForm.getHeadline());
        reviewInDB.setComment(reviewInForm.getComment());

        reviewRepo.save(reviewInDB);
        productRepo.updateReviewCountAndAverageRating(reviewInDB.getProduct().getId());
    }

    @Override
    public void delete(Integer id) throws ReviewNotFoundException {
        // TODO Auto-generated method stub
        if (!reviewRepo.existsById(id)) {
            throw new ReviewNotFoundException("Could not find any reviews with ID " + id);
        }

        reviewRepo.deleteById(id);
    }

}

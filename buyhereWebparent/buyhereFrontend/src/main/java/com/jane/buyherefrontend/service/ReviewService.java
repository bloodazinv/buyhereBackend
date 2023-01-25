/**
 * FileName: ReviewService
 * Author: jane
 * Date: 2023/1/22 15:34
 * Description:
 * Version:
 */

package com.jane.buyherefrontend.service;

import com.jane.buyherecommon.entity.Customer;
import com.jane.buyherecommon.entity.Review;
import com.jane.buyherecommon.entity.order.OrderStatus;
import com.jane.buyherecommon.entity.product.Product;
import com.jane.buyherecommon.exception.ReviewNotFoundException;
import com.jane.buyherefrontend.repository.OrderDetailRepository;
import com.jane.buyherefrontend.repository.ProductRepository;
import com.jane.buyherefrontend.repository.ReviewRepository;
import com.jane.buyherefrontend.service.impl.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class ReviewService implements IReviewService {

    public static final int REVIEWS_PER_PAGE = 5;

    private ReviewRepository reviewRepo;
    private OrderDetailRepository orderDetailRepo;
    private ProductRepository productRepo;

    @Autowired
    public ReviewService(ReviewRepository reviewRepo, OrderDetailRepository orderDetailRepo,
                         ProductRepository productRepo) {
        super();
        this.reviewRepo = reviewRepo;
        this.orderDetailRepo = orderDetailRepo;
        this.productRepo = productRepo;
    }

    @Override
    public Page<Review> listByCustomerByPage(Customer customer, String keyword, int pageNum, String sortField,
                                             String sortDir) {

        // TODO Auto-generated method stub
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

        Pageable pageable = PageRequest.of(pageNum - 1, REVIEWS_PER_PAGE, sort);

        if (keyword != null) {
            return reviewRepo.findByCustomer(customer.getId(), keyword, pageable);
        }

        return reviewRepo.findByCustomer(customer.getId(), pageable);
    }

    @Override
    public Review getByCustomerAndId(Customer customer, Integer reviewId) throws ReviewNotFoundException {
        // TODO Auto-generated method stub
        Review review = reviewRepo.findByCustomerAndId(customer.getId(), reviewId);
        if (review == null)
            throw new ReviewNotFoundException("Customer doesn not have any reviews with ID " + reviewId);

        return review;
    }

    public Page<Review> list3MostVotedReviewsByProduct(Product product) {
        Sort sort = Sort.by("votes").descending();
        Pageable pageable = PageRequest.of(0, 3, sort);

        return reviewRepo.findByProduct(product, pageable);
    }

    public Page<Review> listByProduct(Product product, int pageNum, String sortField, String sortDir) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum - 1, REVIEWS_PER_PAGE, sort);

        return reviewRepo.findByProduct(product, pageable);
    }

    public boolean didCustomerReviewProduct(Customer customer, Integer productId) {
        Long count = reviewRepo.countByCustomerAndProduct(customer.getId(), productId);
        return count == 1;

    }

    public boolean canCustomerReviewProduct(Customer customer, Integer productId) {
        Long count = orderDetailRepo.countByProductAndCustomerAndOrderStatus(productId, customer.getId(), OrderStatus.DELIVERED);
        return count > 0;

    }

    public Review save(Review review) {
        review.setReviewTime(new Date());
        Review savedReview = reviewRepo.save(review);

        Integer productId = savedReview.getProduct().getId();
        productRepo.updateReviewCountAndAverageRating(productId);

        return savedReview;
    }
}

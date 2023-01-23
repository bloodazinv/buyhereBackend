/**
 * FileName: ReviewStatusUtil
 * Author: jane
 * Date: 2023/1/22 15:36
 * Description:
 * Version:
 */

package com.jane.buyherefrontend.util;

import com.jane.buyherecommon.entity.Customer;
import com.jane.buyherecommon.entity.order.Order;
import com.jane.buyherecommon.entity.order.OrderDetail;
import com.jane.buyherecommon.entity.product.Product;
import com.jane.buyherefrontend.service.ReviewService;

import java.util.Iterator;

public class ReviewStatusUtil {

    public static void setProductReviewableStatus(Customer customer, Order order, ReviewService reviewService) {
        Iterator<OrderDetail> iterator = order.getOrderDetails().iterator();

        while(iterator.hasNext()) {
            OrderDetail orderDetail = iterator.next();
            Product product = orderDetail.getProduct();
            Integer productId = product.getId();

            boolean didCustomerReviewProduct = reviewService.didCustomerReviewProduct(customer, productId);
            product.setReviewedByCustomer(didCustomerReviewProduct);

            if (!didCustomerReviewProduct) {
                boolean canCustomerReviewProduct = reviewService.canCustomerReviewProduct(customer, productId);
                product.setCustomerCanReview(canCustomerReviewProduct);
            }

        }
    }
}

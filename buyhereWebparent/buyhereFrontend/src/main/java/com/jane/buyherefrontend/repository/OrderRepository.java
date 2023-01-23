/**
 * FileName: OrderRepository
 * Author: jane
 * Date: 2023/1/21 15:45
 * Description:
 * Version:
 */
package com.jane.buyherefrontend.repository;

import com.jane.buyherecommon.entity.Customer;
import com.jane.buyherecommon.entity.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT DISTINCT o FROM Order o JOIN o.orderDetails od JOIN od.product p "
            + "WHERE o.customer.id = ?2 "
            + "AND (p.name LIKE %?1% OR o.status LIKE %?1%)")
    public Page<Order> findAll(String keyword, Integer customerId, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.customer.id = ?1")
    public Page<Order> findAll(Integer customerId, Pageable pageable);

    public Order findByIdAndCustomer(Integer id, Customer customer);

}

/**
 * FileName: OrderService
 * Author: jane
 * Date: 2023/1/21 13:30
 * Description:
 * Version:
 */

package com.jane.buyherebackend.service;

import com.jane.buyherebackend.error.OrderNotFoundException;
import com.jane.buyherebackend.paging.PagingAndSortingHelper;
import com.jane.buyherebackend.repository.CountryRepository;
import com.jane.buyherebackend.repository.OrderRepository;
import com.jane.buyherebackend.service.impl.IOrderService;
import com.jane.buyherecommon.entity.Country;
import com.jane.buyherecommon.entity.order.Order;
import com.jane.buyherecommon.entity.order.OrderStatus;
import com.jane.buyherecommon.entity.order.OrderTrack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService implements IOrderService {

    private static final int ORDERS_PER_PAGE = 10;

    private OrderRepository orderRepo;

    private CountryRepository countryRepo;


    public OrderService(OrderRepository orderRepo, CountryRepository countryRepo) {
        super();
        this.orderRepo = orderRepo;
        this.countryRepo = countryRepo;
    }

    @Override
    public void listByPage(int pageNum, PagingAndSortingHelper helper) {

        String sortField = helper.getSortField();
        String sortDir = helper.getSortDir();
        String keyword = helper.getKeyword();

        Sort sort = null;

        if ("destination".equals(sortField)) {
            sort = Sort.by("country").and(Sort.by("state")).and(Sort.by("city"));
        } else {
            sort = Sort.by(sortField);
        }

        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum - 1, ORDERS_PER_PAGE, sort);

        Page<Order> page = null;

        if (keyword != null) {
            page = orderRepo.findAll(keyword, pageable);
        } else {
            page = orderRepo.findAll(pageable);
        }

        helper.updateModelAttributes(pageNum, page);
    }

    @Override
    public Order get(Integer id) throws OrderNotFoundException {
        try {
            return orderRepo.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new OrderNotFoundException("Could not find any orders with ID " + id);
        }
    }

    @Override
    public void delete(Integer id) throws OrderNotFoundException {
        Long count = orderRepo.countById(id);
        if (count == null || count == 0) {
            throw new OrderNotFoundException("Could not find any orders with ID " + id);
        }

        orderRepo.deleteById(id);
    }

    public List<Country> listAllCountries() {
        return countryRepo.findAllByOrderByNameAsc();
    }

    public void save(Order orderInForm) {
        Order orderInDB = orderRepo.findById(orderInForm.getId()).get();
        orderInForm.setOrderTime(orderInDB.getOrderTime());
        orderInForm.setCustomer(orderInDB.getCustomer());

        orderRepo.save(orderInForm);
    }

    public void updateStatus(Integer orderId, String status) {
        Order orderInDB = orderRepo.findById(orderId).get();
        OrderStatus statusToUpdate = OrderStatus.valueOf(status);

        if (!orderInDB.hasStatus(statusToUpdate)) {
            List<OrderTrack> orderTracks = orderInDB.getOrderTracks();

            OrderTrack track = new OrderTrack();
            track.setOrder(orderInDB);
            track.setStatus(statusToUpdate);
            track.setUpdatedTime(new Date());
            track.setNotes(statusToUpdate.defaultDescription());

            orderTracks.add(track);

            orderInDB.setStatus(statusToUpdate);

            orderRepo.save(orderInDB);
        }

    }
}
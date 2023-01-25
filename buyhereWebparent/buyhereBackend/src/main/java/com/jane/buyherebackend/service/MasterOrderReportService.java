/**
 * FileName: MasterOrderReportService
 * Author: jane
 * Date: 2023/1/25 11:48
 * Description:
 * Version:
 */

package com.jane.buyherebackend.service;

import com.jane.buyherebackend.dto.ReportItemDTO;
import com.jane.buyherebackend.repository.OrderRepository;
import com.jane.buyherebackend.util.MasterOrderReportServiceUtil;
import com.jane.buyherebackend.util.ReportType;
import com.jane.buyherecommon.entity.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MasterOrderReportService extends AbstractReportService{

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterOrderReportService.class);

    private final OrderRepository repo;

    public MasterOrderReportService(OrderRepository repo) {
        super();
        this.repo = repo;
    }

    @Override
    protected List<ReportItemDTO> getReportDataByDateRangeInternal(Date startTime, Date endTime,
                                                                   ReportType reportType) {

        LOGGER.info("MasterOrderReportService | getReportDataByDateRange is called");

        List<Order> listOrders = repo.findByOrderTimeBetween(startTime, endTime);
        MasterOrderReportServiceUtil.printRawData(listOrders);

        List<ReportItemDTO> listReportItems = MasterOrderReportServiceUtil.createReportData(startTime, endTime, dateFormatter, reportType);

        System.out.println();

        MasterOrderReportServiceUtil.calculateSalesForReportData(listOrders, listReportItems, dateFormatter);

        MasterOrderReportServiceUtil.printReportData(listReportItems);

        return listReportItems;
    }


}

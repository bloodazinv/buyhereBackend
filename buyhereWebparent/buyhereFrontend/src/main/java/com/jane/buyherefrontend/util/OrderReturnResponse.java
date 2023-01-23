/**
 * FileName: OrderReturnResponse
 * Author: jane
 * Date: 2023/1/22 15:37
 * Description:
 * Version:
 */

package com.jane.buyherefrontend.util;

public class OrderReturnResponse {

    private Integer orderId;

    public OrderReturnResponse() { }

    public OrderReturnResponse(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

}

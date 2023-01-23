/**
 * FileName: OrderResponseDTO
 * Author: jane
 * Date: 2023/1/21 13:43
 * Description:
 * Version:
 */

package com.jane.buyherebackend.dto;

public class OrderResponseDTO {

    private Integer orderId;
    private String status;

    public OrderResponseDTO(Integer orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

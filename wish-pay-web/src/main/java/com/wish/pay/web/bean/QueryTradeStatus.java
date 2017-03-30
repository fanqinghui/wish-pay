package com.wish.pay.web.bean;


import org.hibernate.validator.constraints.NotBlank;

/**
 * 订单查询状态类
 */
public class QueryTradeStatus {

    /**
     * 业务订单流水号
     */
    @NotBlank(message = "订单号不能为空")
    private String orderSerial;


    /**
     * 支付方式-alipay与wx
     */
    @NotBlank(message = "支付方式不能为空")
    private String payWay;

    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }
}


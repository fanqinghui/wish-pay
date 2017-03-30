package com.wish.pay.web.bean;


import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

/**
 * 创建支付bean类
 */
public class CreatePayBean {

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品描述
     */
    private String goodDes;

    /**
     * 商品价格
     */
    private String goodsPrice;

    /**
     * 业务订单流水号
     */
    @NotBlank(message = "订单号不能为空")
    private String orderSerial;

    /**
     * 内部业务系统编号--医师端--其他端等
     */
    private String inSysNo;


    /**
     * 实际支付金额
     */
    @NotBlank(message = "支付金额不能为空")
    private String amount;

    /**
     * 支付有效时长
     */
    private String payValidDuration;

    /**
     * 支付方式-alipay与wx
     */
    @NotBlank(message = "支付方式不能为空")
    private String payWay;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodDes() {
        return goodDes;
    }

    public void setGoodDes(String goodDes) {
        this.goodDes = goodDes;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial;
    }

    public String getInSysNo() {
        return inSysNo;
    }

    public void setInSysNo(String inSysNo) {
        this.inSysNo = inSysNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayValidDuration() {
        return payValidDuration;
    }

    public void setPayValidDuration(String payValidDuration) {
        this.payValidDuration = payValidDuration;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }
}


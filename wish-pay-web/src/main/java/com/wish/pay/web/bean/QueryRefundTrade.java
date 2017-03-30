package com.wish.pay.web.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 申请退款
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/19 19:22
 */
public class QueryRefundTrade {

    @NotBlank(message = "商户订单号不能为空")
    @Length(max = 64, message = "商户订单号最大长度不能超过64个字母")
    String outTradeNo;//订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no
    @Length(max = 64, message = "支付宝交易号最大长度不能超过64个字母")
    String tradeNo;//支付宝or微信 交易号，和商户订单号不能同时为空

    @Length(max = 64, message = "商户的退款申请交易号")
    String outRequestNo;//商户退款单号
    /**
     * 支付方式-alipay与wx
     */
    @NotBlank(message = "支付方式不能为空")
    private String payWay;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOutRequestNo() {
        return outRequestNo;
    }

    public void setOutRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }
}

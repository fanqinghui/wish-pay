package com.wish.pay.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 申请退款
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/9 19:22
 */
public class TradeRefund {

    @JSONField(name = "out_trade_no")
    @NotBlank(message = "商户订单号不能为空")
    @Length(max = 64, message = "商户订单号最大长度不能超过64个字母")
    String outTradeNo;//订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no
    @JSONField(name = "trade_no")
    @Length(max = 64, message = "支付宝交易号最大长度不能超过64个字母")
    String tradeNo;//支付宝or微信 交易号，和商户订单号不能同时为空

    @JSONField(name = "trade_no")
    @Length(max = 64, message = "商户退款单号不能为空")
    String outRefundNo;//商户退款单号


    @JSONField(name = "out_request_no")
    @Length(max = 64, message = "商户的退款申请交易号")
    String outRequestNo;//商户退款单号

    @NotNull
    String tradeAmount;//订单金额金额--需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数  微信支付的是分

    @JSONField(name = "refund_amount")
    @NotNull
    String refundAmount;//退款金额--需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数  微信支付的是分

    @JSONField(name = "refund_reason")
    @Length(max = 256, message = "退款的原因说明最大长度不能超过256")
    String refundReason;//退款的原因说明
    @JSONField(name = "operator_id")
    @Length(max = 30, message = "商户操作员Id最大长度不能超过32个字母")
    String operatorId;//操作员Id
    @JSONField(name = "store_id")
    @Length(max = 32, message = "商户的门店编号最大长度不能超过32个字母")
    String storeId;//操作员Id
    @JSONField(name = "terminal_id")
    @Length(max = 32, message = "商户的终端编号最大长度不能超过32个字母")
    String terminalId;//操作员Id


    //以下参数是查询退款时候用
    // 与商户订单号 out_trade_no 商户退款单号 out_refund_no 微信订单号transactionId  微信退款单号refundId四选一
    //微信订单号
    private String transactionId;
    //微信退款单号--微信生成的退款单号，在申请退款接口有返回
    private String refundId;

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

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(String tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getOutRequestNo() {
        return outRequestNo;
    }

    public void setOutRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo;
    }
}

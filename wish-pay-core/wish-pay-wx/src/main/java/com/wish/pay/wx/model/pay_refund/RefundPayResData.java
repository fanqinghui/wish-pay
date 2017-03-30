package com.wish.pay.wx.model.pay_refund;


import com.wish.pay.wx.model.common.BaseResData;

/**
 * 扫码支付返回数据模型
 * //请求需要双向证书
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/14 18:34
 */
public class RefundPayResData extends BaseResData {

    //以下字段在return_code为SUCCESS的时候有返回
    private String device_info;//设备号  否 String(32) 013467007045764 微信支付分配的终端设备号，与下单一致
    private String transaction_id;//微信订单号 4007752501201407033233368018 微信订单号
    private String out_trade_no;//商户订单号 是 String(32) 33368018 商户系统内部的订单号
    private String out_refund_no;//商户退款单号 是 String(32) 121775250 商户退款单号
    private String refund_id;// 微信退款单号是 String(28) 2007752501201407033233368018 微信退款单号
    private String refund_channel;//退款渠道否 String(16) ORIGINAL  ORIGINAL—原路退款    BALANCE—退回到余额

    private int refund_fee;// 退款金额是 Int 100 退款总金额,单位为分,可以做部分退款
    private int settlement_refund_fee;//应结退款金额 否 Int 100 去掉非充值代金券退款金额后的退款金额，退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
    private int total_fee;// 标价金额 是 Int 100 订单总金额，单位为分，只能为整数，详见支付金额
    private String settlement_total_fee;// 应结订单金额 否 Int 100  去掉非充值代金券金额后的订单总金额，应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
    private String fee_type;//标价币种 否 String(8) CNY 订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private int cash_fee;//现金支付金额 是 Int 100 现金支付金额，单位为分，只能为整数，详见支付金额
    private String cash_fee_type;//现金支付币种 否  String(16)  CNY  货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private int cash_refund_fee;// 现金退款金额否 Int 100 现金退款金额，单位为分，只能为整数，详见支付金额
    private String coupon_type_$n;//代金券类型 否  String(8) CASH CASH--充值代金券
    //NO_CASH---非充值代金券
    //订单使用代金券时有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_0

    private String coupon_refund_fee;//代金券退款总金额否 Int 100 代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠
    private int coupon_refund_fee_$n;//单个代金券退款金额否 Int 100 代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠
    private int coupon_refund_count;//退款代金券使用数量 否 Int 1 退款代金券使用数量
    private String coupon_refund_id_$n;//退款代金券ID 否 String(20)  10000  退款代金券ID, $n为下标，从0开始编号


    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getRefund_channel() {
        return refund_channel;
    }

    public void setRefund_channel(String refund_channel) {
        this.refund_channel = refund_channel;
    }

    public int getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(int refund_fee) {
        this.refund_fee = refund_fee;
    }

    public int getSettlement_refund_fee() {
        return settlement_refund_fee;
    }

    public void setSettlement_refund_fee(int settlement_refund_fee) {
        this.settlement_refund_fee = settlement_refund_fee;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getSettlement_total_fee() {
        return settlement_total_fee;
    }

    public void setSettlement_total_fee(String settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public int getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(int cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public int getCash_refund_fee() {
        return cash_refund_fee;
    }

    public void setCash_refund_fee(int cash_refund_fee) {
        this.cash_refund_fee = cash_refund_fee;
    }

    public String getCoupon_type_$n() {
        return coupon_type_$n;
    }

    public void setCoupon_type_$n(String coupon_type_$n) {
        this.coupon_type_$n = coupon_type_$n;
    }

    public String getCoupon_refund_fee() {
        return coupon_refund_fee;
    }

    public void setCoupon_refund_fee(String coupon_refund_fee) {
        this.coupon_refund_fee = coupon_refund_fee;
    }

    public int getCoupon_refund_fee_$n() {
        return coupon_refund_fee_$n;
    }

    public void setCoupon_refund_fee_$n(int coupon_refund_fee_$n) {
        this.coupon_refund_fee_$n = coupon_refund_fee_$n;
    }

    public int getCoupon_refund_count() {
        return coupon_refund_count;
    }

    public void setCoupon_refund_count(int coupon_refund_count) {
        this.coupon_refund_count = coupon_refund_count;
    }

    public String getCoupon_refund_id_$n() {
        return coupon_refund_id_$n;
    }

    public void setCoupon_refund_id_$n(String coupon_refund_id_$n) {
        this.coupon_refund_id_$n = coupon_refund_id_$n;
    }
}

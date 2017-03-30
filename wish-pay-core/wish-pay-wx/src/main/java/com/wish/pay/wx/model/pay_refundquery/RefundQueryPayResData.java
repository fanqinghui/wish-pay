package com.wish.pay.wx.model.pay_refundquery;


import com.wish.pay.wx.model.common.BaseResData;

/**
 * 查询退款返回数据模型
 * //请求需要双向证书
 *
 * @author fqh
 * $n 替换了0
 * @email fanqinghui100@126.com
 * @date 2017/3/14 18:34
 */
public class RefundQueryPayResData extends BaseResData {

    //以下字段在return_code为SUCCESS的时候有返回
    private String device_info;//设备号  否 String(32) 013467007045764 微信支付分配的终端设备号，与下单一致
    private String transaction_id;//微信订单号 4007752501201407033233368018 微信订单号
    private String out_trade_no;//商户订单号 是 String(32) 33368018 商户系统内部的订单号
    private int total_fee;// 标价金额 是 Int 100 订单总金额，单位为分，只能为整数，详见支付金额
    private String settlement_total_fee;//应结订单金额 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。private String settlement_total_fee;//应结订单金额 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
    private String fee_type;//标价币种 否 String(8) CNY 订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型

    private int cash_fee;//现金支付金额 是 Int 100 现金支付金额，单位为分，只能为整数，详见支付金额
    private int refund_count;//退款笔数 退款记录数
    private String refund_id_0;//微信退款单号  是 String(28) 1217752501201407033233368018 微信退款单号
    private String refund_channel_0;//退款渠道  否 String(16) ORIGINAL ORIGINAL—原路退款 BALANCE—退回到余额

    private int refund_fee_0;//申请退款金额 是 Int 100 退款总金额,单位为分,可以做部分退款
    private int settlement_refund_fee_0;//退款金额 否 Int 100 退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
    private int coupon_type_0;//  否  Int  CASH CASH--充值代金券   NO_CASH---非充值代金券  订单使用代金券时有返回（取值：CASH、NO_CASH）。0为下标,从0开始编号，举例：coupon_type_$0

    private int coupon_refund_fee_0;//总代金券退款金额 否 Int 100 代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠
    private int coupon_refund_count_0;//退款代金券使用数量 否 Int 1 退款代金券使用数量 ,0为下标,从0开始编号
    private String coupon_refund_id_0_$m;//退款代金券ID 否 String(20)  10000  退款代金券ID, 0为下标，$m为下标，从0开始编号
    private int coupon_refund_fee_0_$m;//单个代金券退款金额 否 Int 100 单个退款代金券支付金额, 0为下标，$m为下标，从0开始编号
    private String refund_status_0;//退款状态 是 String(16) SUCCESS 退款状态：
    /*SUCCESS—退款成功
    REFUNDCLOSE—退款关闭。
    NOTSURE—未确定，需要商户用原退款单号重新发起退款申请。
    PROCESSING—退款处理中
    CHANGE—退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，商户可以发起异常退款处理的申请，可以退款到用户的新卡或者退款商户，商户自行处理。0为下标，从0开始编号。*/

    private String refund_account_0;//退款资金来源 否 String(30) REFUND_SOURCE_RECHARGE_FUNDS REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款/基本账户 REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款  0为下标，从0开始编号。

    private String refund_recv_accout_0;//退款入账账户 是 String(64) 招商银行信用卡0403 取当前退款单的退款入账方
    /*1）退回银行卡：    {银行名称}{卡类型}{卡尾号}
    2）退回支付用户零钱:    支付用户零钱
    3）退还商户:    商户基本账户  商户结算银行账户*/
    private String refund_accout_0;//退款成功时间 否 String(20) 2016-07-25 15:26:26 退款成功时间，当退款状态为退款成功时有返回。0为下标，从0开始编号。

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

    public int getRefund_count() {
        return refund_count;
    }

    public void setRefund_count(int refund_count) {
        this.refund_count = refund_count;
    }

    public String getRefund_id_0() {
        return refund_id_0;
    }

    public void setRefund_id_0(String refund_id_0) {
        this.refund_id_0 = refund_id_0;
    }

    public String getRefund_channel_0() {
        return refund_channel_0;
    }

    public void setRefund_channel_0(String refund_channel_0) {
        this.refund_channel_0 = refund_channel_0;
    }

    public int getRefund_fee_0() {
        return refund_fee_0;
    }

    public void setRefund_fee_0(int refund_fee_0) {
        this.refund_fee_0 = refund_fee_0;
    }

    public int getSettlement_refund_fee_0() {
        return settlement_refund_fee_0;
    }

    public void setSettlement_refund_fee_0(int settlement_refund_fee_0) {
        this.settlement_refund_fee_0 = settlement_refund_fee_0;
    }

    public int getCoupon_type_0() {
        return coupon_type_0;
    }

    public void setCoupon_type_0(int coupon_type_0) {
        this.coupon_type_0 = coupon_type_0;
    }

    public int getCoupon_refund_fee_0() {
        return coupon_refund_fee_0;
    }

    public void setCoupon_refund_fee_0(int coupon_refund_fee_0) {
        this.coupon_refund_fee_0 = coupon_refund_fee_0;
    }

    public int getCoupon_refund_count_0() {
        return coupon_refund_count_0;
    }

    public void setCoupon_refund_count_0(int coupon_refund_count_0) {
        this.coupon_refund_count_0 = coupon_refund_count_0;
    }

    public String getCoupon_refund_id_0_$m() {
        return coupon_refund_id_0_$m;
    }

    public void setCoupon_refund_id_0_$m(String coupon_refund_id_0_$m) {
        this.coupon_refund_id_0_$m = coupon_refund_id_0_$m;
    }

    public int getCoupon_refund_fee_0_$m() {
        return coupon_refund_fee_0_$m;
    }

    public void setCoupon_refund_fee_0_$m(int coupon_refund_fee_0_$m) {
        this.coupon_refund_fee_0_$m = coupon_refund_fee_0_$m;
    }

    public String getRefund_status_0() {
        return refund_status_0;
    }

    public void setRefund_status_0(String refund_status_0) {
        this.refund_status_0 = refund_status_0;
    }

    public String getRefund_account_0() {
        return refund_account_0;
    }

    public void setRefund_account_0(String refund_account_0) {
        this.refund_account_0 = refund_account_0;
    }

    public String getRefund_recv_accout_0() {
        return refund_recv_accout_0;
    }

    public void setRefund_recv_accout_0(String refund_recv_accout_0) {
        this.refund_recv_accout_0 = refund_recv_accout_0;
    }

    public String getRefund_accout_0() {
        return refund_accout_0;
    }

    public void setRefund_accout_0(String refund_accout_0) {
        this.refund_accout_0 = refund_accout_0;
    }
}

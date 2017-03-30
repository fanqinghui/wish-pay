package com.wish.pay.wx.model.pay_orderquery;


import com.wish.pay.wx.model.common.BaseResData;

/**
 * 订单查询返回数据模型
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/14 17:31
 */
public class OrderQueryResData extends BaseResData {

    //业务返回的具体数据（以下字段在return_code 和result_code 都为SUCCESS 的时候有返回）
    private String device_info = "";//设备号
    private String openid = "";//预支付交易会话标识
    private String is_subscribe = "";//是否关注公众账号
    private String trade_type = "";//交易类型
    /**
     * SUCCESS—支付成功
     * <p>
     * REFUND—转入退款
     * <p>
     * NOTPAY—未支付
     * <p>
     * CLOSED—已关闭
     * <p>
     * REVOKED—已撤销（刷卡支付）
     * <p>
     * USERPAYING--用户支付中
     * <p>
     * PAYERROR--支付失败(其他原因，如银行返回失败)
     */
    private String trade_state = "";//交易类型
    private String bank_type = "";//付款银行

    private int total_fee;//标价金额  订单总金额，单位为分
    private int settlement_total_fee;//应结订单金额 否 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。

    private String fee_type;//标价币种 CNY  货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型

    private int cash_fee; //现金支付金额 是  Int  100  现金支付金额订单现金支付金额，详见支付金额

    private String cash_fee_type;//现金支付币种 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private int coupon_fee;//代金券金额 “代金券”金额<=订单金额，订单金额-“代金券”金额=现金支付金额，详见支付金额
    private int coupon_count;//代金券使用数量   代金券使用数量
    private String coupon_type_0;//代金券类型 CASH CASH--充值代金券

    // NO_CASH---非充值代金券     订单使用代金券时有返回（取值：CASH、NO_CASH）。0为下标,从0开始编号，举例：coupon_type_$0
    private String coupon_id_0;//代金券ID代金券ID, 0为下标，从0开始编号
    private int coupon_fee_0;//单个代金券支付金额  单个代金券支付金额, 0为下标，从0开始编号
    private String transaction_id;//微信支付订单号 是  String(32)  1009660380201506130728806387  微信支付订单号
    private String out_trade_no;// 商户订单号  商户系统的订单号，与请求一致。
    private String attach;//  附加数据 深圳分店 附加数据，原样返回
    private String time_end;// 支付完成时间  订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
    private String trade_state_desc;//交易状态描述   支付失败，请重新下单支付 对当前查询订单状态的描述和下一步操作的指引

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public int getSettlement_total_fee() {
        return settlement_total_fee;
    }

    public void setSettlement_total_fee(int settlement_total_fee) {
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

    public int getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(int coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public int getCoupon_count() {
        return coupon_count;
    }

    public void setCoupon_count(int coupon_count) {
        this.coupon_count = coupon_count;
    }

    public String getCoupon_type_0() {
        return coupon_type_0;
    }

    public void setCoupon_type_0(String coupon_type_0) {
        this.coupon_type_0 = coupon_type_0;
    }

    public String getCoupon_id_0() {
        return coupon_id_0;
    }

    public void setCoupon_id_0(String coupon_id_0) {
        this.coupon_id_0 = coupon_id_0;
    }

    public int getCoupon_fee_0() {
        return coupon_fee_0;
    }

    public void setCoupon_fee_0(int coupon_fee_0) {
        this.coupon_fee_0 = coupon_fee_0;
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

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    public void setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
    }
}

package com.wish.pay.common.model;

/**
 * 交易状态统一代码枚举
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/29 15:06
 */
public enum TradeOrderStatus {

    WAIT_BUYER_PAY("WAIT_BUYER_PAY", "WAIT_BUYER_PAY", "NOTPAY", "交易创建，等待买家付款"),//USERPAYING--用户支付中
    USERPAYING("USERPAYING", "", "USERPAYING", "用户支付中"),//USERPAYING--用户支付中
    TRADE_CLOSED("TRADE_CLOSED", "TRADE_CLOSED", "CLOSED", "未付款交易超时关闭，或支付完成后全额退款"),
    TRADE_SUCCESS("TRADE_SUCCESS", "TRADE_SUCCESS", "SUCCESS", "交易支付成功"),
    TRADE_FINISHED("TRADE_FINISHED", "TRADE_FINISHED", "", "交易结束，不可退款");


/*
    --------SUCCESS—支付成功
    REFUND—转入退款
    NOTPAY—未支付
    --------CLOSED—已关闭
    REVOKED—已撤销（刷卡支付）
    --------USERPAYING--用户支付中
    PAYERROR--支付失败(其他原因，如银行返回失败)
*/


    private String tradeCode;//阿里交易代码
    private String aliTradeCode;//阿里交易代码
    private String wxTradeCode;//微信交易代码
    private String codeDesc;//交易状态

    TradeOrderStatus(String tradeCode, String aliTradeCode, String wxTradeCode, String codeDesc) {
        this.tradeCode = tradeCode;
        this.aliTradeCode = aliTradeCode;
        this.wxTradeCode = wxTradeCode;
        this.codeDesc = codeDesc;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public String getAliTradeCode() {
        return aliTradeCode;
    }

    public String getWxTradeCode() {
        return wxTradeCode;
    }

    public String getCodeDesc() {
        return codeDesc;
    }
}

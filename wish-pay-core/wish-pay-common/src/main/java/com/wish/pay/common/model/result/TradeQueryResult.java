package com.wish.pay.common.model.result;

import com.wish.pay.common.model.Contains;

/**
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/29 13:30
 */
public class TradeQueryResult implements Result {


    /**
     * 交易状态结果
     */
    private Contains.TradeStatus tradeStatus;
    /**
     * 返回信息
     */
    private String msg;//信息
    private String tradeNo;//支付宝或者微信订单号
    private String outTradeNo;//商户订单号
    private String totalAmount;//订单金额
    private String tradeCode;//交易状态code


    public void setTradeStatus(Contains.TradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    @Override
    public boolean isTradeSuccess() {
        return tradeStatus != null &&
                Contains.TradeStatus.SUCCESS.equals(tradeStatus);
    }
}

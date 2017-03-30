package com.wish.pay.common.model.result;

import com.wish.pay.common.model.Contains;

/**
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/24 15:23
 */
public class RefundResult implements Result {

    private Contains.TradeStatus tradeStatus;
    private String msg;

    private String tradeNo;//支付宝或者微信订单号

    private String outTradeNo;//商户订单号

    private String refundReason;//退款原因

    private long totalAmount;//对款对应交易的订单金额

    private long refundAmount;//本次退款请求，对应的退款金额

    private String refundStatus;//退款进度
/*
    SUCCESS—退款成功
    REFUNDCLOSE—退款关闭。
    NOTSURE—未确定，需要商户用原退款单号重新发起退款申请。
    PROCESSING—退款处理中
    CHANGE—退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，商户可以发起异常退款处理的申请，可以退款到用户的新卡或者退款商户，商户自行处理。$n为下标，从0开始编号。
*/



    @Override
    public boolean isTradeSuccess() {
        return tradeStatus != null &&
                Contains.TradeStatus.SUCCESS.equals(tradeStatus);
    }

    public Contains.TradeStatus getTradeStatus() {
        return tradeStatus;
    }

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

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }
}

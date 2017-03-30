package com.wish.pay.ali.model.notify;

/**
 * 阿里支付异步通知状态枚举
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/8 18:35
 */
public enum NotifyTradeStatus {
    WAIT_BUYER_PAY,//	交易创建，等待买家付款
    TRADE_CLOSED,//	未付款交易超时关闭，或支付完成后全额退款
    TRADE_SUCCESS,//交易支付成功
    TRADE_FINISHED;//	交易结束，不可退款
}

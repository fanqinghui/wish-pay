package com.wish.pay.common.model;


/**
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/2/27 15:31
 */
public interface Contains {

    public final static String SUCCESS = "success";
    public final static String FAIL = "fail";
    public final static String WishPayResultKey = "wish_pay_result_key";

    enum TradeStatus {
        SUCCESS  // 业务交易明确成功，比如支付成功、退货成功
        , FAILED  // 业务交易明确失败，比如支付明确失败、退货明确失败
        , UNKNOWN // 业务交易状态未知，此时不清楚该业务是否成功或者失败，需要商户自行确认
        , INIT // 业务交易初始化
    }
}

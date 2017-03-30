package com.wish.pay.common.model.result;


import com.wish.pay.common.model.Contains.TradeStatus;
import com.wish.pay.common.model.response.PayResponse;

/**
 * 支付相关结果
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/2/27 15:25
 */
public class PayResult implements Result {

    private TradeStatus tradeStatus;
    private PayResponse response;

    public PayResult(PayResponse response) {
        this.response = response;
    }

    public void setTradeStatus(TradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public void setResponse(PayResponse response) {
        this.response = response;
    }

    public TradeStatus getTradeStatus() {
        return tradeStatus;
    }

    public PayResponse getResponse() {
        return response;
    }

    @Override
    public boolean isTradeSuccess() {
        return response != null &&
                TradeStatus.SUCCESS.equals(tradeStatus);
    }
}

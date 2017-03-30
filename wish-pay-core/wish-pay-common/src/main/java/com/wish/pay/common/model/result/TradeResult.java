package com.wish.pay.common.model.result;


import com.wish.pay.common.model.Contains.TradeStatus;
import com.wish.pay.common.model.response.PayResponse;

/**
 * 支付订单结果
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/2/27 15:25
 */
public class TradeResult implements Result {

    /**
     * 交易状态结果
     */
    private TradeStatus tradeStatus;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 二维码-仅对预下单接口操作有用
     */
    private String qrCode;

    public TradeResult(TradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public TradeResult() {
        this.tradeStatus = TradeStatus.INIT;
    }

    public void setTradeStatus(TradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public TradeStatus getTradeStatus() {
        return tradeStatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    /**
     * 判断是否交易成功
     *
     * @return
     */
    @Override
    public boolean isTradeSuccess() {
        return tradeStatus != null &&
                TradeStatus.SUCCESS.equals(tradeStatus);
    }

    @Override
    public String toString() {
        return "创建订单返回结果：{" +
                "tradeStatus=" + tradeStatus +
                ", msg='" + msg + '\'' +
                ", qrCode='" + qrCode + '\'' +
                '}';
    }
}

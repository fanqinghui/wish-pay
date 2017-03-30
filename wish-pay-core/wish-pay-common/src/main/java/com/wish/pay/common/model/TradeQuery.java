package com.wish.pay.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/9 19:22
 */
public class TradeQuery {

    @JSONField(name = "out_trade_no")
    @NotBlank(message = "商户订单号不能为空")
    @Length(max = 64,message = "商户订单号最大长度不能超过64个字母")
    String outTradeNo;//订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no
    @JSONField(name = "trade_no")
    @Length(max = 64,message = "支付宝交易号最大长度不能超过64个字母")
    String tradeNo;//支付宝or微信 交易号，和商户订单号不能同时为空

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
}

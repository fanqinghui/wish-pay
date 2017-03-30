package com.wish.pay.wx.model.pay_refundquery;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wish.pay.wx.common.WxUtils;
import com.wish.pay.wx.model.common.BaseReqData;

/**
 * 查询退款API需要提交的数据
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/14 18:34
 */
@XStreamAlias(value = "xml")
public class RefundQueryPayReqData extends BaseReqData {

    //每个字段具体的意思请查看API文档
    private String transaction_id = "";//微信订单号
    private String out_trade_no = "";//商户订单号 --必填
    private String out_refund_no = "";//商户退款单号-商户侧传给微信的退款单号
    private String refund_id = "";//微信退款单号--微信生成的退款单号，在申请退款接口有返回
    //查询交易以上2个参数必须四选一，推荐使用 商户订单号 或者 商户退款单号


    /**
     * @param appid
     * @param mchId
     * @param deviceInfo
     * @param key
     * @param outTradeNo
     * @param transactionId
     * @param outRefundNo
     * @param refundId
     */
    public RefundQueryPayReqData(String appid, String mchId, String deviceInfo, String key, String outTradeNo, String transactionId, String outRefundNo, String refundId) {
        //微信分配的公众号ID（开通公众号之后可以获取到）
        setAppid(appid);
        //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
        setMch_id(mchId);
        //商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
        setDevice_info(deviceInfo);
        //商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
        setOut_trade_no(outTradeNo);
        //微信订单号--微信生成的单号，与商户订单号二选一即可
        setTransaction_id(transactionId);
        //商户退款单号
        setOut_refund_no(outRefundNo);
        //设置微信退款单号
        setRefund_id(refundId);
        //随机字符串，不长于32 位
        setNonce_str(WxUtils.getRandomStringByLength(32));
        //根据API给的签名规则进行签名
        String sign = WxUtils.getSign(toMap(), key);
        setSign(sign);//把签名数据设置到Sign这个属性中
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

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }
}

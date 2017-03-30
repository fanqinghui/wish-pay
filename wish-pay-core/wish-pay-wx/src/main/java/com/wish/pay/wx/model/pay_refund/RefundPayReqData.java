package com.wish.pay.wx.model.pay_refund;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wish.pay.wx.common.WxUtils;
import com.wish.pay.wx.model.common.BaseReqData;

/**
 * 请求被扫支付API需要提交的数据
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/14 18:34
 */
@XStreamAlias(value = "xml")
public class RefundPayReqData extends BaseReqData {

    //每个字段具体的意思请查看API文档
    private String transaction_id = "";//微信订单号
    private String out_trade_no = "";//商户订单号 --必填
    //查询交易以上2个参数必须二选一，微信建议使用微信订单号，实际上使用用户订单号

    private String out_refund_no = "";//商户退款单号-必填 商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
    private int total_fee = 0;//订单总金额--单位为分--必填
    private int refund_fee = 0;//退款金额--单位为分 --必填
    private String refund_fee_type = "";//货币种类--CNY
    private String op_user_id = "";//操作员帐号, 默认为商户号-必填
    private String refund_account = "";//退款资金来源仅针对老资金流商户使用
    //REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
    //REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款(限非当日交易订单的退款

    /**
     * @param appid
     * @param mchId
     * @param deviceInfo
     * @param key
     * @param outTradeNo
     * @param transactionId
     * @param outRefundNo
     * @param totalFee
     * @param refundFee
     * @param opUserId
     */
    public RefundPayReqData(String appid, String mchId, String deviceInfo, String key, String outTradeNo, String transactionId, String outRefundNo, int totalFee, int refundFee, String opUserId) {
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
        //订单总金额，单位为“分”，只能整数
        setTotal_fee(totalFee);
        //退款金额，单位为“分”，只能整数
        setRefund_fee(refundFee);
        //操作员--默认可以传递商户号mchId
        setOp_user_id(opUserId);
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

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public int getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(int refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getRefund_fee_type() {
        return refund_fee_type;
    }

    public void setRefund_fee_type(String refund_fee_type) {
        this.refund_fee_type = refund_fee_type;
    }

    public String getOp_user_id() {
        return op_user_id;
    }

    public void setOp_user_id(String op_user_id) {
        this.op_user_id = op_user_id;
    }

    public String getRefund_account() {
        return refund_account;
    }

    public void setRefund_account(String refund_account) {
        this.refund_account = refund_account;
    }
}

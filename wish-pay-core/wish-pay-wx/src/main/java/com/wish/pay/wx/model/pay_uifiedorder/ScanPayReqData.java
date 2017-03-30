package com.wish.pay.wx.model.pay_uifiedorder;

import com.beust.jcommander.internal.Maps;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wish.pay.wx.common.WxUtils;
import com.wish.pay.wx.model.common.BaseReqData;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 请求被扫支付API需要提交的数据
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/14 18:34
 */
@XStreamAlias(value = "xml")
public class ScanPayReqData extends BaseReqData {

    //每个字段具体的意思请查看API文档
    private String body = "";//商品描述
    private String attach = "";//附加数据
    private String out_trade_no = "";//商户订单号
    private int total_fee = 0;//订单总金额--单位为分
    private String spbill_create_ip = "";//终端IP
    private String time_start = "";//交易起始时间
    private String time_expire = "";//
    private String notify_url = "";//通知地址
    private String trade_type = "NATIVE";//JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付


    /**
     * @param appid
     * @param mchId
     * @param deviceInfo
     * @param key
     * @param body
     * @param attach
     * @param outTradeNo
     * @param totalFee
     * @param spBillCreateIP
     * @param timeStart
     * @param timeExpire
     */
    public ScanPayReqData(String appid, String mchId, String deviceInfo, String key, String body, String attach, String outTradeNo, int totalFee, String spBillCreateIP, String timeStart, String timeExpire) {
        //微信分配的公众号ID（开通公众号之后可以获取到）
        setAppid(appid);
        //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
        setMch_id(mchId);
        //商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
        setDevice_info(deviceInfo);
        //要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
        setBody(body);
        //支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回，有助于商户自己可以注明该笔消费的具体内容，方便后续的运营和记录
        setAttach(attach);
        //商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
        setOut_trade_no(outTradeNo);
        //订单总金额，单位为“分”，只能整数
        setTotal_fee(totalFee);
        //订单生成的机器IP
        setSpbill_create_ip(spBillCreateIP);
        //订单生成时间， 格式为yyyyMMddHHmmss，如2009年12 月25 日9 点10 分10 秒表示为20091225091010。时区为GMT+8 beijing。该时间取自商户服务器
        setTime_start(timeStart);
        //订单失效时间，格式同上
        setTime_expire(timeExpire);
        //随机字符串，不长于32 位
        setNonce_str(WxUtils.getRandomStringByLength(32));
        //根据API给的签名规则进行签名
        String sign = WxUtils.getSign(toMap(), key);
        setSign(sign);//把签名数据设置到Sign这个属性中
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }
}

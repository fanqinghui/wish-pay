package com.wish.pay.wx.common;

/**
 * 阿里支付交易接口枚举类
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/2/27 11:14
 */
public enum WxPayInterface {

    WxpayTrade_Unifiedorder(1, "/pay/unifiedorder", "统一下单子"),
    WxpayTrade_Orderquery(2, "/pay/orderquery", "查询订单"),
    WxpayTrade_Closeorder(3, "/pay/closeorder", "关闭订单"),
    WxpayTrade_Refund(4, "/secapi/pay/refund", "申请退款"),
    WxpayTrade_Refundquery(5, "/pay/refundquery", "查询退款"),
    WxpayTrade_Downloadbill(6, "/pay/downloadbill", "下载对账单"),
    WxpayTrade_Report(7, "/payitil/report", "交易保障"),
    WxpayTrade_Shorturl(8, "/tools/shorturl","转换端连接(主要用于扫码原生支付模式一中的二维码链接转成短链接)");

    WxPayInterface(Integer type, String method, String methodName) {
        this.type = type;
        this.method = method;
        this.methodName = methodName;
    }

    private Integer type;//交易类型
    private String method;//交易接口
    private String methodName;//交易接口名称

    public Integer getType() {
        return type;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getMethod() {
        return method;
    }
}

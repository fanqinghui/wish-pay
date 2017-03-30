package com.wish.pay.ali.model.notify;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 当面付异步通知实体-仅用于扫码支付
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/8 17:02
 */
public class AliPayTradeResultNotify {

    @JSONField(name = "notify_time")
    private String notifyTime;//notify_time	通知时间	Date	是	通知的发送时间。格式为yyyy-MM-dd HH:mm:ss	2015-14-27 15:45:58
    @JSONField(name = "notify_type")
    private String notifyType;//	通知类型	String(64)	是	通知的类型	trade_status_sync
    @JSONField(name = "notify_id")
    private String notifyId;//	通知校验ID	String(128)	是	通知校验ID	ac05099524730693a8b330c5ecf72da9786
    @JSONField(name = "sign_type")
    private String signType;//	签名类型	String(10)	是	商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2	RSA2
    @JSONField(name = "sign")
    private String sign;//	签名	String(256)	是	请参考异步返回结果的验签	601510b7970e52cc63db0f44997cf70e
    @JSONField(name = "trade_no")
    private String tradeNo;//	支付宝交易号	String(64)	是	支付宝交易凭证号	2013112011001004330000121536
    @JSONField(name = "app_id")
    private String appId;//	开发者的app_id	String(32)	是	支付宝分配给开发者的应用Id	2014072300007148
    @JSONField(name = "out_trade_no")
    private String outTradeNo;//	商户订单号	String(64)	是	原支付请求的商户订单号	6823789339978248
    @JSONField(name = "out_biz_no")
    private String outBizNo;//	商户业务号	String(64)	否	商户业务ID，主要是退款通知中返回退款申请的流水号	HZRF001
    @JSONField(name = "buyer_id")
    private String buyerId;//	买家支付宝用户号	String(16)	否	买家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字	2088102122524333
    @JSONField(name = "buyer_logon_id")
    private String buyerLogonId;//	买家支付宝账号	String(100)	否	买家支付宝账号	15901825620
    @JSONField(name = "seller_id")
    private String sellerId;//	卖家支付宝用户号	String(30)	否	卖家支付宝用户号	2088101106499364
    @JSONField(name = "seller_email")
    private String sellerEmail;//	卖家支付宝账号	String(100)	否	卖家支付宝账号	zhuzhanghu@alitest.com
    @JSONField(name = "trade_status")
    private String tradeStatus;//	交易状态	String(32)	否	交易目前所处的状态	TRADE_CLOSED
    @JSONField(name = "total_amount")
    private String totalAmount;//订单金额	Number(9,2)	否	本次交易支付的订单金额，单位为人民币（元）	20
    @JSONField(name = "receipt_amount")
    private String receiptAmount;//实收金额	Number(9,2)	否	商家在交易中实际收到的款项，单位为元	15
    @JSONField(name = "invoice_amount")
    private String invoiceAmount;//开票金额	Number(9,2)	否	用户在交易中支付的可开发票的金额	10.00
    @JSONField(name = "buyer_pay_amount")
    private String buyerPayAmount;//付款金额	Number(9,2)	否	用户在交易中支付的金额	13.88
    @JSONField(name = "point_amount")
    private String pointAmount;//集分宝金额	Number(9,2)	否	使用集分宝支付的金额	12.00
    @JSONField(name = "refund_fee")
    private String refundFee;//总退款金额	Number(9,2)	否	退款通知中，返回总退款金额，单位为元，支持两位小数	2.58
    @JSONField(name = "send_back_fee")
    private String sendBackFee;//实际退款金额	Number(9,2)	否	商户实际退款给用户的金额，单位为元，支持两位小数	2.08
    @JSONField(name = "subject")
    private String subject;//	订单标题	String(256)	否	商品的标题/交易标题/订单标题/订单关键字等，是请求时对应的参数，原样通知回来	当面付交易
    @JSONField(name = "body")
    private String body;//	商品描述	String(400)	否	该订单的备注、描述、明细等。对应请求时的body参数，原样通知回来	当面付交易内容
    @JSONField(name = "gmt_create")
    private String gmtCreate;//	交易创建时间	Date	否	该笔交易创建的时间。格式为yyyy-MM-dd HH:mm:ss	2015-04-27 15:45:57
    @JSONField(name = "gmt_payment")
    private String gmtPayment;//交易付款时间	Date	否	该笔交易的买家付款时间。格式为yyyy-MM-dd HH:mm:ss	2015-04-27 15:45:57
    @JSONField(name = "gmt_refund")
    private String gmtRefund;//交易退款时间	Date	否	该笔交易的退款时间。格式为yyyy-MM-dd HH:mm:ss.S	2015-04-28 15:45:57.320
    @JSONField(name = "gmt_close")
    private String gmtClose;//	交易结束时间	Date	否	该笔交易结束时间。格式为yyyy-MM-dd HH:mm:ss	2015-04-29 15:45:57
    @JSONField(name = "fund_bill_list")
    private List<FundBill> fundBillList;//支付金额信息	String(512)	否	支付成功的各个渠道金额信息，详见资金明细信息说明	[{“amount”:“15.00”,“fundChannel”:“ALIPAYACCOUNT”}]

    public String getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutBizNo() {
        return outBizNo;
    }

    public void setOutBizNo(String outBizNo) {
        this.outBizNo = outBizNo;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(String receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getBuyerPayAmount() {
        return buyerPayAmount;
    }

    public void setBuyerPayAmount(String buyerPayAmount) {
        this.buyerPayAmount = buyerPayAmount;
    }

    public String getPointAmount() {
        return pointAmount;
    }

    public void setPointAmount(String pointAmount) {
        this.pointAmount = pointAmount;
    }

    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }

    public String getSendBackFee() {
        return sendBackFee;
    }

    public void setSendBackFee(String sendBackFee) {
        this.sendBackFee = sendBackFee;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtPayment() {
        return gmtPayment;
    }

    public void setGmtPayment(String gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    public String getGmtRefund() {
        return gmtRefund;
    }

    public void setGmtRefund(String gmtRefund) {
        this.gmtRefund = gmtRefund;
    }

    public String getGmtClose() {
        return gmtClose;
    }

    public void setGmtClose(String gmtClose) {
        this.gmtClose = gmtClose;
    }

    public List<FundBill> getFundBillList() {
        return fundBillList;
    }

    public void setFundBillList(List<FundBill> fundBillList) {
        this.fundBillList = fundBillList;
    }
}

/**
 * 资金明细信息说明
 */
class FundBill {

    @JSONField(name = "fundChannel")
    private String fundChannel;//	支付渠道	String	支付渠道，参见下面的“支付渠道说明”。	可空	ALIPAYACCOUNT
    @JSONField(name = "amount")
    private String amount;//	支付金额	String	使用指定支付渠道支付的金额，单位为元。	可空	15.00

    public String getFundChannel() {
        return fundChannel;
    }

    public void setFundChannel(String fundChannel) {
        this.fundChannel = fundChannel;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

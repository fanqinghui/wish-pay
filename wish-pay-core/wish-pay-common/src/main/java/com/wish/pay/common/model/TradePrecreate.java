package com.wish.pay.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 支付宝创建订单(创建订单交易准备数据)
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/1 16:51
 */
public class TradePrecreate  {

    /**
     * (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线
     * 需保证商户系统端不能重复，建议通过数据库sequence生成，
     */
    @NotEmpty
    @Length(max = 64)
    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    /**
     * (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
     */
    @NotEmpty
    @JSONField(name = "subject")
    private String subject;

    /**
     * (必填) 订单总金额，单位为元，不能超过1亿元
     * 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
     */
    @NotEmpty
    @JSONField(name = "total_amount")
    private String totalAmount;

    @JSONField(name = "scene")
    private String scene = "bar_code";


    /**
     * (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
     * 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
     */
    @JSONField(name="undiscountable_amount")
    private String undiscountableAmount;

    @JSONField(name="discountable_amount")
    private String discountableAmount;

    /**
     * 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
     * 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
     */
    @JSONField(name="seller_id")
    private String sellerId;

    /**
     * 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
     */
    @JSONField(name = "body")
    private String body;

    /**
     * 商户操作员编号，添加此参数可以为商户操作员做销售统计
     */
    @JSONField(name="operator_id")
    private String operatorId;

    /**
     * (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息
     */
    @NotEmpty
    @JSONField(name = "store_id")
    private String storeId;

    /**
     * 支付超时，默认为定义为60分钟
     */
    @JSONField(name = "timeout_express")
    private String timeoutExpress = "60m";

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getUndiscountableAmount() {
        return undiscountableAmount;
    }

    public void setUndiscountableAmount(String undiscountableAmount) {
        this.undiscountableAmount = undiscountableAmount;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getTimeoutExpress() {
        return timeoutExpress;
    }

    public void setTimeoutExpress(String timeoutExpress) {
        this.timeoutExpress = timeoutExpress;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getDiscountableAmount() {
        return discountableAmount;
    }

    public void setDiscountableAmount(String discountableAmount) {
        this.discountableAmount = discountableAmount;
    }
}

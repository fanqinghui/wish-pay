package com.wish.pay.wx.common;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 商品详情
 * 商品详细列表，使用Json格式，传输签名前请务必使用CDATA标签将JSON文本串保护起来。
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/13 17:35
 */
public class GoodDetail {

    @JSONField(name = "cost_price")
    private double costPrice;//订单原价
    @JSONField(name = "receipt_id")
    private String receiptId;//商家小票id
    @JSONField(name = "goods_detail")
    private List<Good> goodsDdetail;

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public List<Good> getGoodsDdetail() {
        return goodsDdetail;
    }

    public void setGoodsDdetail(List<Good> goodsDdetail) {
        this.goodsDdetail = goodsDdetail;
    }
}

package com.wish.pay.wx.common;

import com.alibaba.fastjson.annotation.JSONField;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * 商品信息
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/13 17:10
 */
public class Good {

    @JSONField(name ="goods_id")
    @NotNull
    @Max(32)
    private String goodId;//商品编码

    @JSONField(name ="wxpay_goods_id")
    private String wxpayGoodsId;//微信支付定义的统一商品编号


    @JSONField(name ="goods_name")
    private String goodsName;//商品名称

    @JSONField(name ="quantity")
    @NotNull
    private Integer quantity;//商品数量

    @JSONField(name ="price")
    @NotNull
    private Double price;//商品单价


    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getWxpayGoodsId() {
        return wxpayGoodsId;
    }

    public void setWxpayGoodsId(String wxpayGoodsId) {
        this.wxpayGoodsId = wxpayGoodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

package com.wish.pay.web.dao.entity;

import com.wish.pay.common.utils.TradeStatusEnum;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

public class TradeOrder {

    public TradeOrder() {
        this.status= TradeStatusEnum.CREATE.getValue();
    }

    private Long id;

    @NotBlank(message = "购买的商品名称不能为空")
    private String goodsName;//购买的商品名称

    @NotBlank(message = "业务流水号不能为空")
    private String orderSerial;//业务流水号

    private String tradeNo;//支付机构的交易凭证号

    @Max(1)
    @Min(0)
    @NotNull(message = "订单状态不能为空")
    private Integer status;//交易状态--订单状态 0 创建 未支付 1 支付成功 2 支付失败

    @Max(1)
    @Min(0)
    @NotNull(message = "支付渠道不能为空")
    private Integer payWay;// 0 支付宝 1微信

    @DecimalMin("0")
    @DecimalMax("100000000")
    @NotNull
    private BigDecimal amount;//支付金额

    private String msg;//订单支付回馈信息

    private Date createDate;

    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
package com.wish.pay.common.model;

/**
 * 支付类型
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/16 16:11
 */
public enum PayTypeEnum {
    AliPay("0"), WxPay("1");

    private String type;

    PayTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

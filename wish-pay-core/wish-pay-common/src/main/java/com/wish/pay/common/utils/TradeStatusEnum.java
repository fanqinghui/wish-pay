package com.wish.pay.common.utils;

/**
 * 交易订单状态
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/6 15:33
 */
public enum TradeStatusEnum {
    //0  1 支付成功 2 支付失败
    CREATE(0,"创建 未支付"),
    PAY_SUCCESS(1,"支付成功"),
    PAY_FAIL(2,"支付失败");

    TradeStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    private Integer value;
    private String name;

    public Integer getValue() {
        return value;
    }
}

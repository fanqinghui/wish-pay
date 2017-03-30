package com.wish.pay.common.model.result;

/**
 * 退款状态枚举
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/24 17:45
 */
public enum RefundStatusEnum {
    SUCCESS("SUCCESS", "退款成功"),
    REFUNDCLOSE("REFUNDCLOSE", "退款关闭"),
    NOTSURE("NOTSURE", "未确定，需要商户用原退款单号重新发起退款申请"),
    PROCESSING("PROCESSING", "退款处理中"),
    CHANGE("CHANGE", "退款异常");

    private String key;
    private String value;

    RefundStatusEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getStatusValue(String key){
        for(RefundStatusEnum statusEnum:RefundStatusEnum.values()){
            if(key.equals(statusEnum.getKey())){
                return statusEnum.getValue();
            }
        }
        return "";
    }

    /*SUCCESS—退款成功
    REFUNDCLOSE—退款关闭。
    NOTSURE—未确定，需要商户用原退款单号重新发起退款申请。
    PROCESSING—退款处理中
    CHANGE—退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，商户可以发起异常退款处理的申请，可以退款到用户的新卡或者退款商户，商户自行处理*/

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

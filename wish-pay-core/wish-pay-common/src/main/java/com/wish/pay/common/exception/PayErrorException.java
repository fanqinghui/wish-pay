package com.wish.pay.common.exception;

/**
 * 支付异常
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/2/27 14:49
 */
public class PayErrorException extends Exception {

    private PayError payError;

    public PayErrorException(PayError payError) {
        super(payError.toString());
        this.payError = payError;
    }

    public PayError getError() {
        return payError;
    }

}

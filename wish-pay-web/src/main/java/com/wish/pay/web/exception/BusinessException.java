package com.wish.pay.web.exception;

import com.wish.pay.common.exception.PayError;

/**
 * 支付参数异常
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/2/27 14:49
 */
public class BusinessException extends Exception {

    public BusinessException(String message) {
        super(message);
    }

    private ParamsError paramsError;

    public BusinessException(ParamsError paramsError) {
        super(paramsError.toString());
        this.paramsError = paramsError;
    }

    public ParamsError getError() {
        return paramsError;
    }

}

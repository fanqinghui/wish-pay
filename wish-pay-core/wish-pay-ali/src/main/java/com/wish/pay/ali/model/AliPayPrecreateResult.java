package com.wish.pay.ali.model;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.wish.pay.common.model.response.PayResponse;
import com.wish.pay.common.model.result.PayResult;

/**
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/2/28 10:09
 */
public class AliPayPrecreateResult extends PayResult {

    public AliPayPrecreateResult(PayResponse response) {
        super(response);
    }

    /*AlipayTradePrecreateResponse precreateResponse;
    public AliPayPrecreateResult(AliPayPrecreateResult response) {
        super();
        this.precreateResponse=response;
    }*/

}

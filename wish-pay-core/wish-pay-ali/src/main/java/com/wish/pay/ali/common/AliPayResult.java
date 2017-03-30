package com.wish.pay.ali.common;

import com.wish.pay.common.model.response.PayResponse;
import com.wish.pay.common.model.result.PayResult;

/**
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/2 15:37
 */
public class AliPayResult extends PayResult{
    public AliPayResult(PayResponse response) {
        super(response);
    }
}

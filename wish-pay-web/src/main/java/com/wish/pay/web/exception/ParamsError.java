package com.wish.pay.web.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 支付错误码说明
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/2/27 14:49
 */

public class ParamsError implements Serializable {

    private String errorMsg;

    private String responseContent;

    public ParamsError() {
    }

    public ParamsError(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ParamsError(String errorMsg, String responseContent) {
        this(errorMsg);
        this.responseContent = responseContent;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public static ParamsError fromJson(String json) {

        JSONObject jsonObject = JSON.parseObject(json);
        ParamsError error = jsonObject.toJavaObject(ParamsError.class);
        error.setResponseContent(json);
        return error;
    }

    @Override
    public String toString() {
        return "参数错误";
    }

}

package com.wish.pay.wx.model.common;

/**
 * 微信业务基础请求返回
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/14 18:34
 * 相关接口列表 可以参考 @Link https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1 返回结果列表
 */
public class BaseResData extends ProtocolResData {

    //协议返回的具体数据（以下字段在return_code 为SUCCESS 的时候有返回）
    private String appid = "";//公众帐号ID-必须
    private String mch_id = "";//商户号-必须
    private String nonce_str = "";//随机字符串-必须
    private String sign = "";//签名-必须
    private String result_code = "";//业务结果 SUCCESS/FAIL
    private String err_code = "";//错误代码
    private String err_code_des = "";//错误代码描述

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }
}

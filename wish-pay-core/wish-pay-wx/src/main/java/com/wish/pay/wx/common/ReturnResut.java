package com.wish.pay.wx.common;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信返回结果类
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/13 18:42
 */
@XmlRootElement
public class ReturnResut {

    //返回状态码
    private String return_code;//返回状态码 SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
    //返回信息
    private String return_msg;//返回信息，如非空，为错误原因

    //业务字段

    //公众账号ID
    private String appid;//调用接口提交的公众账号ID

    //商户号
    private String mch_id;//调用接口提交的商户号

    //设备号
    private String device_info;//自定义参数，可以为请求支付的终端设备号等


    //随机字符串
    private String nonce_str;//微信返回的随机字符串


    //签名
    private String sign;//微信返回的签名值，详见签名算法


    //业务结果
    private String result_code;//SUCCESS/FAIL


    //错误代码
    private String err_code;//SYSTEMERROR  详细参见下文错误列表


    //错误代码描述
    private String err_code_des;// 错误信息描述
}

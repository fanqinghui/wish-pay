package com.wish.pay.wx.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信返回结果协议层
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/27 18:01
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProtocolResData {
    //协议层
    @XmlElement(name = "return_code")
    private String return_code = "";//返回状态码SUCCESS/FAIL
    @XmlElement(name = "return_msg")
    private String return_msg = "";//返回信息


    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }


    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }
}

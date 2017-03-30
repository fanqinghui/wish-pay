package com.wish.pay.wx.model.common;

import com.beust.jcommander.internal.Maps;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 基础请求参数
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/14 18:34
 * 相关接口列表 可以参考 @Link https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
 */
public class BaseReqData {

    private String appid = "";//公众帐号ID-必须
    private String mch_id = "";//商户号-必须
    private String nonce_str = "";//随机字符串-必须
    private String sign = "";//签名-必须
    private String signType = "MD5";//签名类型-非必须，默认MD5

    private String device_info = "WEB";//设备号-可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"

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

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
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

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }


    /**
     * 转换成map
     * @return
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = Maps.newHashMap();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object obj;
            try {
                obj = field.get(this);
                if (obj != null) {
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}

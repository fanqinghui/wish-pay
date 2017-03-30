package com.wish.pay.wx.model.pay_uifiedorder;


import com.wish.pay.wx.model.common.BaseResData;

/**
 * 扫码支付返回数据模型
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/14 18:34
 */
public class ScanPayResData extends BaseResData{

    //以下字段在return_code为SUCCESS的时候有返回
    private String device_info = "";//设备号

    //业务返回的具体数据（以下字段在return_code 和result_code 都为SUCCESS 的时候有返回）
    private String trade_type = "";//交易类型
    private String prepay_id = "";//预支付交易会话标识
    private String code_url = "";//二维码链接

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }
}

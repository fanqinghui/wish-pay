package com.wish.pay.common.model.builder;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.validator.constraints.Length;

public class ExtendParams {
    /**
     * 系统商编号 该参数作为系统商返佣数据提取的依据，请填写系统商签约协议的PID
     */
    @JSONField(name = "sys_service_provider_id")
    @Length(max = 64)
    private String sysServiceProviderId;
    /**
     * 使用花呗分期要进行的分期数
     */
    @JSONField(name="hb_fq_num")
    @Length(max = 5)
    private String hbFqNum;
    /**
     * 使用花呗分期需要卖家承担的手续费比例的百分值，传入100代表100%
     */
    @JSONField(name="hb_fq_seller_percent")
    @Length(max = 3)
    private String hbFqSellerPercent;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExtendParams{");
        sb.append("sysServiceProviderId='").append(sysServiceProviderId).append('\'');
        sb.append("hbFqNum='").append(hbFqNum).append('\'');
        sb.append("hbFqSellerPercent='").append(hbFqSellerPercent).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getSysServiceProviderId() {
        return sysServiceProviderId;
    }

    public ExtendParams setSysServiceProviderId(String sysServiceProviderId) {
        this.sysServiceProviderId = sysServiceProviderId;
        return this;
    }

    public String getHbFqNum() {
        return hbFqNum;
    }

    public void setHbFqNum(String hbFqNum) {
        this.hbFqNum = hbFqNum;
    }

    public String getHbFqSellerPercent() {
        return hbFqSellerPercent;
    }

    public void setHbFqSellerPercent(String hbFqSellerPercent) {
        this.hbFqSellerPercent = hbFqSellerPercent;
    }
}
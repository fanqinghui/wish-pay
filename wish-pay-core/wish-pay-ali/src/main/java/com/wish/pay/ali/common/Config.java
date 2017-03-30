package com.wish.pay.ali.common;

/**
 * @author fqh
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date ${date} ${time}
 */
public class Config {

    //URL支付网关（固定）
    private String URL = "https://openapi.alipay.com/gateway.do";
    private String APP_ID = "2016080300153208";//APPID即创建应用后生成	获取见上面创建应用并获取APPID
    //私钥(开发者应用私钥，由开发者自己生成	获取详见上面配置密钥)
    private String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCIKG+2xjhVh/7lckPsuokDHF1GYlloNQe66zvx7mb2jP1ZSPmvpQp+8olUnbQfKpTYiQ6Az3MtHBt427gUECw4igrKWR9sNggxLMV8h2xFwu4r8wrus/Brvya+SvQz49RMj3xvldtrkzRFJVn2ATlix1S/MZANvgmkeXgMZqQkA4pR25x/8ITE8s+YpVnLeNyyzEAEUnyEuDF+JqwzMKuPGgyC0nPE7ZdX688yqJfH/RDWfcvbya4REMU2IC0/Fb2lo4ym4WHkVYqJ6TOgPfku9r3mHjyRdFbM2OIZmaMb7S4LxaNq10+H+qU9agaK7lQ8BmTP2CU1AzXEJYtUm9HDAgMBAAECggEAKs3jiOc58JGnbYSFnBdsWWECZc0nZF4/huDK4JzdG6dpQjdx32kYKp6e084cQSwGzSgROid9gvHyJQWWEiCfdkrXZ5OrED7QRn1XvHVqcWf9ri1jA3XoIEdgq7Qun12BcWwuZ3oKGACYDyDV7kOmQcp+dvji258hsuZI+kUOVw+Y5oghef4jPuhLcyCiyAkXK//ewaoQ1znk0x2afqyLSiFUUFO58TgaWUkVfLRYp9xa/ZnnMLC+z8MmEI+4wUO0D7dBxKEVtv6gfi1qT0uN/yXxG8P0OtZL2Ja4m5rpe0WaJmOp1jjahXcpg6LnsgVr3b+U7aT2JseBrUxmXFcs8QKBgQDu07TOca3Q+FKItuQdallmyeabOsM/KQ8qeGPCDZdCaPQG1B8LgZCgKpkeUZjQ/5nMu6SbNOmhVMtlsly8e49gSHo2XYS7jQ+3nzgIesDR7A2k51Bu4giFaQJMfqEw18UOOHtBPGUOur8slbkuMud1VtbKoIAaN/OA9V3h5ayaSQKBgQCR8tAjR6IgUm2gafLDuHy/asqYwMCMukuTV5bvA6C3obk+tOzol9SNfpExgl6ysDhjTjdaJS4P/A0J4fNPv+2gwQIBNiEXNkd1PoollV5fQPkSz6YavNJUyi3SDP3gCsmpT+6Ldb84FmjGRDVCGN4BSV294Va6dn+wxZvqlTKrqwKBgQCeDPbL5IleEOg116sxGE9f6d+1/PZ3VwnVVmTWaD4g0eoklr6Q08bNaEN6wA88yNqUld2CZUrz3HTasWYTykWBN5XBYrRTli+/mhvv6KSwh+Ijrn0ZVHbFK1A9JVQxSan8Fj7jVj+ettGLhO5O95sbmUN+RydfsoVwY3Ek7OUEGQKBgHL1Q/aWBODarFjvOvXpCfGoRz48jS4Ly12aX1uSivQ5YXVAA19NwHFXITxTCQ5MeY3W3QiXQon6qbaAECtf7OdzP4X+wd/LtEtoYF4sIjJ7NfUYNCjZU/7PZXoPG6VuOduwByA4Nc8S76JDtYODnNJ8nGbk6HBkyD1P2XjT3h6TAoGAGDCsluL5yA2jySCJ1ZwQevdggY4DN8LhJsDuAEr5fhyloCBlOlbb5V8e+jcqhPLMPCtyLJa1pbtBEmSwd2WuNPSHpBG23AXMVaQFKLMc6zBdAZ18VO/UKW72P1cAx7CvIE5uWfawkVEIZjD40nf1iVpu06vO32xA/QTFfQ44S7s=";
    private String FORMAT = "json";//参数返回格式，只支持json	json（固定）
    private String CHARSET = "UTF-8";//请求和签名使用的字符编码格式，支持GBK和UTF-8
    //支付宝公钥
    private String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxA2H2Zuo2DT7oi04nhG/bjq8Ng67W8iVM4dZmVbSckgi7ZYWf3+9n7anI1cCme9FODt3tT/36YrFcPQ5uvZnRXEmYrS332r7jyLMUktQPIwbJiqDfJAnjFlvxFhpMPJcYi1lwZx2gCzDZLaFae6uMLsBJ1uPfvDTj8hTy2mxP/F9t4eEFHnJbo44PtfpGMovSpR16P8Z/8fcUxaJ3SVGKkn86BM66j7O2QvDWX7gFAOOYSwvM9EUE4CDG3OIv0/0cjXOi0q17+YIpmFtbp1CSMgwh/Kc15jY3M9IBVVY+R0OBcBxJslYUZoD/J2JYJAgzhEE4AJU8ddAdJLHy3ojGwIDAQAB";
    private String SIGN_TYPE = "RSA2";//商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2

    private Boolean IS_TEST = false;//是否测试环境

    public String getURL() {
        if (!getIS_TEST())//如果是测试环境，采用沙箱测试地址
            URL = "https://openapi.alipaydev.com/gateway.do";//沙箱测试链接地址
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getAPP_ID() {
        return APP_ID;
    }

    public void setAPP_ID(String APP_ID) {
        this.APP_ID = APP_ID;
    }

    public String getAPP_PRIVATE_KEY() {
        return APP_PRIVATE_KEY;
    }

    public void setAPP_PRIVATE_KEY(String APP_PRIVATE_KEY) {
        this.APP_PRIVATE_KEY = APP_PRIVATE_KEY;
    }

    public String getFORMAT() {
        return FORMAT;
    }

    public void setFORMAT(String FORMAT) {
        this.FORMAT = FORMAT;
    }

    public String getCHARSET() {
        return CHARSET;
    }

    public void setCHARSET(String CHARSET) {
        this.CHARSET = CHARSET;
    }

    public String getALIPAY_PUBLIC_KEY() {
        return ALIPAY_PUBLIC_KEY;
    }

    public void setALIPAY_PUBLIC_KEY(String ALIPAY_PUBLIC_KEY) {
        this.ALIPAY_PUBLIC_KEY = ALIPAY_PUBLIC_KEY;
    }

    public String getSIGN_TYPE() {
        return SIGN_TYPE;
    }

    public void setSIGN_TYPE(String SIGN_TYPE) {
        this.SIGN_TYPE = SIGN_TYPE;
    }

    public Boolean getIS_TEST() {
        return IS_TEST;
    }

    public void setIS_TEST(Boolean IS_TEST) {
        this.IS_TEST = IS_TEST;
    }
}

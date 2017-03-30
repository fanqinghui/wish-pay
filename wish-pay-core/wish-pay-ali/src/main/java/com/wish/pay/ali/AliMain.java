package com.wish.pay.ali;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.wish.pay.ali.common.AliPayConstants;
import com.wish.pay.ali.common.SceneEnum;
import com.wish.pay.ali.service.AliPayServiceImpl;
import com.wish.pay.common.model.TradePrecreate;
import com.wish.pay.common.model.result.TradeResult;
import com.wish.pay.common.service.PayService;
import com.wish.pay.common.utils.SerialNumberUtils;

import static org.apache.commons.lang.CharEncoding.UTF_8;

/**
 * @author fqh
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date ${date} ${time}
 */
public class AliMain {

    static AlipayClient alipayClient;

    static {
        //URL支付网关（固定）
        String URL; //"https://openapi.alipay.com/gateway.do";
        URL = "https://openapi.alipaydev.com/gateway.do";//沙箱测试链接地址
        String APP_ID = "2016080300153208";//APPID即创建应用后生成	获取见上面创建应用并获取APPID
        //私钥(开发者应用私钥，由开发者自己生成	获取详见上面配置密钥)
        String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCIKG+2xjhVh/7lckPsuokDHF1GYlloNQe66zvx7mb2jP1ZSPmvpQp+8olUnbQfKpTYiQ6Az3MtHBt427gUECw4igrKWR9sNggxLMV8h2xFwu4r8wrus/Brvya+SvQz49RMj3xvldtrkzRFJVn2ATlix1S/MZANvgmkeXgMZqQkA4pR25x/8ITE8s+YpVnLeNyyzEAEUnyEuDF+JqwzMKuPGgyC0nPE7ZdX688yqJfH/RDWfcvbya4REMU2IC0/Fb2lo4ym4WHkVYqJ6TOgPfku9r3mHjyRdFbM2OIZmaMb7S4LxaNq10+H+qU9agaK7lQ8BmTP2CU1AzXEJYtUm9HDAgMBAAECggEAKs3jiOc58JGnbYSFnBdsWWECZc0nZF4/huDK4JzdG6dpQjdx32kYKp6e084cQSwGzSgROid9gvHyJQWWEiCfdkrXZ5OrED7QRn1XvHVqcWf9ri1jA3XoIEdgq7Qun12BcWwuZ3oKGACYDyDV7kOmQcp+dvji258hsuZI+kUOVw+Y5oghef4jPuhLcyCiyAkXK//ewaoQ1znk0x2afqyLSiFUUFO58TgaWUkVfLRYp9xa/ZnnMLC+z8MmEI+4wUO0D7dBxKEVtv6gfi1qT0uN/yXxG8P0OtZL2Ja4m5rpe0WaJmOp1jjahXcpg6LnsgVr3b+U7aT2JseBrUxmXFcs8QKBgQDu07TOca3Q+FKItuQdallmyeabOsM/KQ8qeGPCDZdCaPQG1B8LgZCgKpkeUZjQ/5nMu6SbNOmhVMtlsly8e49gSHo2XYS7jQ+3nzgIesDR7A2k51Bu4giFaQJMfqEw18UOOHtBPGUOur8slbkuMud1VtbKoIAaN/OA9V3h5ayaSQKBgQCR8tAjR6IgUm2gafLDuHy/asqYwMCMukuTV5bvA6C3obk+tOzol9SNfpExgl6ysDhjTjdaJS4P/A0J4fNPv+2gwQIBNiEXNkd1PoollV5fQPkSz6YavNJUyi3SDP3gCsmpT+6Ldb84FmjGRDVCGN4BSV294Va6dn+wxZvqlTKrqwKBgQCeDPbL5IleEOg116sxGE9f6d+1/PZ3VwnVVmTWaD4g0eoklr6Q08bNaEN6wA88yNqUld2CZUrz3HTasWYTykWBN5XBYrRTli+/mhvv6KSwh+Ijrn0ZVHbFK1A9JVQxSan8Fj7jVj+ettGLhO5O95sbmUN+RydfsoVwY3Ek7OUEGQKBgHL1Q/aWBODarFjvOvXpCfGoRz48jS4Ly12aX1uSivQ5YXVAA19NwHFXITxTCQ5MeY3W3QiXQon6qbaAECtf7OdzP4X+wd/LtEtoYF4sIjJ7NfUYNCjZU/7PZXoPG6VuOduwByA4Nc8S76JDtYODnNJ8nGbk6HBkyD1P2XjT3h6TAoGAGDCsluL5yA2jySCJ1ZwQevdggY4DN8LhJsDuAEr5fhyloCBlOlbb5V8e+jcqhPLMPCtyLJa1pbtBEmSwd2WuNPSHpBG23AXMVaQFKLMc6zBdAZ18VO/UKW72P1cAx7CvIE5uWfawkVEIZjD40nf1iVpu06vO32xA/QTFfQ44S7s=";
        String FORMAT = "json";//参数返回格式，只支持json	json（固定）
        String CHARSET = UTF_8;//请求和签名使用的字符编码格式，支持GBK和UTF-8
        //支付宝公钥
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxA2H2Zuo2DT7oi04nhG/bjq8Ng67W8iVM4dZmVbSckgi7ZYWf3+9n7anI1cCme9FODt3tT/36YrFcPQ5uvZnRXEmYrS332r7jyLMUktQPIwbJiqDfJAnjFlvxFhpMPJcYi1lwZx2gCzDZLaFae6uMLsBJ1uPfvDTj8hTy2mxP/F9t4eEFHnJbo44PtfpGMovSpR16P8Z/8fcUxaJ3SVGKkn86BM66j7O2QvDWX7gFAOOYSwvM9EUE4CDG3OIv0/0cjXOi0q17+YIpmFtbp1CSMgwh/Kc15jY3M9IBVVY+R0OBcBxJslYUZoD/J2JYJAgzhEE4AJU8ddAdJLHy3ojGwIDAQAB";
        String SIGN_TYPE = "RSA2";//商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
        alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);

    }


    public static void main1(String[] argus) {
        //测试下订单
        String seriaNo = SerialNumberUtils.getSeriaNumber();
        //seriaNo = "NO3267436790827008";
        System.out.println("订单号：" + seriaNo);
  /*        boolean result = testPrecreate(seriaNo);
        result = true;
        if (true) {
            System.out.println("下单成功：" + seriaNo);
            while (true) {
                result = testQuery(seriaNo);
                if (result) break;
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                }
            }
            System.out.println("已经查询出来了订单-开始申请退款");
            boolean reFesult = testRefund(seriaNo);
            System.out.println(reFesult);
        }*/


        PayService payService = new AliPayServiceImpl(alipayClient);
        TradePrecreate tradePrecreate = new TradePrecreate();
        try {
            tradePrecreate.setOutTradeNo(seriaNo);
            tradePrecreate.setScene(SceneEnum.BarCode.getType());
            /*tradePrecreate.setSubject("测试购买抱枕");*/
            tradePrecreate.setBody("测试北欧卖包子");
            tradePrecreate.setStoreId("BJ_001");
            tradePrecreate.setTotalAmount("0.1");
            tradePrecreate.setTimeoutExpress("90m");
            TradeResult result = payService.createTradeOrder(tradePrecreate);
            System.out.println(result);
            if (result.isTradeSuccess()) {
                System.out.println(result.getQrCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试下单-等等支付
    static boolean testPrecreate(String seriaNo) {
        //阿里预下单接口
        AlipayTradePrecreateRequest precreateReques = new AlipayTradePrecreateRequest();


        precreateReques.setBizContent("{" + "    \"out_trade_no\":\"" + seriaNo + "\"," +
                "    \"scene\":\"bar_code\"," +
                "    \"auth_code\":\"28763443825664394\"," +
                "    \"subject\":\"手机\"," +
                "    \"store_id\":\"NJ_001\"," +
                //"    \"alipay_store_id\":\"2088102169682535\"," +
                "    \"timeout_express\":\"2m\"," + //2分钟
                "    \"total_amount\":\"0.11\"," +
                "    \"body\":\"购买手机模型预付费\"" +
                "  }"); //设置业务参数

        try {
            AlipayTradePrecreateResponse precreateResponse = alipayClient.execute(precreateReques);
            System.out.println(precreateResponse.getBody());
            return precreateResponse.getCode().equals(AliPayConstants.SUCCESS);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return false;
    }

    //测试查询
    static boolean testQuery(String seriaNo) {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();//创建API对应的request类
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + seriaNo + "\"}");// +  //out_trade_no 支付时传入的商户订单号，与trade_no必填一个
        //"\"trade_no\":\"" + trade_no + "\"}"); //trade_no支付宝交易号,与商户订单号out_trade_no不能同时为空
        try {
            System.out.println(request.getBizContent());
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            System.out.println(response.getBody());
            if (response.getCode().equals(AliPayConstants.SUCCESS)) {
                return true;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return false;
    }

    //测试退款
    static boolean testRefund(String seriaNo) {
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();//创建API对应的request类
        request.setBizContent("{" +
                "    \"out_trade_no\":\"" + seriaNo + "\"," +
                //"    \"trade_no\":\"2014112611001004680073956707\"," +
                //"    \"out_request_no\":\"1000001\"," +
                "    \"refund_amount\":\"0.11\"}"); //设置退款金额必须与订单金额一致
        try {
            Thread.sleep(5000);
            System.out.println(request.getBizContent());
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            System.out.println(response.getBody());
            if (response.getCode().equals(AliPayConstants.SUCCESS)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //当面付异步通知
    //当收银台调用预下单请求API生成二维码展示给用户后，用户通过手机扫描二维码进行支付，支付宝会将该笔订单的变更信息，沿着商户调用预下单请求时所传入的通知地址主动推送给商户。

}

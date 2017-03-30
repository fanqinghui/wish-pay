package com.wish.pay.wx;

import com.wish.pay.common.model.TradePrecreate;
import com.wish.pay.common.model.TradeQuery;
import com.wish.pay.common.model.TradeRefund;
import com.wish.pay.common.model.result.TradeQueryResult;
import com.wish.pay.common.model.result.TradeResult;
import com.wish.pay.common.model.result.RefundResult;
import com.wish.pay.common.utils.SerialNumberUtils;
import com.wish.pay.wx.service.WxPayServiceImpl;

/**
 * @author fqh
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date ${date} ${time}
 */
public class WxMain {

    static String preUrl = "https://api.mch.weixin.qq.com/sandboxnew";
    static String appid = "wx0598967789076e07";
    static String mch_id = "1230000109";
    static String device_info = "PCWEB";

    public static void main(String[] argus) {
        //测试下订单
        String seriaNo = SerialNumberUtils.getSeriaNumber();
        //seriaNo = "NO3267436790827008";
        System.out.println("订单号：" + seriaNo);
        //testPrecreate(seriaNo);//ok
        //testQuery(seriaNo);//ok
        //testClose(seriaNo);//ok
        //testRefund(seriaNo);//ok
        testQueryRefund(seriaNo);//ok
    }

    //测试下单-等等支付
    static boolean testPrecreate(String seriaNo) {
        TradePrecreate tradePrecreate = new TradePrecreate();
        try {
            tradePrecreate.setOutTradeNo(seriaNo);
            tradePrecreate.setSubject("测试购买抱枕");
            tradePrecreate.setBody("测试北欧卖包子");
            tradePrecreate.setStoreId("BJ_001");
            tradePrecreate.setTotalAmount("0.1");
            tradePrecreate.setTimeoutExpress("90m");
            WxPayServiceImpl wxPay = new WxPayServiceImpl(preUrl, appid, mch_id, device_info);
            TradeResult result = wxPay.createTradeOrder(tradePrecreate);
            System.out.println(result);
            if (result.isTradeSuccess()) {
                System.out.println(result.getQrCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //测试查询
    static boolean testQuery(String seriaNo) {
        WxPayServiceImpl wxPay = null;
        try {
            wxPay = new WxPayServiceImpl(preUrl, appid, mch_id, device_info);
            TradeQuery query = new TradeQuery();
            query.setOutTradeNo(seriaNo);
            TradeQueryResult result= wxPay.queryPreTradeOrder(query);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //测试关闭交易
    static boolean testClose(String seriaNo) {
        WxPayServiceImpl wxPay = null;
        try {
            wxPay = new WxPayServiceImpl(preUrl, appid, mch_id, device_info);
            TradeQuery query = new TradeQuery();
            query.setOutTradeNo(seriaNo);
            TradeResult result = wxPay.canalTradeOrder(query);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 测试退款
     *
     * @param seriaNo
     * @return
     */
    static boolean testRefund(String seriaNo) {
        WxPayServiceImpl wxPay = null;
        try {
            wxPay = new WxPayServiceImpl(preUrl, appid, mch_id, device_info);
            TradeRefund refund = new TradeRefund();
            refund.setOutTradeNo(seriaNo);
            refund.setRefundAmount("1.1");
            refund.setTradeAmount("1.1");
            refund.setOperatorId("test_001");
            refund.setOutRefundNo(SerialNumberUtils.getSeriaNumber());//退款id
            refund.setRefundReason("正常退款");
            TradeResult result = wxPay.refundTradeOrder(refund);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 查询退款--根据商户订单号
     *
     * @param seriaNo
     * @return
     */
    static boolean testQueryRefund(String seriaNo) {
        WxPayServiceImpl wxPay = null;
        try {
            wxPay = new WxPayServiceImpl(preUrl, appid, mch_id, device_info);
            TradeRefund refund = new TradeRefund();
            refund.setOutTradeNo(seriaNo);
            RefundResult result = wxPay.queryRefundTradeOrder(refund);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

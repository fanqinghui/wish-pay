package com.wish.pay.wx.service;

import com.wish.pay.common.model.Contains;
import com.wish.pay.common.model.TradePrecreate;
import com.wish.pay.common.model.TradeQuery;
import com.wish.pay.common.model.TradeRefund;
import com.wish.pay.common.model.result.TradeQueryResult;
import com.wish.pay.common.model.result.TradeResult;
import com.wish.pay.common.model.result.RefundResult;
import com.wish.pay.common.model.result.RefundStatusEnum;
import com.wish.pay.common.service.PayService;
import com.wish.pay.common.utils.http.HttpClientUtils;
import com.wish.pay.common.utils.validator.ValidationResult;
import com.wish.pay.common.utils.validator.ValidationUtils;
import com.wish.pay.wx.common.Config;
import com.wish.pay.wx.common.WxPayInterface;
import com.wish.pay.wx.common.WxUtils;
import com.wish.pay.wx.model.pay_closeorder.CloseOrderReqData;
import com.wish.pay.wx.model.pay_closeorder.CloseOrderResData;
import com.wish.pay.wx.model.pay_orderquery.OrderQueryReqData;
import com.wish.pay.wx.model.pay_orderquery.OrderQueryResData;
import com.wish.pay.wx.model.pay_refund.RefundPayReqData;
import com.wish.pay.wx.model.pay_refund.RefundPayResData;
import com.wish.pay.wx.model.pay_refundquery.RefundQueryPayReqData;
import com.wish.pay.wx.model.pay_refundquery.RefundQueryPayResData;
import com.wish.pay.wx.model.pay_uifiedorder.ScanPayReqData;
import com.wish.pay.wx.model.pay_uifiedorder.ScanPayResData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/14 14:40
 */
public class WxPayServiceImpl implements PayService {


    Logger logger = LoggerFactory.getLogger(WxPayServiceImpl.class);
    BigDecimal yuanToFen = new BigDecimal(100);

    //1）被扫支付API
    public static String PAY_API = "https://api.mch.weixin.qq.com/pay/micropay";
    //2）被扫支付查询API
    public static String PAY_QUERY_API = "https://api.mch.weixin.qq.com/pay/orderquery";
    //3）退款API
    public static String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    //4）退款查询API
    public static String REFUND_QUERY_API = "https://api.mch.weixin.qq.com/pay/refundquery";
    //5）撤销API
    public static String REVERSE_API = "https://api.mch.weixin.qq.com/secapi/pay/reverse";
    //6）下载对账单API
    public static String DOWNLOAD_BILL_API = "https://api.mch.weixin.qq.com/pay/downloadbill";
    //7) 统计上报API
    public static String REPORT_API = "https://api.mch.weixin.qq.com/payitil/report";

    private static HttpClientUtils httpClientUtils;
    private Config config;

    public WxPayServiceImpl(String preUrl, String appId, String mchId, String deviceInfo) throws Exception {
        if (StringUtils.isBlank(appId) || StringUtils.isBlank(mchId)) {
            throw new Exception("appid 或者mchid为null，请检查配置");
        }
        config = new Config(preUrl, appId, mchId, deviceInfo);
    }

    public WxPayServiceImpl(String preUrl, String appId, String mchId, String deviceInfo, String sign_type) throws Exception {
        if (StringUtils.isBlank(appId) || StringUtils.isBlank(mchId)) {
            throw new Exception("appid 或者mchid为null，请检查配置");
        }
        config = new Config(preUrl, appId, mchId, deviceInfo, sign_type);
    }

    /**
     * 当面付2.0预下单(生成二维码)
     * 创建订单（ali wx 创建订单）
     *
     * @param trade
     * @return
     */
    @Override
    public TradeResult createTradeOrder(TradePrecreate trade) throws Exception {
        return createTradeOrder(trade, null);
    }

    /**
     * 当面付2.0预下单，生成二维码
     *
     * @param trade
     * @param notifyResultUrl
     * @return
     * @throws Exception
     */
    @Override
    public TradeResult createTradeOrder(TradePrecreate trade, String notifyResultUrl) throws Exception {
        TradeResult result = new TradeResult();
        String unifiedorderUrl = config.getPreUrl() + WxPayInterface.WxpayTrade_Unifiedorder.getMethod();
        logger.info("微信下单地址是：", unifiedorderUrl);
        //组装xml
        //时间
        long start = System.currentTimeMillis();
        String startTime = WxUtils.getDateByLongTime(start);
        String endTime = WxUtils.addMinTime(start, trade.getTimeoutExpress());
        int money = new BigDecimal(trade.getTotalAmount()).multiply(yuanToFen).intValue();
        System.out.print(money);
        //appid, mchId, deviceInfo,,key, body, attach, outTradeNo, int totalFee,  spBillCreateIP, timeStart, timeExpire) {
        ScanPayReqData xmlObj = new ScanPayReqData(
                config.getAppid(),
                config.getMch_id(),
                config.getDevice_info(),
                config.getKey(),
                trade.getSubject(),//对应微信离得subject
                trade.getBody(),//对应attach
                trade.getOutTradeNo(),
                new BigDecimal(trade.getTotalAmount()).multiply(yuanToFen).intValue(),
                "127.0.0.1",
                startTime,
                endTime);
        if (StringUtils.isNotBlank(notifyResultUrl)) {
            xmlObj.setNotify_url(notifyResultUrl);
        }
        //http请求
        String resXml = httpClientUtils.doPost(unifiedorderUrl, xmlObj);
        logger.info("请求处理返回结果xml:，", resXml);
        //处理结果
        ScanPayResData resData = (ScanPayResData) WxUtils.getObjectFromXML(resXml, ScanPayResData.class);
        if (resData != null) {
            if (resData.getResult_code().equalsIgnoreCase(Contains.SUCCESS) ||
                    resData.getReturn_code().equalsIgnoreCase(Contains.SUCCESS)) {
                result.setTradeStatus(Contains.TradeStatus.SUCCESS);
                result.setQrCode(resData.getCode_url());
            } else {
                result.setTradeStatus(Contains.TradeStatus.FAILED);
            }
            result.setMsg(resData.getReturn_msg());
        }
        return result;
    }

    /**
     * 取消预支付订单
     *
     * @param trade
     * @return
     * @throws Exception
     */
    @Override
    public TradeResult canalTradeOrder(TradeQuery trade) throws Exception {
        TradeResult result = new TradeResult();
        String unifiedorderUrl = config.getPreUrl() + WxPayInterface.WxpayTrade_Closeorder.getMethod();
        logger.info("微信关闭订单地址是：", unifiedorderUrl);
        //组装xml
        //时间
        //appid, mchId, deviceInfo,,key,outTradeNo) {
        CloseOrderReqData xmlObj = new CloseOrderReqData(
                config.getAppid(),
                config.getMch_id(),
                config.getDevice_info(),
                config.getKey(),
                trade.getOutTradeNo());
        //http请求
        String resXml = httpClientUtils.doPost(unifiedorderUrl, xmlObj);
        logger.info("请求处理返回结果xml:，", resXml);
        //处理结果
        CloseOrderResData resData = (CloseOrderResData) WxUtils.getObjectFromXML(resXml, CloseOrderResData.class);
        if (resData != null) {
            if (resData.getResult_code().equalsIgnoreCase(Contains.SUCCESS) ||
                    resData.getReturn_code().equalsIgnoreCase(Contains.SUCCESS)) {
                result.setTradeStatus(Contains.TradeStatus.SUCCESS);
            } else {
                result.setTradeStatus(Contains.TradeStatus.FAILED);
            }
            result.setMsg(resData.getReturn_msg());
        }
        return result;
    }

    /**
     * 申请退款接口
     *
     * @param refund
     * @return
     * @throws Exception
     */
    @Override
    public TradeResult refundTradeOrder(TradeRefund refund) throws Exception {
        ValidationResult validationResult = ValidationUtils.validateEntity(refund);
        if (validationResult.isHasErrors()) {
            logger.error("[refundTradeOrder] 申请退款接口参数出错", validationResult.getErrorMsg().toString());
            throw new Exception(validationResult.getErrorMsg().toString());
        }

        TradeResult result = new TradeResult();
        String unifiedorderUrl = config.getPreUrl() + WxPayInterface.WxpayTrade_Closeorder.getMethod();
        logger.info("微信关闭订单地址是：", unifiedorderUrl);
        int totalFee = new BigDecimal(refund.getRefundAmount()).multiply(yuanToFen).intValue();
        int refundFee = new BigDecimal(refund.getRefundAmount()).multiply(yuanToFen).intValue();
        //组装xml

        //appid, mchId, deviceInfo, key, outTradeNo, transactionId, outRefundNo, int totalFee, int refundFee, opUserId
        RefundPayReqData xmlObj = new RefundPayReqData(
                config.getAppid(),
                config.getMch_id(),
                config.getDevice_info(),
                config.getKey(),
                refund.getOutTradeNo(),
                refund.getTradeNo(),
                refund.getOutRefundNo(),
                totalFee,
                refundFee,
                refund.getOperatorId()
        );
        //http请求
        String resXml = httpClientUtils.doPost(unifiedorderUrl, xmlObj);
        logger.info("请求处理返回结果xml:，", resXml);
        //处理结果
        RefundPayResData resData = (RefundPayResData) WxUtils.getObjectFromXML(resXml, RefundPayResData.class);
        if (resData != null) {
            if (resData.getResult_code().equalsIgnoreCase(Contains.SUCCESS) ||
                    resData.getReturn_code().equalsIgnoreCase(Contains.SUCCESS)) {
                result.setTradeStatus(Contains.TradeStatus.SUCCESS);
            } else {
                result.setTradeStatus(Contains.TradeStatus.FAILED);
            }
            result.setMsg(resData.getReturn_msg());
        }
        return result;
    }

    /**
     * 查询申请退款接口
     *
     * @param refund
     * @return
     * @throws Exception
     */
    @Override
    public RefundResult queryRefundTradeOrder(TradeRefund refund) throws Exception {
        RefundResult result = new RefundResult();
        String url = config.getPreUrl() + WxPayInterface.WxpayTrade_Refundquery.getMethod();
        logger.info("微信查退款信息的地址是：", url);

        //组装xml

        //appid, mchId, deviceInfo, key, outTradeNo, transactionId, outRefundNo, String refundId
        RefundQueryPayReqData xmlObj = new RefundQueryPayReqData(
                config.getAppid(),
                config.getMch_id(),
                config.getDevice_info(),
                config.getKey(),
                refund.getOutTradeNo(),
                refund.getTransactionId(),
                refund.getOutRefundNo(),
                refund.getRefundId()
        );
        //http请求
        String resXml = httpClientUtils.doPost(url, xmlObj);
        logger.info("请求处理返回结果xml:，", resXml);
        //处理结果
        RefundQueryPayResData resData = (RefundQueryPayResData) WxUtils.getObjectFromXML(resXml, RefundQueryPayResData.class);
        if (resData != null) {
            if (resData.getResult_code().equalsIgnoreCase(Contains.SUCCESS) &&
                    resData.getReturn_code().equalsIgnoreCase(Contains.SUCCESS)) {
                result.setTradeStatus(Contains.TradeStatus.SUCCESS);
                result.setRefundAmount(resData.getTotal_fee() * 100);
                result.setTotalAmount(resData.getTotal_fee() * 100);
                String status = resData.getRefund_status_0();
                result.setRefundStatus(RefundStatusEnum.getStatusValue(status));
            } else {
                result.setTradeStatus(Contains.TradeStatus.FAILED);
            }
            result.setMsg(resData.getReturn_msg());
        }
        return result;
    }

    /**
     * 异步通知进行通知
     *
     * @param request
     * @param AliPayPublicKey
     * @return
     */
    @Override
    public Map<String, String> notifyResult(HttpServletRequest request, String AliPayPublicKey) {
        return null;
    }

    /**
     *  查询预支付订单
     * @param tradeQuery
     * out_trade_no	String	特殊可选	64	订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no	20150320010101001
     * trade_no	String	特殊可选	64	支付宝交易号，和商户订单号不能同时为空	2014112611001004680 073956707
     * @return
     */
    @Override
    public TradeQueryResult queryPreTradeOrder(TradeQuery tradeQuery) throws Exception {
        String unifiedorderUrl = config.getPreUrl() + WxPayInterface.WxpayTrade_Orderquery.getMethod();
        logger.info("微信查询订单地址是：", unifiedorderUrl);
        //组装xml
        //appid, mchId, deviceInfo, key, outTradeNo,transaction_id
        OrderQueryReqData xmlObj = new OrderQueryReqData(
                config.getAppid(),
                config.getMch_id(),
                config.getDevice_info(),
                config.getKey(),
                tradeQuery.getOutTradeNo(),
                tradeQuery.getTradeNo());
        //http请求
        String resXml = httpClientUtils.doPost(unifiedorderUrl, xmlObj);
        logger.info("请求处理返回结果xml:，", resXml);
        //处理结果
        OrderQueryResData resData = (OrderQueryResData) WxUtils.getObjectFromXML(resXml, OrderQueryResData.class);

        TradeQueryResult result=new TradeQueryResult();
        if (resData != null) {
            if (resData.getResult_code().equalsIgnoreCase(Contains.SUCCESS) ||
                    resData.getReturn_code().equalsIgnoreCase(Contains.SUCCESS)) {
                //return "商户订单号:" + resData.getOut_trade_no() + "交易状态：" + resData.getTrade_state() + "";
                result.setOutTradeNo(resData.getOut_trade_no());
                result.setTradeNo(resData.getTransaction_id());//微信支付订单号
                result.setTradeCode(resData.getTrade_state());
                result.setTradeStatus(Contains.TradeStatus.SUCCESS);
                result.setMsg(resData.getReturn_msg());
                result.setTotalAmount(resData.getTotal_fee()*100+"");
            } else {
                //return "查询返回信息：" + resData.getReturn_msg() + "业务结果：" + resData.getResult_code() + "|" + resData.getErr_code_des();
                result.setTradeStatus(Contains.TradeStatus.FAILED);
                result.setMsg(resData.getErr_code_des());
            }
        }
        return result;
    }
}

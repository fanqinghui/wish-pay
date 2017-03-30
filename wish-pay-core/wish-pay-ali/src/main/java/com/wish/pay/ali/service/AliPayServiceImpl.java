package com.wish.pay.ali.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.wish.pay.ali.AliPayUtil;
import com.wish.pay.ali.common.AliPayConstants;
import com.wish.pay.common.model.Contains;
import com.wish.pay.common.model.TradePrecreate;
import com.wish.pay.common.model.TradeQuery;
import com.wish.pay.common.model.TradeRefund;
import com.wish.pay.common.model.result.TradeQueryResult;
import com.wish.pay.common.model.result.TradeResult;
import com.wish.pay.common.model.result.RefundResult;
import com.wish.pay.common.service.PayService;
import com.wish.pay.common.utils.JsonUtils;
import com.wish.pay.common.utils.validator.ValidationResult;
import com.wish.pay.common.utils.validator.ValidationUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/1 16:42
 */
public class AliPayServiceImpl implements PayService {

    Logger logger = LoggerFactory.getLogger(AliPayServiceImpl.class);

    private AlipayClient alipayClient;

    public AliPayServiceImpl(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }

    /**
     * 创建订单
     *
     * @param tradePrecreate
     * @return
     */
    @Override
    public TradeResult createTradeOrder(TradePrecreate tradePrecreate) throws Exception {
        return createTradeOrder(tradePrecreate, null);
    }

    /**
     * 创建订单
     *
     * @param tradePrecreate
     * @return
     */
    @Override
    public TradeResult createTradeOrder(TradePrecreate tradePrecreate, String notifyResultUrl) throws Exception {
        ValidationResult validationResult = ValidationUtils.validateEntity(tradePrecreate);
        if (validationResult.isHasErrors()) {
            logger.error("[createTradeOrder] 创建订单参照验证错误", validationResult.getErrorMsg().toString());
            throw new Exception(validationResult.getErrorMsg().toString());
        }
        //阿里预下单接口
        AlipayTradePrecreateRequest precreateReques = new AlipayTradePrecreateRequest();
        //自动构建json
        String bizJson = JsonUtils.toJson(tradePrecreate);
        System.out.println(bizJson);
        if (StringUtils.isNotBlank(notifyResultUrl)) {
            precreateReques.setNotifyUrl(notifyResultUrl);//配置的回调通知信息
        }
        precreateReques.setBizContent(bizJson);

        TradeResult result = new TradeResult();
        try {
            AlipayTradePrecreateResponse precreateResponse = alipayClient.execute(precreateReques);
            logger.info("[createTradeOrder]返回结果:", precreateResponse.getBody());
            System.out.println(precreateResponse.getBody());
            if (precreateResponse.getCode().equals(AliPayConstants.SUCCESS) &&
                    precreateResponse.getMsg().equals("Success")) {
                result.setTradeStatus(Contains.TradeStatus.SUCCESS);
                result.setMsg(precreateResponse.getMsg());
                result.setQrCode(precreateResponse.getQrCode());
            }
        } catch (AlipayApiException e) {
            result.setTradeStatus(Contains.TradeStatus.UNKNOWN);
            logger.error("[createTradeOrder]提交订单AlipayApiException异常：", e);
        }
        return result;
    }

    @Override
    public Map<String, String> notifyResult(HttpServletRequest request, String AliPayPublicKey) {
        Map<String, String> params = AliPayUtil.getAlipayNotify(request);
        System.out.println(params);
        boolean signResult = AliPayUtil.verifySignWithRSA(params, AliPayPublicKey);
        if (signResult) {//验签正确
            params.put(Contains.WishPayResultKey, Contains.SUCCESS);
        }
        return params;
    }

    /**
     * 查询预支付订单
     *
     * @param tradeQuery
     * @return
     * @throws Exception
     */
    public TradeQueryResult queryPreTradeOrder(TradeQuery tradeQuery) throws Exception {
        ValidationResult validationResult = ValidationUtils.validateEntity(tradeQuery);
        if (validationResult.isHasErrors()) {
            logger.error("[queryPreTradeOrder] 查询订单参照验证错误", validationResult.getErrorMsg().toString());
            throw new Exception(validationResult.getErrorMsg().toString());
        }
        TradeQueryResult result=new TradeQueryResult();
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();//创建API对应的request类
        String bizJson = JsonUtils.toJson(tradeQuery);
        System.out.println(bizJson);
        request.setBizContent(bizJson);// +  //out_trade_no 支付时传入的商户订单号，与trade_no必填一个
        //"\"trade_no\":\"" + trade_no + "\"}"); //trade_no支付宝交易号,与商户订单号out_trade_no不能同时为空
        try {
            System.out.println(request.getBizContent());
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            //return "商户订单号：" + response.getOutTradeNo() + " 购买金额为：" + response.getTotalAmount() + "交易状态是："
            // + response.getTradeStatus() + "查询结果" + response.getMsg() + "代码:" + response.getCode();
            if(response.isSuccess()){
                result.setMsg(response.getMsg());
                result.setTradeNo(response.getTradeNo());
                result.setOutTradeNo(response.getOutTradeNo());
                result.setTotalAmount(response.getTotalAmount());
                result.setTradeStatus(Contains.TradeStatus.SUCCESS);
                result.setTradeCode(response.getTradeStatus());
            }else {
                response.setMsg("调用失败");
            }
        } catch (AlipayApiException e) {
            logger.error("[queryPreTradeOrder]查询预支付订单出错:", e);
        }
        return result;
    }


    /**
     * 取消预支付订单
     *
     * @param tradeQuery
     * @return
     * @throws Exception
     */
    @Override
    public TradeResult canalTradeOrder(TradeQuery tradeQuery) throws Exception {
        ValidationResult validationResult = ValidationUtils.validateEntity(tradeQuery);
        if (validationResult.isHasErrors()) {
            logger.error("[canalTradeOrder] 取消订单参数验证错误", validationResult.getErrorMsg().toString());
            throw new Exception(validationResult.getErrorMsg().toString());
        }
        TradeResult result = new TradeResult();
        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
        String bizJson = JsonUtils.toJson(tradeQuery);
        request.setBizContent(bizJson);// +  //out_trade_no 支付时传入的商户订单号，与trade_no必填一个
        //"\"trade_no\":\"" + trade_no + "\"}"); //trade_no支付宝交易号,与商户订单号out_trade_no不能同时为空
        AlipayTradeCancelResponse response = alipayClient.execute(request);
        logger.info("取消支付订单调用接口", response);
        if (response.isSuccess()) {
            result.setTradeStatus(Contains.TradeStatus.SUCCESS);
        }
        result.setMsg(response.getMsg());
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
            logger.error("[refundTradeOrder] 申请退款参数验证错误", validationResult.getErrorMsg().toString());
            throw new Exception(validationResult.getErrorMsg().toString());
        }
        TradeResult result = new TradeResult();
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        String bizJson = JsonUtils.toJson(refund);
        request.setBizContent(bizJson);
         /*"{" +
            "\"out_trade_no\":\"20150320010101001\"," +
            "    \"trade_no\":\"2014112611001004680073956707\"," +
            "    \"refund_amount\":200.12," +
            "    \"refund_reason\":\"正常退款\"," +
            "    \"out_request_no\":\"HZ01RF001\"," +
            "    \"operator_id\":\"OP001\"," +
            "    \"store_id\":\"NJ_S_001\"," +
            "    \"terminal_id\":\"NJ_T_001\"" +
            "  }"*/
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        logger.info("申请退款接口接口返回结果：", response);
        if (response.isSuccess()) {
            result.setTradeStatus(Contains.TradeStatus.SUCCESS);
        }
        result.setMsg("code:" + response.getMsg() + " detail:" + response.getSubMsg());
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
        /*ValidationResult validationResult = ValidationUtils.validateEntity(refund);
        if (validationResult.isHasErrors()) {
            logger.error("[refundTradeOrder] 申请退款参数验证错误", validationResult.getErrorMsg().toString());
            throw new Exception(validationResult.getErrorMsg().toString());
        }*/
        if (refund == null || StringUtils.isBlank(refund.getOutTradeNo()) || StringUtils.isBlank(refund.getOutRequestNo())) {
            logger.error("[refundTradeOrder] 申请退款参数验证错误", "查询退款 商户交易号或者退款号出错");
            throw new Exception("查询退款 商户交易号或者退款号出错");
        }
        RefundResult result = new RefundResult();
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        String bizJson = JsonUtils.toJson(refund);
        request.setBizContent(bizJson);
        /*"{" +
                "    \"trade_no\":\"20150320010101001\"," +
                "    \"out_trade_no\":\"2014112611001004680073956707\"," +
                "    \"out_request_no\":\"2014112611001004680073956707\"" +
                "  }"*/
        AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
        logger.info("查询申请退款接口接口返回结果：", response);
        if (response.isSuccess()) {
            result.setTradeStatus(Contains.TradeStatus.SUCCESS);
        }
        result.setMsg("code:" + response.getMsg() + " detail:" + response.getSubMsg());
        return result;
    }


    public AlipayClient getAlipayClient() {
        return alipayClient;
    }

    public void setAlipayClient(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }
}

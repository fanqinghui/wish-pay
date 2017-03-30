package com.wish.pay.web.controller;

import com.wish.pay.ali.common.SceneEnum;
import com.wish.pay.ali.service.AliPayServiceImpl;
import com.wish.pay.common.model.*;
import com.wish.pay.common.model.result.RefundResult;
import com.wish.pay.common.model.result.TradeQueryResult;
import com.wish.pay.common.model.result.TradeResult;
import com.wish.pay.common.utils.BeanMapper;
import com.wish.pay.common.utils.TradeStatusEnum;
import com.wish.pay.common.utils.ZxingUtils;
import com.wish.pay.common.utils.validator.ValidationResult;
import com.wish.pay.common.utils.validator.ValidationUtils;
import com.wish.pay.web.bean.CreatePayBean;
import com.wish.pay.web.bean.QueryRefundTrade;
import com.wish.pay.web.bean.QueryTradeStatus;
import com.wish.pay.web.bean.RefundTrade;
import com.wish.pay.web.dao.entity.TradeOrder;
import com.wish.pay.web.exception.BusinessException;
import com.wish.pay.web.service.ITradeOrderService;
import com.wish.pay.wx.model.common.ProtocolResData;
import com.wish.pay.wx.service.WxPayServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Map;


@Controller
@RequestMapping("/pay")
public class PayController {

    Logger logger = LoggerFactory.getLogger(PayController.class);
    @Autowired
    ITradeOrderService tradeOrderService;

    @Autowired
    AliPayServiceImpl aliPayService;
    @Autowired
    WxPayServiceImpl wxPayService;

    @Value("${aliPay.notifyResultUrl}")
    String ALiPayNotifyResultUrl;
    @Value("${wxPay.notifyResultUrl}")
    String WXPayNotifyResultUrl;
    @Value("${aliPay.alipayPublicKey}")
    String AliPayPublicKey;

    /**
     * 创建阿里支付订单
     *
     * @param createPay
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/createPay", method = {RequestMethod.POST, RequestMethod.GET}, produces = "image/jpeg;charset=UTF-8")
    @ResponseBody
    public byte[] createPay(CreatePayBean createPay) throws Exception {
        //1.校验参数
        ValidationResult result = ValidationUtils.validateEntity(createPay);
        if (result.isHasErrors()) {
            throw new BusinessException(result.toString());
        }
        TradeOrder order = new TradeOrder();
        BeanMapper.copy(createPay, order);
        //2.插入订单记录
        boolean insertResult = tradeOrderService.save(order);
        //3.调用支付宝，创建订单，返回参数（订单支付url）
        if (insertResult) {
            TradePrecreate tradePrecreate = new TradePrecreate();
            tradePrecreate.setOutTradeNo(order.getOrderSerial());
            tradePrecreate.setScene(SceneEnum.BarCode.getType());
            tradePrecreate.setSubject("购买" + order.getGoodsName());
            tradePrecreate.setStoreId("test_001");
            tradePrecreate.setTotalAmount(order.getAmount().toString());
            tradePrecreate.setTimeoutExpress("90m");
            TradeResult tradeResult = null;
            if (createPay.getPayWay().equalsIgnoreCase(PayTypeEnum.AliPay.getType())) {
                tradeResult = aliPayService.createTradeOrder(tradePrecreate, ALiPayNotifyResultUrl);
            } else if (createPay.getPayWay().equalsIgnoreCase(PayTypeEnum.WxPay.getType())) {
                tradeResult = wxPayService.createTradeOrder(tradePrecreate, ALiPayNotifyResultUrl);
            }
            if (tradeResult != null && tradeResult.isTradeSuccess()) {//支付成功
                BufferedImage bufferedImage = ZxingUtils.writeInfoToJpgBuffImg(tradeResult.getQrCode(), 400, 400);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "JPEG", baos);
                return baos.toByteArray();
            }
        }
        return null;
    }


    /**
     * 查询交易状态
     *
     * @param tradeStatus
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryTradeOrderStatus", method = {RequestMethod.GET})
    @ResponseBody
    public TradeQueryResult queryTradeOrderStatus(QueryTradeStatus tradeStatus) throws Exception {
        ValidationResult result = ValidationUtils.validateEntity(tradeStatus);
        if (result.isHasErrors()) {
            throw new BusinessException(result.toString());
        }
        TradeQuery query = new TradeQuery();
        query.setOutTradeNo(tradeStatus.getOrderSerial());
        if (tradeStatus.getPayWay().equals("0")) {//支付宝
            return aliPayService.queryPreTradeOrder(query);
        } else if (tradeStatus.getPayWay().equals("1")) {//微信
            return wxPayService.queryPreTradeOrder(query);
        }
        return null;
    }


    /**
     * 交易退款
     *
     * @param refundTrade
     * @return
     */
    @RequestMapping(value = "/refundTradeOrder", method = {RequestMethod.GET})
    @ResponseBody
    public String refundTradeOrder(RefundTrade refundTrade) throws Exception {
        ValidationResult result = ValidationUtils.validateEntity(refundTrade);
        if (result.isHasErrors()) {
            throw new BusinessException(result.toString());
        }
        TradeRefund refund = new TradeRefund();
        BeanMapper.copy(refundTrade, refund);
        TradeResult tradeResult = new TradeResult();
        if (refundTrade.getPayWay().equals("0")) {//支付宝支付宝
            tradeResult = aliPayService.refundTradeOrder(refund);
            logger.info("[refundTradeOrder][alipay]退款返回结果", tradeResult);
        } else if (refundTrade.getPayWay().equals("1")) {//微信
            tradeResult = wxPayService.refundTradeOrder(refund);
            logger.info("[refundTradeOrder][wxPay]退款返回结果", tradeResult);
        }
        return tradeResult.getMsg();
    }

    /**
     * 查询交易退款
     *
     * @param queryRefundTrade
     * @return
     */
    @RequestMapping(value = "/queryRefundTradeOrder", method = {RequestMethod.GET})
    @ResponseBody
    public String queryRefundTradeOrder(QueryRefundTrade queryRefundTrade) throws Exception {
        ValidationResult result = ValidationUtils.validateEntity(queryRefundTrade);
        if (result.isHasErrors()) {
            throw new BusinessException(result.toString());
        }
        TradeRefund refund = new TradeRefund();
        BeanMapper.copy(queryRefundTrade, refund);
        RefundResult tradeResult = new RefundResult();
        if (queryRefundTrade.getPayWay().equals("0")) {//支付宝
            tradeResult = aliPayService.queryRefundTradeOrder(refund);
            logger.info("[refundTradeOrder][alipay]退款返回结果", tradeResult);
        } else if (queryRefundTrade.getPayWay().equals("1")) {//微信
            tradeResult = wxPayService.queryRefundTradeOrder(refund);
            logger.info("[refundTradeOrder][wxPay]退款返回结果", tradeResult);
        }
        return tradeResult.getMsg();
    }

    /**
     * 微信通知接口
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/notifyFromWx", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ProtocolResData notifyFromWx(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("notifyFromWx begin--->", request);
        System.out.println("notifyFromWx" + request.toString());
        ProtocolResData resData = new ProtocolResData();
        try {
            Map<String, String> resultMap = aliPayService.notifyResult(request, AliPayPublicKey);
            String value = resultMap.get(Contains.WishPayResultKey);
            if (StringUtils.isNotBlank(value) && value.equalsIgnoreCase(Contains.SUCCESS)) {
                //TODO:业务逻辑处理
            /*商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
            同时需要校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
            上述有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
            在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
            在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。*/
                String out_trade_no = resultMap.get("out_trade_no");
                String total_amount = resultMap.get("total_amount");
                String trade_status = resultMap.get("trade_status");
                TradeOrder tradeOrder = tradeOrderService.getTradeOrderByOrderSerial(out_trade_no);
                System.out.println((tradeOrder.getAmount().doubleValue() + "").equals(total_amount));
                if (tradeOrder != null && (tradeOrder.getAmount().doubleValue() + "").equals(total_amount) &&
                        (trade_status.equals("TRADE_SUCCESS") || trade_status.equals("TRADE_FINISHED"))) {
                    tradeOrder.setStatus(TradeStatusEnum.PAY_SUCCESS.getValue());//交易成功
                    boolean updateResult = tradeOrderService.update(tradeOrder);
                    if (!updateResult) {
                        logger.error("流水号：" + out_trade_no + " 更新状态更新出错");
                        throw new Exception("业务出错");
                    }
                }
            }
            resData.setReturn_code("SUCCESS");
            resData.setReturn_msg("OK");
        } catch (Exception e) {
            resData.setReturn_code("Fail");
            resData.setReturn_msg("NOOK");
            resData.setReturn_msg("程序业务处理失败");
            e.printStackTrace();
            //return WxUtils.notifyFalseXml();
        }
        return resData;
    }


    /**
     * 支付宝请求此接口，是post方式，而且
     * 支付宝通知接口,如果成功则会输出success，
     * 程序执行完后必须打印输出“success”（不包含引号）否则支付宝服务器会不断重发通知，直到超过24小时22分钟。
     * 一般情况下，25小时以内完成8次通知（通知的间隔频率一般是：4m,10m,10m,1h,2h,6h,15h）；
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/notifyFromAli", method = {RequestMethod.POST})
    @ResponseBody
    public String notifyFromAli(HttpServletRequest request) throws Exception {
        logger.info("notifyFromAli begin--->", request);
        System.out.println("notifyFromAli" + request.toString());
        Map<String, String> resultMap = aliPayService.notifyResult(request, AliPayPublicKey);
        String value = resultMap.get(Contains.WishPayResultKey);
        if (StringUtils.isNotBlank(value) && value.equalsIgnoreCase(Contains.SUCCESS)) {
            //TODO:业务逻辑处理
            /*商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
            同时需要校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
            上述有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
            在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
            在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。*/
            String out_trade_no = resultMap.get("out_trade_no");
            String total_amount = resultMap.get("total_amount");
            String trade_status = resultMap.get("trade_status");
            TradeOrder tradeOrder = tradeOrderService.getTradeOrderByOrderSerial(out_trade_no);
            System.out.println((tradeOrder.getAmount().doubleValue() + "").equals(total_amount));
            if (tradeOrder != null && (tradeOrder.getAmount().doubleValue() + "").equals(total_amount) &&
                    (trade_status.equals("TRADE_SUCCESS") || trade_status.equals("TRADE_FINISHED"))) {
                tradeOrder.setStatus(TradeStatusEnum.PAY_SUCCESS.getValue());//交易成功
                boolean updateResult = tradeOrderService.update(tradeOrder);
                if (!updateResult) {
                    logger.error("流水号：" + out_trade_no + " 更新状态更新出错");
                    throw new Exception("业务出错");
                }
            }
        }
        return value;
    }


}
package com.wish.pay.common.service;

import com.wish.pay.common.model.TradePrecreate;
import com.wish.pay.common.model.TradeQuery;
import com.wish.pay.common.model.TradeRefund;
import com.wish.pay.common.model.result.TradeQueryResult;
import com.wish.pay.common.model.result.TradeResult;
import com.wish.pay.common.model.result.RefundResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 支付接口service
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/2/27 15:17
 */
public interface PayService {

   /* // 当面付2.0流程支付
    public PayResult tradePay(AlipayTradePayRequestBuilder builder);

    // 当面付2.0消费查询
    public AlipayF2FQueryResult queryTradeResult(AlipayTradeQueryRequestBuilder builder);

    // 当面付2.0消费退款
    public AlipayF2FRefundResult tradeRefund(AlipayTradeRefundRequestBuilder builder);
*/

    /**
     * 当面付2.0预下单(生成二维码)
     * 创建订单（ali wx 创建订单）
     *
     * @param tradePrecreate
     * @return
     */
    public TradeResult createTradeOrder(TradePrecreate tradePrecreate) throws Exception;

    /**
     * 当面付2.0预下单，生成二维码
     *
     * @param tradePrecreate
     * @param notifyResultUrl
     * @return
     * @throws Exception
     */
    public TradeResult createTradeOrder(TradePrecreate tradePrecreate, String notifyResultUrl) throws Exception;

    /**
     * 查询预支付订单
     * @param tradeQuery
     * @return
     */
    public TradeQueryResult queryPreTradeOrder(TradeQuery tradeQuery) throws Exception;


    /**
     * 取消预支付订单
     *
     * @param tradeQuery
     * @return
     * @throws Exception
     */
    public TradeResult canalTradeOrder(TradeQuery tradeQuery) throws Exception;


    /**
     * 申请退款接口
     *
     * @param refund
     * @return
     * @throws Exception
     */
    public TradeResult refundTradeOrder(TradeRefund refund) throws Exception;

    /**
     * 查询申请退款接口
     *
     * @param refund
     * @return
     * @throws Exception
     */
    public RefundResult queryRefundTradeOrder(TradeRefund refund) throws Exception;

    /**
     * 异步通知进行通知
     *
     * @param request
     * @return
     */
    public Map<String, String> notifyResult(HttpServletRequest request, String AliPayPublicKey);


}
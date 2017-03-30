package com.wish.pay.web.service;

import com.wish.pay.web.dao.entity.TradeOrder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/1 16:03
 */
public interface ITradeOrderService {

    /**
     * 保存交易记录
     * @param tradeOrder
     * @return
     */
    boolean save(TradeOrder tradeOrder);

    public TradeOrder getTradeOrderByOrderSerial(String orderSerial);

    boolean update(TradeOrder tradeOrder);
}

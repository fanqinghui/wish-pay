package com.wish.pay.web.dao.mapper;

import com.wish.pay.web.dao.MybatisBaseDao;
import com.wish.pay.web.dao.entity.TradeOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeOrderMapper extends MybatisBaseDao<Long, TradeOrder> {

    /**
     *  根据商品订单号查询交易记录
     * @param orderSerial
     * @return
     */
    TradeOrder getTradeOrderByOrderSerial(String orderSerial);
}
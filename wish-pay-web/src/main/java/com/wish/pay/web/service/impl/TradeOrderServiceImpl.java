package com.wish.pay.web.service.impl;

import com.wish.pay.common.utils.validator.ValidationResult;
import com.wish.pay.common.utils.validator.ValidationUtils;
import com.wish.pay.web.dao.entity.TradeOrder;
import com.wish.pay.web.dao.mapper.TradeOrderMapper;
import com.wish.pay.web.service.ITradeOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 交易账单service
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/1 16:03
 */
@Service
public class TradeOrderServiceImpl implements ITradeOrderService {
    Logger logger = LoggerFactory.getLogger(TradeOrderServiceImpl.class);

    @Autowired
    private TradeOrderMapper tradeOrderMapper;


    @Override
    public boolean save(TradeOrder tradeOrder) {
        try {
            ValidationResult result = ValidationUtils.validateEntity(tradeOrder);
            if (tradeOrder != null &&
                    result.isHasErrors()) {
                logger.error("[]TradeOrderServiceImpl save validate error", result.getErrorMsg());
                return false;
            }
            tradeOrderMapper.insert(tradeOrder);
        } catch (Exception e) {
            logger.error("[TradeOrderServiceImpl.save] error：", e);
            return false;
        }
        return true;
    }

    @Override
    public TradeOrder getTradeOrderByOrderSerial(String orderSerial) {
        return tradeOrderMapper.getTradeOrderByOrderSerial(orderSerial);
    }

    @Override
    public boolean update(TradeOrder tradeOrder) {
        int count = tradeOrderMapper.updateByPrimaryKeySelective(tradeOrder);
        if (count > 0)
            return true;
        return false;
    }
}

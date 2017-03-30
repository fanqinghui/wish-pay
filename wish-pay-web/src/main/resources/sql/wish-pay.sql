CREATE TABLE `trade_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_name` varchar(50) NOT NULL COMMENT '购买的商品名称',
  `order_serial` varchar(64) NOT NULL COMMENT '业务订单流水号',
  `trade_no` varchar(64) DEFAULT NULL COMMENT '支付机构的交易凭证号',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '订单状态 0 创建 未支付 1 支付成功 2 支付失败',
  `pay_way` tinyint(4) NOT NULL COMMENT '支付途径 0支付宝 1微信',
  `amount` decimal(10,2) NOT NULL COMMENT '支付金额(单位元)',
  `msg` varchar(2000) DEFAULT NULL COMMENT '支付信息返回信息',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_order_number` (`order_serial`),
  UNIQUE KEY `unique_trade_no` (`trade_no`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='支付订单表'
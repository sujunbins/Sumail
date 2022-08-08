package com.junbin.mail.order.dao;

import com.junbin.mail.order.entity.OrderOperateHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单操作历史记录
 * 
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 17:53:12
 */
@Mapper
public interface OrderOperateHistoryDao extends BaseMapper<OrderOperateHistoryEntity> {
	
}

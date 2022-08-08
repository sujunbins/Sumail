package com.junbin.mail.order.dao;

import com.junbin.mail.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 17:53:12
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}

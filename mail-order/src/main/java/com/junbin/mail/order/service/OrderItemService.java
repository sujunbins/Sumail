package com.junbin.mail.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.junbin.common.utils.PageUtils;
import com.junbin.mail.order.entity.OrderItemEntity;

import java.util.Map;

/**
 * 订单项信息
 *
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 17:53:12
 */
public interface OrderItemService extends IService<OrderItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


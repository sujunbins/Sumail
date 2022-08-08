package com.junbin.mail.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.junbin.common.utils.PageUtils;
import com.junbin.mail.coupon.entity.SeckillSkuRelationEntity;

import java.util.Map;

/**
 * 秒杀活动商品关联
 *
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 17:31:01
 */
public interface SeckillSkuRelationService extends IService<SeckillSkuRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


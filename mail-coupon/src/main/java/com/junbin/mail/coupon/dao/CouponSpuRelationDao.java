package com.junbin.mail.coupon.dao;

import com.junbin.mail.coupon.entity.CouponSpuRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券与产品关联
 * 
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 17:31:00
 */
@Mapper
public interface CouponSpuRelationDao extends BaseMapper<CouponSpuRelationEntity> {
	
}

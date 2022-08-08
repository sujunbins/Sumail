package com.junbin.mail.coupon.dao;

import com.junbin.mail.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 17:31:01
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}

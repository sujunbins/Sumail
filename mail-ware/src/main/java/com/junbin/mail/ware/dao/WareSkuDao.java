package com.junbin.mail.ware.dao;

import com.junbin.mail.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 17:57:37
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}

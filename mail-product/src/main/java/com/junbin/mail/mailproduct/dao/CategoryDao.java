package com.junbin.mail.mailproduct.dao;

import com.junbin.mail.mailproduct.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 14:54:07
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}

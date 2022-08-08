package com.junbin.mail.mailproduct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.junbin.common.utils.PageUtils;
import com.junbin.mail.mailproduct.entity.CategoryBrandRelationEntity;

import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 14:54:07
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


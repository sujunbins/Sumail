package com.junbin.mail.mailproduct.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junbin.common.utils.PageUtils;
import com.junbin.mail.mailproduct.DTO.AttrResVO;
import com.junbin.mail.mailproduct.entity.AttrEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 14:54:07
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);



    void saveAttr(AttrEntity attr);

    void removeAttr(List<Long> asList);

    AttrResVO getAttrinfo(Long attrId);

    void updateAttr(AttrResVO attr);

    PageUtils queryPage(Map<String, Object> params, Long attrId, Integer i);

    PageUtils queryPage(IPage<AttrEntity> page, QueryWrapper<AttrEntity> attrEntityQueryWrapper);

    List<Long> selectSearchAttrs(List<Long> attrIds);
}


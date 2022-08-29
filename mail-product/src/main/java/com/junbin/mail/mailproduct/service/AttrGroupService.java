package com.junbin.mail.mailproduct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.junbin.common.utils.PageUtils;
import com.junbin.mail.mailproduct.DTO.AttrGroupRelationVo;
import com.junbin.mail.mailproduct.DTO.AttrGroupWithattr;
import com.junbin.mail.mailproduct.entity.AttrEntity;
import com.junbin.mail.mailproduct.entity.AttrGroupEntity;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 14:54:07
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Long catelogId);

    List<AttrEntity> getRalationAttr(Long attrgroupId);

    void deleteRelation(AttrGroupRelationVo[] attrGroupRelationVo);

    PageUtils getRalationnoAttr(Map<String, Object> params, Long attrgroupId);

    List<AttrGroupWithattr> getAttrGroupWithattr(Long catelogId);
}


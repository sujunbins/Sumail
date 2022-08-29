package com.junbin.mail.mailproduct.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.junbin.mail.mailproduct.DTO.AttrGroupRelationVo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junbin.common.utils.PageUtils;
import com.junbin.common.utils.Query;

import com.junbin.mail.mailproduct.dao.AttrAttrgroupRelationDao;
import com.junbin.mail.mailproduct.entity.AttrAttrgroupRelationEntity;
import com.junbin.mail.mailproduct.service.AttrAttrgroupRelationService;


@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements AttrAttrgroupRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrAttrgroupRelationEntity> page = this.page(
                new Query<AttrAttrgroupRelationEntity>().getPage(params),
                new QueryWrapper<AttrAttrgroupRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveBatch(AttrGroupRelationVo[] attrGroupRelationVo) {
        List<AttrAttrgroupRelationEntity> entitys = Arrays.stream(attrGroupRelationVo).map(item -> {
            AttrAttrgroupRelationEntity entity = JSONObject.parseObject(JSONObject.toJSONBytes(item), AttrAttrgroupRelationEntity.class);
            return entity;
        }).collect(Collectors.toList());
        this.saveBatch(entitys);
    }

}
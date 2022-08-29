package com.junbin.mail.mailproduct.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.junbin.mail.mailproduct.DTO.AttrGroupRelationVo;
import com.junbin.mail.mailproduct.DTO.AttrGroupWithattr;
import com.junbin.mail.mailproduct.dao.AttrAttrgroupRelationDao;
import com.junbin.mail.mailproduct.dao.AttrDao;
import com.junbin.mail.mailproduct.entity.AttrAttrgroupRelationEntity;
import com.junbin.mail.mailproduct.entity.AttrEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.junbin.mail.mailproduct.dao.AttrGroupDao;
import com.junbin.mail.mailproduct.entity.AttrGroupEntity;
import com.junbin.mail.mailproduct.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    AttrAttrgroupRelationDao relationDao;
    @Autowired
    AttrDao attrDao;

    @Autowired
    AttrServiceImpl attrService;



    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {

        String key = (String) params.get("key");
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>();
        if(!StringUtils.isEmpty(key))
        {
            wrapper.and((obj)->{
                obj.eq("attr_group_id", key).or().like("attr_group_name", key);
            });
        }
        if(catelogId==0)
        {
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params),
                    wrapper
            );
            return new PageUtils(page);
        }else {
            wrapper.eq("catelog_id", catelogId);
            IPage<AttrGroupEntity> page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    wrapper
            );
            return new PageUtils(page);


        }

    }
    /*
    * 根据分组ID找到所有的基本属性
    * */
    @Override
    public List<AttrEntity> getRalationAttr(Long attrgroupId) {


        List<AttrAttrgroupRelationEntity> attr_group_id = relationDao.selectList(
                new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId));
        List<Long> list = attr_group_id.stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());
        if(list.size()==0||list == null)
        {
            return null;
        }
        List<AttrEntity> entities = attrDao.selectBatchIds(list);
        return entities;
    }

    @Override
    public void deleteRelation(AttrGroupRelationVo[] attrGroupRelationVo) {

        List<AttrAttrgroupRelationEntity> collect = Arrays.stream(attrGroupRelationVo).map((item) -> {
            AttrAttrgroupRelationEntity entity = new AttrAttrgroupRelationEntity();
            entity = JSONObject.parseObject(JSONObject.toJSONBytes(item), AttrAttrgroupRelationEntity.class);
            return entity;
        }).collect(Collectors.toList());

        relationDao.deleterelation(collect);

    }
    /*
    * 获取当前分组没有关联的所有属性
    *
    *
    * */
    @Override
    public PageUtils getRalationnoAttr(Map<String, Object> params, Long attrgroupId) {


        AttrGroupEntity entity = this.baseMapper.selectById(attrgroupId);
        Long catelogId = entity.getCatelogId();
        //分组id下的属性
        List<AttrGroupEntity> attr_group_id = this.baseMapper.selectList(new QueryWrapper<AttrGroupEntity>()
                .eq("catelog_id", catelogId));
        List<Object> attrgroup = attr_group_id.stream().map((item) -> {
            return item.getAttrGroupId();
        }).collect(Collectors.toList());


        List<AttrAttrgroupRelationEntity> attr_id = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>()
                .in("attr_group_id", attrgroup));
        if(attr_id.size()==0||attr_id==null)
        {
            return new PageUtils(attr_id,0,0,1);
        }
        List<Object> collect = attr_id.stream().map((item) -> {
            return item.getAttrId();
        }).collect(Collectors.toList());

        QueryWrapper<AttrEntity> attrEntityQueryWrapper = new QueryWrapper<AttrEntity>()
                .eq("catelog_id", catelogId).eq("attr_type", 1).notIn("attr_id", collect);
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key))
        {
            attrEntityQueryWrapper.and((obj) -> {
                obj.eq("attr_id", key).or().like("attr_name", key);
            });


        }
        PageUtils pageUtils = attrService.queryPage(new Query<AttrEntity>().getPage(params), attrEntityQueryWrapper);



        //
        return pageUtils;
    }

    /*
    *
    * 根据分类id查询分组属性
    * */
    @Override
    public List<AttrGroupWithattr> getAttrGroupWithattr(Long catelogId) {
        List<AttrGroupEntity> attrGroupEntities = this.list(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));
        //查询所有属性
        List<AttrGroupWithattr> collect = attrGroupEntities.stream().map(item -> {

            AttrGroupWithattr attrGroupWithattr = JSON.parseObject(JSONObject.toJSONBytes(item), AttrGroupWithattr.class);

            List<AttrEntity> ralationAttr = this.getRalationAttr(attrGroupWithattr.getAttrGroupId());
            attrGroupWithattr.setAttrs(ralationAttr);
            return attrGroupWithattr;
        }).collect(Collectors.toList());


        return collect;
    }

}
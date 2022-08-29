package com.junbin.mail.mailproduct.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.junbin.mail.mailproduct.DTO.AttrResVO;
import com.junbin.mail.mailproduct.dao.AttrAttrgroupRelationDao;
import com.junbin.mail.mailproduct.dao.AttrGroupDao;
import com.junbin.mail.mailproduct.dao.CategoryDao;
import com.junbin.mail.mailproduct.entity.AttrAttrgroupRelationEntity;
import com.junbin.mail.mailproduct.entity.AttrGroupEntity;
import com.junbin.mail.mailproduct.entity.CategoryEntity;
import com.junbin.mail.mailproduct.service.CategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junbin.common.utils.PageUtils;
import com.junbin.common.utils.Query;

import com.junbin.mail.mailproduct.dao.AttrDao;
import com.junbin.mail.mailproduct.entity.AttrEntity;
import com.junbin.mail.mailproduct.service.AttrService;
import org.springframework.transaction.annotation.Transactional;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {


    @Autowired
    AttrGroupDao attrGroupDao;

    @Autowired
    AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    CategoryDao categoryDao;


    @Autowired
    CategoryService service;




    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId,Integer type) {



        String key = (String) params.get("key");
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>().eq("attr_type", type);


        if(catelogId != 0)
        {
            wrapper.eq("catelog_id", catelogId);
        }
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                wrapper
        );

        PageUtils pageUtils = new PageUtils(page);
        List<AttrResVO> attrs = pageUtils.getList().stream().map((attr) -> {
            AttrResVO attrResVO = new AttrResVO();
            attrResVO = JSONObject.parseObject(JSONObject.toJSONBytes(attr), AttrResVO.class);
            if(type != 0)
            {
                AttrAttrgroupRelationEntity att = attrAttrgroupRelationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrResVO.getAttrId()));
                if (att != null) {
                    attrResVO.setGroupName(attrGroupDao.selectById(att.getAttrGroupId()).getAttrGroupName());
                }

            }
            CategoryEntity categoryEntity = categoryDao.selectById(attrResVO.getCatelogId());
            if (categoryEntity != null) {
                attrResVO.setCatelogName(categoryEntity.getName());
            }



            return attrResVO;
        }).collect(Collectors.toList());
        pageUtils.setList(attrs);
        return pageUtils;

    }

    @Override
    public PageUtils queryPage(IPage<AttrEntity> page, QueryWrapper<AttrEntity> attrEntityQueryWrapper) {
        this.page(page, attrEntityQueryWrapper);

        return new PageUtils(page);
    }

    @Override
    public List<Long> selectSearchAttrs(List<Long> attrIds) {
        List<Long> searchAttrIds = this.baseMapper.selectSearchAttrIds(attrIds);
        return searchAttrIds;
    }

    @Override
    public void saveAttr(AttrEntity attr) {
        this.save(attr);
        if(attr.getAttrGroupId()==null)
        {
            return;
        }
        if(attr.getAttrType() == 1) {
            AttrAttrgroupRelationEntity entity = new AttrAttrgroupRelationEntity();
            entity.setAttrGroupId(attr.getAttrGroupId());
            entity.setAttrId(attr.getAttrId());


            attrAttrgroupRelationDao.insert(entity);
        }
    }

    @Override
    public void removeAttr(List<Long> asList) {
        this.baseMapper.deleteBatchIds(asList);

    }

    @Override
    public AttrResVO getAttrinfo(Long attrId) {


        AttrResVO attrResVO = JSONObject.parseObject(JSONObject.toJSONBytes(this.getById(attrId)), AttrResVO.class);
        AttrAttrgroupRelationEntity att = attrAttrgroupRelationDao.selectOne(
                new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrResVO.getAttrId()));

        if(attrResVO.getAttrType() == 1) {
            if (att != null) {

                attrResVO.setAttrGroupId(att.getAttrGroupId());
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(att.getAttrGroupId());
                if (attrGroupEntity != null) {
                    attrResVO.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }
        }
        //設置分類信息
        Long[] catelogPaths = service.findCatelogPath(attrResVO.getCatelogId());


        attrResVO.setCatelogPath(catelogPaths);

        CategoryEntity categoryEntity = categoryDao.selectById(attrResVO.getCatelogId());
        if (categoryEntity != null) {
            attrResVO.setCatelogName(categoryEntity.getName());
        }


        return attrResVO;
    }

    @Override
    @Transactional
    public void updateAttr(AttrResVO attr) {

        AttrEntity att = JSONObject.parseObject(JSONObject.toJSONBytes(attr), AttrEntity.class);
        this.updateById(att);
        if(att.getAttrType() == 1 ) {
            if(attr.getAttrGroupId()==null)
            {
                return;
            }
            //修改分組關聯關係
            AttrAttrgroupRelationEntity entity = new AttrAttrgroupRelationEntity();
            entity.setAttrId(attr.getAttrId());
            entity.setAttrGroupId(attr.getAttrGroupId());


            Integer count = attrAttrgroupRelationDao.selectCount(
                    new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId()));

            if (count > 0 ) {
                attrAttrgroupRelationDao.update(entity,
                        new UpdateWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId()));
            } else {
                attrAttrgroupRelationDao.insert(entity);
            }

        }





    }



}
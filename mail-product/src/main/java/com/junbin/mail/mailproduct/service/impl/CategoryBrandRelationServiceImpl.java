package com.junbin.mail.mailproduct.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.junbin.mail.mailproduct.dao.BrandDao;
import com.junbin.mail.mailproduct.dao.CategoryDao;
import com.junbin.mail.mailproduct.entity.BrandEntity;
import com.junbin.mail.mailproduct.entity.CategoryEntity;
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

import com.junbin.mail.mailproduct.dao.CategoryBrandRelationDao;
import com.junbin.mail.mailproduct.entity.CategoryBrandRelationEntity;
import com.junbin.mail.mailproduct.service.CategoryBrandRelationService;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    BrandDao brandDao;

    @Autowired
    CategoryDao categoryDao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();
        QueryWrapper<CategoryBrandRelationEntity> QueryRelation =
                new QueryWrapper<CategoryBrandRelationEntity>().eq("brand_id", brandId)
                        .and(i-> i.eq("catelog_id", catelogId));
        ;
        if(this.getOne(QueryRelation)!=null) {
            return;
        }
        //查询
        BrandEntity brandEntity = brandDao.selectById(brandId);
        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);
        categoryBrandRelation.setBrandName(brandEntity.getName());
        categoryBrandRelation.setCatelogName(categoryEntity.getName());
        this.save(categoryBrandRelation);
    }

    @Override
    public void updateBrand(Long brandId, String name) {
        CategoryBrandRelationEntity entity = new CategoryBrandRelationEntity();
        entity.setBrandName(name);
        entity.setBrandId(brandId);
        this.update(entity,
                new UpdateWrapper<CategoryBrandRelationEntity>().eq("brand_id", brandId));
    }

    @Override
    public void updateCategory(Long catId, String name) {
        CategoryBrandRelationEntity entity = new CategoryBrandRelationEntity();
        entity.setCatelogName(name);
        entity.setCatelogId(catId);
        this.update(entity,
                new UpdateWrapper<CategoryBrandRelationEntity>().eq("catelog_id", catId));

    }

    @Override
    public List<BrandEntity> getbrandlist(Long catId) {
        QueryWrapper<CategoryBrandRelationEntity> query = new QueryWrapper<CategoryBrandRelationEntity>()
                .eq("catelog_id", catId);
        List<CategoryBrandRelationEntity> categoryBrandRelationEntities = this.baseMapper.selectList(query);
        List<BrandEntity> collect = categoryBrandRelationEntities.stream().map(item -> {
            BrandEntity brandEntity = brandDao.selectById(item.getBrandId());
            return brandEntity;
        }).collect(Collectors.toList());
        return collect;
    }

}
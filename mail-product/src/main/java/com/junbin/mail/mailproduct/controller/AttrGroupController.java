package com.junbin.mail.mailproduct.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.junbin.mail.mailproduct.DTO.AttrGroupRelationVo;
import com.junbin.mail.mailproduct.DTO.AttrGroupWithattr;
import com.junbin.mail.mailproduct.DTO.AttrResVO;
import com.junbin.mail.mailproduct.entity.AttrEntity;
import com.junbin.mail.mailproduct.service.AttrAttrgroupRelationService;
import com.junbin.mail.mailproduct.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.junbin.mail.mailproduct.entity.AttrGroupEntity;
import com.junbin.mail.mailproduct.service.AttrGroupService;
import com.junbin.common.utils.PageUtils;
import com.junbin.common.utils.R;



/**
 * 属性分组
 *
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 14:54:07
 */
@RestController
@RequestMapping("mailproduct/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;



    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;



    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("mailproduct:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrGroupService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    //@RequiresPermissions("mailproduct:attrgroup:list")
    public R listbyId(@RequestParam Map<String, Object> params,
                      @PathVariable("catelogId") Long catelogId){
//        PageUtils page = attrGroupService.queryPage(params);
        PageUtils page = attrGroupService.queryPage(params,catelogId);

        return R.ok().put("page", page);
    }

    @GetMapping("/{catelogId}/withattr")
    public R getAttrGroupWithattr(@PathVariable("catelogId")Long catelogId)
    {
        //1.查出當前分類下的所有屬性分组

        //2.查出每个分组的所有属性
        List<AttrGroupWithattr> attrs =  attrGroupService.getAttrGroupWithattr(catelogId);

        return R.ok().put("data", attrs);
    }


    /**
     * 列表
     */
    @RequestMapping("/{attrgroupId}/attr/relation")
    //@RequiresPermissions("mailproduct:attrgroup:list")
    public R attrRelation(
                      @PathVariable("attrgroupId") Long attrgroupId){
//        PageUtils page = attrGroupService.queryPage(params);
        List<AttrEntity> entities = attrGroupService.getRalationAttr(attrgroupId);

        return R.ok().put("data", entities);
    }

    /**
     * 列表
     */
    @GetMapping("/{attrgroupId}/noattr/relation")
    //@RequiresPermissions("mailproduct:attrgroup:list")
    public R attrnoRelation(
            @PathVariable("attrgroupId") Long attrgroupId,
            @RequestParam Map<String,Object> params){
//        PageUtils page = attrGroupService.queryPage(params);
        PageUtils page = attrGroupService.getRalationnoAttr(params,attrgroupId);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    //@RequiresPermissions("mailproduct:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        attrGroup.setCatelogPath(
                categoryService.findCatelogPath(attrGroup.getCatelogId()));


        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("mailproduct:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 保存
     */
    @PostMapping("/attr/relation")
    //@RequiresPermissions("mailproduct:attrgroup:save")
    public R save(@RequestBody AttrGroupRelationVo[] attrGroupRelationVo){
        attrAttrgroupRelationService.saveBatch(attrGroupRelationVo);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("mailproduct:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("mailproduct:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/attr/relation/delete")
    //@RequiresPermissions("mailproduct:attrgroup:delete")
    public R delete(@RequestBody AttrGroupRelationVo[] attrGroupRelationVo){

        attrGroupService.deleteRelation(attrGroupRelationVo);


        return R.ok();
    }






}

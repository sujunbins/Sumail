package com.junbin.mail.mailproduct.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.junbin.mail.mailproduct.DTO.AttrResVO;
import com.junbin.mail.mailproduct.entity.ProductAttrValueEntity;
import com.junbin.mail.mailproduct.service.ProductAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.junbin.mail.mailproduct.entity.AttrEntity;
import com.junbin.mail.mailproduct.service.AttrService;
import com.junbin.common.utils.PageUtils;
import com.junbin.common.utils.R;



/**
 * 商品属性
 *
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 14:54:07
 */
@RestController
@RequestMapping("mailproduct/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("mailproduct:attr:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);

    }

    /**
     *  获取spu规格
     */
    @GetMapping("/base/listforspu/{spuId}")
    public R baseAttrlistforspu(@PathVariable("spuId") Long spuId){

        List<ProductAttrValueEntity> entities = productAttrValueService.baseAttrListforspu(spuId);

        return R.ok().put("data",entities);
    }

    /**
     * 列表
     */
    @RequestMapping("/sale/list/{attrId}")
    //@RequiresPermissions("mailproduct:attrgroup:list")
    public R listbyId(@RequestParam Map<String, Object> params,
                      @PathVariable("attrId") Long attrId){
//        PageUtils page = attrGroupService.queryPage(params);
        PageUtils page = attrService.queryPage(params,attrId,0);

        return R.ok().put("page", page);
    }


    /**
     * 列表
     */
    @RequestMapping("/base/list/{attrId}")
    //@RequiresPermissions("mailproduct:attrgroup:list")
    public R listbyIdType(@RequestParam Map<String, Object> params,
                      @PathVariable("attrId") Long attrId){
//        PageUtils page = attrGroupService.queryPage(params);
        PageUtils page = attrService.queryPage(params,attrId,1);

        return R.ok().put("page", page);
    }



    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    //@RequiresPermissions("mailproduct:attr:info")
    public R info(@PathVariable("attrId") Long attrId){
        AttrResVO attr = attrService.getAttrinfo(attrId);

        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("mailproduct:attr:save")
    public R save(@RequestBody AttrEntity attr){
		attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("mailproduct:attr:update")
    public R update(@RequestBody AttrResVO attr){
		attrService.updateAttr(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("mailproduct:attr:delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeAttr(Arrays.asList(attrIds));


        return R.ok();
    }

    //product/attr/update/{spuId}
    @PostMapping("/update/{spuId}")
    //@RequiresPermissions("product:attr:update")
    public R updateSpuAttr(@PathVariable("spuId") Long spuId,
                           @RequestBody List<ProductAttrValueEntity> entities){

        productAttrValueService.updateSpuAttr(spuId,entities);

        return R.ok();
    }

}

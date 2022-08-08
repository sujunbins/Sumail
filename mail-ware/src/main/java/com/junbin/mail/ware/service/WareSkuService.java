package com.junbin.mail.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.junbin.common.utils.PageUtils;
import com.junbin.mail.ware.entity.WareSkuEntity;

import java.util.Map;

/**
 * 商品库存
 *
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 17:57:37
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


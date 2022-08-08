package com.junbin.mail.mailproduct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.junbin.common.utils.PageUtils;
import com.junbin.mail.mailproduct.entity.SpuImagesEntity;

import java.util.Map;

/**
 * spu图片
 *
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 14:54:07
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


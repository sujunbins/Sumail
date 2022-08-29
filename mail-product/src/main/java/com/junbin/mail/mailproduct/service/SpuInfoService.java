package com.junbin.mail.mailproduct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.junbin.common.utils.PageUtils;
import com.junbin.mail.mailproduct.DTO.SpuSaveVo;
import com.junbin.mail.mailproduct.entity.SpuInfoEntity;

import java.util.Map;

/**
 * spu信息
 *
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 14:54:07
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void savesupInfo(SpuSaveVo vo);

    void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity);

    PageUtils queryPageByCondtion(Map<String, Object> params);

    // @Transactional(rollbackFor = Exception.class)
    void up(Long spuId);

    SpuInfoEntity getSpuInfoBySkuId(Long skuId);
}


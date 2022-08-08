package com.junbin.mail.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.junbin.common.utils.PageUtils;
import com.junbin.mail.ware.entity.WareInfoEntity;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 17:57:38
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


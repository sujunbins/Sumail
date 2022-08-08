package com.junbin.mail.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.junbin.common.utils.PageUtils;
import com.junbin.mail.ware.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 17:57:38
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


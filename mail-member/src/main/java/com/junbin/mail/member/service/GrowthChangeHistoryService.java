package com.junbin.mail.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.junbin.common.utils.PageUtils;
import com.junbin.mail.member.entity.GrowthChangeHistoryEntity;

import java.util.Map;

/**
 * 成长值变化历史记录
 *
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 17:45:32
 */
public interface GrowthChangeHistoryService extends IService<GrowthChangeHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


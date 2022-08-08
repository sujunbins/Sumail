package com.junbin.mail.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.junbin.common.utils.PageUtils;
import com.junbin.mail.member.entity.MemberLevelEntity;

import java.util.Map;

/**
 * 会员等级
 *
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 17:45:32
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


package com.junbin.mail.member.dao;

import com.junbin.mail.member.entity.MemberLoginLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员登录记录
 * 
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 17:45:31
 */
@Mapper
public interface MemberLoginLogDao extends BaseMapper<MemberLoginLogEntity> {
	
}

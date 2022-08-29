package com.junbin.mail.mailproduct.fallback;

import com.junbin.common.exception.BizCodeEnum;
import com.junbin.common.utils.R;
import com.junbin.mail.mailproduct.feign.SeckillFeignService;
import org.springframework.stereotype.Component;

/**
 * @ Author     ：苏俊滨
 * @ Date       ：Created in 7:20 2022/8/23
 * @ Description：${description}
 **/
@Component
public class SeckillFeignServiceFallBack implements SeckillFeignService {
    @Override
    public R getSkuSeckilInfo(Long skuId) {
        return R.error(BizCodeEnum.TO_MANY_REQUEST.getCode(),BizCodeEnum.TO_MANY_REQUEST.getMessage());
    }
}
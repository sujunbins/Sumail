package com.junbin.mail.member.OpenFeign;


import com.junbin.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ Author     ：苏俊滨
 * @ Date       ：Created in 0:17 2022/8/9
 * @ Description：远程调用接口
 **/
@FeignClient("mail-coupon")
public interface CouponFeignServer {

    @RequestMapping("coupon/coupon/member/coupon")
    public R getCoupon();
}

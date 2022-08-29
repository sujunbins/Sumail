package com.junbin.common.validator.ValidatorValue;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;

/**
 * @ Author     ：苏俊滨
 * @ Date       ：Created in 17:26 2022/8/15
 * @ Description：自定义校验注解
 **/
@Documented
@Constraint(
        validatedBy = {StatusConstraintValidator.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface StatusValue {
    String message() default "参数范围不对";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int[] vals();
}

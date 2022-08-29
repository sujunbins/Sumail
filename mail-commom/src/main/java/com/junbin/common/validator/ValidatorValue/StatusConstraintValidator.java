package com.junbin.common.validator.ValidatorValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @ Author     ：苏俊滨
 * @ Date       ：Created in 17:33 2022/8/15
 * @ Description：${description}
 **/
public class StatusConstraintValidator implements ConstraintValidator<StatusValue, Integer> {

    private Set<Integer> set = new HashSet<>();


    @Override
    public void initialize(StatusValue constraintAnnotation) {
        Arrays.stream(constraintAnnotation.vals()).forEach(t->{
            set.add(t);
        });

    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(integer);
    }
}

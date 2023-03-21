package com.example.getmesocialservice.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LowercaseOnlyValidator implements ConstraintValidator<LowercaseOnly, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.matches("[a-z1-9@.]+")){
            return true;
        }
        return false;
    }
}

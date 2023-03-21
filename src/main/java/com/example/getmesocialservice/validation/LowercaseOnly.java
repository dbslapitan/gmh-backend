package com.example.getmesocialservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LowercaseOnlyValidator.class)
@Documented
public @interface LowercaseOnly {

    String message() default "Lowercase letters only.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

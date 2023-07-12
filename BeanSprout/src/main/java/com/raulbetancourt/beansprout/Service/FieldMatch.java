package com.raulbetancourt.beansprout.Service;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

//Field matching parameters to be used by FieldMatch validator.
@Constraint(validatedBy = FieldMatchValidator.class)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldMatch {

    String message() default "";

    /*
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
     */

    String first();

    String second();

    @Target( { ElementType.TYPE, ElementType.ANNOTATION_TYPE} )
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List
    {
        FieldMatch[] value();
    }

}

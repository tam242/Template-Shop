package hu.elte.templateshop.webdomains.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FilePathValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FilePathValid {
    String message() default "Template doesn't exist or invalid path";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
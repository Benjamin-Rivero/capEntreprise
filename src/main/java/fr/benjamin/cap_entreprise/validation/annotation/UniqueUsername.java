package fr.benjamin.cap_entreprise.validation.annotation;

import fr.benjamin.cap_entreprise.validation.UniqueUsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueUsernameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UniqueUsername {

    String message() default "This username already exists !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

package com.hilquiascamelo.facialrecognitionsystem.domain.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Verificação de e-mail
 *
 * <p>
 * Tipos suportados:
 * <ul>
 *     <li>{@code String}</li>
 * </ul>
 *
 * @autor GuoGuang
 * @criado em 2021-09-02
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CheckEmail.ValidatorValue.class)
public @interface CheckEmail {

    int value() default 0;

    String message() default "E-mail inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Define várias anotações {@link CheckEmail} no mesmo elemento
     */
    @Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        CheckEmail[] value();
    }

    class ValidatorValue implements ConstraintValidator<CheckEmail, String> {

        private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        @Override
        public void initialize(CheckEmail constraintAnnotation) {
        }

        @Override
        public boolean isValid(String content, ConstraintValidatorContext context) {
            if (content == null) {
                return false;
            }
            return content.matches(EMAIL_REGEX);
        }
    }
}

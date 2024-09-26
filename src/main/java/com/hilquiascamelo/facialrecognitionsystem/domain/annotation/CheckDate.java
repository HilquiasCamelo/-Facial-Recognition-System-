package com.hilquiascamelo.facialrecognitionsystem.domain.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Verificação de data personalizada
 *
 * <p>
 * Tipos suportados:
 * <ul>
 *     <li>{@code Date}</li>
 *     <li>{@code LocalDate}</li>
 * </ul>
 *
 * @autor GuoGuang
 * @criado em 2021-09-02
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {CheckDate.CheckDateValidatorForDate.class, CheckDate.CheckDateValidatorForLocalDate.class})
public @interface CheckDate {

    int value() default 0;

    String message() default "Data inválida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Define várias anotações {@link CheckDate} no mesmo elemento
     */
    @Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        CheckDate[] value();
    }

    class CheckDateValidatorForDate implements ConstraintValidator<CheckDate, java.util.Date> {

        @Override
        public void initialize(CheckDate constraintAnnotation) {
        }

        @Override
        public boolean isValid(java.util.Date content, ConstraintValidatorContext context) {
            if (content == null || content.toString().equals("1970-01-01")) {
                return false;
            }
            LocalDate localDate = new java.sql.Date(content.getTime()).toLocalDate();
            return isValidDate(localDate);
        }

        private boolean isValidDate(LocalDate date) {
            return date.isAfter(LocalDate.of(1970, 1, 1)); // Exemplo de validação
        }
    }

    class CheckDateValidatorForLocalDate implements ConstraintValidator<CheckDate, LocalDate> {

        @Override
        public void initialize(CheckDate constraintAnnotation) {
        }

        @Override
        public boolean isValid(LocalDate content, ConstraintValidatorContext context) {
            if (content == null || content.toString().equals("1970-01-01")) {
                return false;
            }
            return isValidDate(content);
        }

        private boolean isValidDate(LocalDate date) {
            return date.isAfter(LocalDate.of(1970, 1, 1)); // Exemplo de validação


        }
    }
}

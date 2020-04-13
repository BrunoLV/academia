package br.com.valhala.academia.alunos.aplicacao.validacao.customizadas;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidaCPF.class)
public @interface ValorCPF {

    String message() default "CPF invalido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

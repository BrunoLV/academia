package br.com.valhala.academia.alunos.aplicacao.validacao.customizadas;

import br.com.caelum.stella.validation.CPFValidator;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidaCPF implements ConstraintValidator<ValorCPF, Long> {

    @Override
    public boolean isValid(Long cpf, ConstraintValidatorContext context) {
        if (cpf == null) {
            return false;
        }
        final String cpfComoTexto = StringUtils.leftPad(String.valueOf(cpf), 11, "0");
        return new CPFValidator().isEligible(cpfComoTexto);
    }

}

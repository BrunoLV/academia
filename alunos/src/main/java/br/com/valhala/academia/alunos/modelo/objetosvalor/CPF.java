package br.com.valhala.academia.alunos.modelo.objetosvalor;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.valhala.academia.alunos.aplicacao.validacao.customizadas.ValorCPF;
import br.com.valhala.academia.alunos.aplicacao.validacao.grupos.Edicao;
import br.com.valhala.academia.alunos.aplicacao.validacao.grupos.Novo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CPF {

    @Getter
    @ValorCPF(groups = {Novo.class, Edicao.class})
    @Column(name = "cpf")
    private Long cpf;

    public String formatado() {
        final String texto = StringUtils.leftPad(String.valueOf(cpf), 11, "0");
        return new CPFFormatter().format(texto);
    }

}

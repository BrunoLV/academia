package br.com.valhala.academia.alunos.modelo.objetosvalor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CEP {

    @Getter
    @Column(name = "cep")
    @NotNull(message = "O cep é uma informação obrigatória")
    private Integer cep;

    public String formatado() {
        final String cepNaoFormatado = StringUtils.leftPad(String.valueOf(cep), 8, "0");
        return String.join("-",
                cepNaoFormatado.substring(0, 5),
                cepNaoFormatado.substring(5));
    }

}

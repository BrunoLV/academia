package br.com.valhala.academia.alunos.modelo.objetosvalor;

import br.com.valhala.academia.alunos.aplicacao.validacao.grupos.Edicao;
import br.com.valhala.academia.alunos.aplicacao.validacao.grupos.Novo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Nome {

    @Getter
    @Column(name = "primeiro_nome")
    @NotNull(message = "O primeiro nome é uma informação obrigatória")
    private String primeiroNome;

    @Getter
    @Column(name = "nome_do_meio")
    private String nomeDoMeio;

    @Getter
    @Column(name = "sobrenome")
    @NotNull(message = "O sobrenome é uma informação obrigatória")
    private String sobrenome;

    public String completo() {
        StringBuilder builder = new StringBuilder();
        builder.append(primeiroNome).append(" ");
        if (nomeDoMeio != null && !nomeDoMeio.isEmpty()) {
            builder.append(nomeDoMeio).append(" ");
        }
        builder.append(sobrenome);
        return builder.toString();
    }

}

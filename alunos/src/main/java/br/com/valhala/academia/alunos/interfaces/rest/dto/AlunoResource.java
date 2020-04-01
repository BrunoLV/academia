package br.com.valhala.academia.alunos.interfaces.rest.dto;

import br.com.valhala.academia.alunos.modelo.agregados.Aluno;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlunoResource implements Serializable {

    private String guid;
    private String nome;
    private String nomeDoMeio;
    private String sobrenome;
    private LocalDate dataNascimento;
    private EnderecoResource endereco;

    public static AlunoResource aPartirDe(final Aluno aluno) {

        return AlunoResource.
                builder().
                guid(aluno.getAlunoId().getGuid()).
                nome(aluno.getNome().getPrimeiroNome()).
                nomeDoMeio(aluno.getNome().getNomeDoMeio()).
                sobrenome(aluno.getNome().getSobrenome()).
                dataNascimento(aluno.getDataNascimento()).
                endereco(aluno.getEndereco() != null ? EnderecoResource.aPartirDe(aluno.getEndereco()) : null).
                build();

    }

}

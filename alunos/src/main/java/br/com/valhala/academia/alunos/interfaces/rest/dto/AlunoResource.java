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

    @ApiModelProperty(required = false, dataType = "uuid")
    private String guid;
    @ApiModelProperty(required = true, example = "Geraldo", dataType = "string")
    private String nome;
    @ApiModelProperty(required = true, example = "Iago", dataType = "string")
    private String nomeDoMeio;
    @ApiModelProperty(required = true, example = "Moreira", dataType = "string")
    private String sobrenome;
    @ApiModelProperty(required = true, example = "1996-02-10", dataType = "date")
    private LocalDate dataNascimento;
    @ApiModelProperty(required = true, example = "86135635034", dataType = "number")
    private Long cpf;

    @ApiModelProperty(required = true, dataType = "EnderecoResource")
    private EnderecoResource endereco;

    public static AlunoResource aPartirDe(final Aluno aluno) {

        return AlunoResource.
                builder().
                guid(aluno.getAlunoId().getGuid()).
                nome(aluno.getNome().getPrimeiroNome()).
                nomeDoMeio(aluno.getNome().getNomeDoMeio()).
                sobrenome(aluno.getNome().getSobrenome()).
                dataNascimento(aluno.getDataNascimento()).
                cpf(aluno.getCpf().getCpf()).
                endereco(aluno.getEndereco() != null ? EnderecoResource.aPartirDe(aluno.getEndereco()) : null).
                build();

    }

}

package br.com.valhala.academia.alunos.modelo.comandos;

import br.com.valhala.academia.alunos.modelo.entidades.Endereco;
import br.com.valhala.academia.alunos.modelo.objetosvalor.CPF;
import br.com.valhala.academia.alunos.modelo.objetosvalor.Nome;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NovoAlunoCommand {

    private Nome nome;
    private LocalDate dataNascimento;
    private Endereco endereco;
    private CPF cpf;

}

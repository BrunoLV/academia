package br.com.valhala.academia.alunos.modelo.comandos;

import br.com.valhala.academia.alunos.modelo.agregados.AlunoID;
import br.com.valhala.academia.alunos.modelo.entidades.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtualizaEnderecoCommand {

    private AlunoID alunoID;
    private Endereco endereco;

}

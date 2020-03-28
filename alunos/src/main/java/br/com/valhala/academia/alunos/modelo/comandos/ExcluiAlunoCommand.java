package br.com.valhala.academia.alunos.modelo.comandos;

import br.com.valhala.academia.alunos.modelo.agregados.AlunoID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExcluiAlunoCommand {

    private AlunoID alunoID;

}

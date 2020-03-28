package br.com.valhala.academia.alunos.interfaces.rest.assembler;

import br.com.valhala.academia.alunos.modelo.agregados.AlunoID;
import br.com.valhala.academia.alunos.modelo.comandos.ExcluiAlunoCommand;

public class ExcluiAlunoCommandAssembler {

    public static ExcluiAlunoCommand toExcluiAlunoCommand(final String guid) {

        return new ExcluiAlunoCommand(new AlunoID(guid));

    }

}

package br.com.valhala.academia.alunos.interfaces.rest.assembler;

import br.com.valhala.academia.alunos.interfaces.rest.dto.AlunoResource;
import br.com.valhala.academia.alunos.interfaces.rest.dto.EnderecoResource;
import br.com.valhala.academia.alunos.modelo.agregados.AlunoID;
import br.com.valhala.academia.alunos.modelo.comandos.AtualizaAlunoCommand;
import br.com.valhala.academia.alunos.modelo.entidades.Endereco;
import br.com.valhala.academia.alunos.modelo.objetosvalor.CEP;
import br.com.valhala.academia.alunos.modelo.objetosvalor.Logradouro;
import br.com.valhala.academia.alunos.modelo.objetosvalor.Nome;

public class AtualizaAlunoCommandAssembler {

    public static AtualizaAlunoCommand toAtualizaAlunoCommand(final String guid, final AlunoResource alunoResource) {

        EnderecoResource enderecoResource = alunoResource.getEndereco();

        Logradouro logradouro = Logradouro.
                builder().
                tipo(enderecoResource.getTipo()).
                logradouro(enderecoResource.getLogradouro()).
                complemento(enderecoResource.getComplemento()).
                numero(enderecoResource.getNumero()).
                build();

        CEP cep = new CEP(enderecoResource.getCep());

        Endereco endereco = new Endereco(logradouro,
                cep,
                enderecoResource.getBairro(),
                enderecoResource.getMunicipio(),
                enderecoResource.getUf());

        Nome nome = Nome.
                builder().
                primeiroNome(alunoResource.getNome()).
                nomeDoMeio(alunoResource.getNomeDoMeio()).
                sobrenome(alunoResource.getSobrenome()).
                build();

        AtualizaAlunoCommand command = AtualizaAlunoCommand.
                builder().
                alunoID(new AlunoID(guid)).
                nome(nome).
                dataNascimento(alunoResource.getDataNascimento()).
                endereco(endereco).
                build();

        return command;
    }

}

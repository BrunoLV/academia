package br.com.valhala.academia.alunos.interfaces.rest.assembler;

import br.com.valhala.academia.alunos.interfaces.rest.dto.EnderecoResource;
import br.com.valhala.academia.alunos.modelo.agregados.AlunoID;
import br.com.valhala.academia.alunos.modelo.comandos.AtualizaEnderecoCommand;
import br.com.valhala.academia.alunos.modelo.entidades.Endereco;
import br.com.valhala.academia.alunos.modelo.objetosvalor.CEP;
import br.com.valhala.academia.alunos.modelo.objetosvalor.Logradouro;

public class AtualizaEnderecoCommandAssembler {

    public static AtualizaEnderecoCommand toAtualizaEnderecoCommand(String guid, EnderecoResource enderecoResource) {

        Endereco endereco = null;

        if (enderecoResource != null) {

            Logradouro logradouro = Logradouro.
                    builder().
                    tipo(enderecoResource.getTipo()).
                    logradouro(enderecoResource.getLogradouro()).
                    complemento(enderecoResource.getComplemento()).
                    numero(enderecoResource.getNumero()).
                    build();

            CEP cep = new CEP(enderecoResource.getCep());


            endereco = new Endereco(logradouro,
                    cep,
                    enderecoResource.getBairro(),
                    enderecoResource.getMunicipio(),
                    enderecoResource.getUf());

        }

        return new AtualizaEnderecoCommand(new AlunoID(guid), endereco);

    }

}

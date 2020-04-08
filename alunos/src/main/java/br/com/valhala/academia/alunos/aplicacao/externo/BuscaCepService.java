package br.com.valhala.academia.alunos.aplicacao.externo;

import br.com.valhala.academia.alunos.infraestrutura.service.buscacep.BuscaCepClient;
import br.com.valhala.academia.alunos.infraestrutura.service.buscacep.dtos.EnderecoDto;
import br.com.valhala.academia.alunos.modelo.entidades.Endereco;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class BuscaCepService {

    private BuscaCepClient buscaCepCliente;

    public BuscaCepService(BuscaCepClient buscaCepClient) {
        this.buscaCepCliente = buscaCepClient;
    }

    public Endereco completaEndereco(Endereco endereco) {

        try {
            EnderecoDto dto = buscaCepCliente.buscaCep(StringUtils.leftPad(String.valueOf(endereco.getCep().getCep()), 8, "0"));
            if (dto != null) {
                return new Endereco(endereco.getLogradouro(), endereco.getCep(), dto.getBairro(), dto.getCidade(), dto.getUf());
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            return endereco;
        }


    }


}

package br.com.valhala.academia.alunos.aplicacao.externo;

import br.com.valhala.academia.alunos.infraestrutura.service.buscacep.BuscaCepClient;
import br.com.valhala.academia.alunos.infraestrutura.service.buscacep.dtos.EnderecoDto;
import br.com.valhala.academia.alunos.modelo.entidades.Endereco;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BuscaCepService {

    private BuscaCepClient buscaCepCliente;

    public BuscaCepService(BuscaCepClient buscaCepClient) {
        this.buscaCepCliente = buscaCepClient;
    }

    /**
     * Esse método tem por objetivo chamar o serviço de correios pesquisar o endereço utilizando o CEP e completar
     * o mesmo com as informações desenvolvidas pelo serviço.
     * Caso ocorra erro ele não interrompe a execução da aplicação, apenas retorno o endereço original passado para o
     * método e loga o erro do serviço para rastreio da falha.
     * @param endereco
     * @return
     */
    public Endereco completaEndereco(final Endereco endereco) {

        try {
            log.info("Executando serviço dos correios.");
            EnderecoDto dto = buscaCepCliente.buscaCep(StringUtils.leftPad(String.valueOf(endereco.getCep().getCep()), 8, "0"));
            if (dto != null) {
                return new Endereco(endereco.getLogradouro(), endereco.getCep(), dto.getBairro(), dto.getCidade(), dto.getUf());
            } else {
                return endereco;
            }
        } catch (Exception e) {
            log.error("Ocorreu um erro na execução do serviço dos correios. Erro: ", e.getMessage(), e);
            return endereco;
        }


    }


}

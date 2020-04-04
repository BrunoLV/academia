package br.com.valhala.academia.buscacep.interfaces.rest.dto;

import br.com.valhala.academia.buscacep.modelo.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoResource implements Serializable {

    private String bairro;
    private String cep;
    private String cidade;
    private String endereco;
    private String uf;

    public static EnderecoResource aPartirDe(final Endereco endereco) {
        return new EnderecoResource(endereco.getBairro(),
                                    endereco.getCep(),
                                    endereco.getCidade(),
                                    endereco.getEndereco(),
                                    endereco.getUf());
    }

}

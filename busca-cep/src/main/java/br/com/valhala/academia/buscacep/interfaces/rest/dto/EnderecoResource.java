package br.com.valhala.academia.buscacep.interfaces.rest.dto;

import br.com.valhala.academia.buscacep.modelo.Endereco;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel(value = "EnderecoResource", description = "Endereço resultante da operação de busca")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoResource implements Serializable {

    @ApiModelProperty(value = "Bairro do endereço")
    private String bairro;
    @ApiModelProperty(value = "Cep do Endereço")
    private String cep;
    @ApiModelProperty(value = "Cidade do Endereço")
    private String cidade;
    @ApiModelProperty(value = "Logradouro completo")
    private String endereco;
    @ApiModelProperty(value = "UF do Endereço")
    private String uf;

    public static EnderecoResource aPartirDe(final Endereco endereco) {
        return new EnderecoResource(endereco.getBairro(),
                                    endereco.getCep(),
                                    endereco.getCidade(),
                                    endereco.getEndereco(),
                                    endereco.getUf());
    }

}

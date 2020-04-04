package br.com.valhala.academia.alunos.interfaces.rest.dto;

import br.com.valhala.academia.alunos.modelo.entidades.Endereco;
import br.com.valhala.academia.alunos.modelo.objetosvalor.TipoLogradouro;
import br.com.valhala.academia.alunos.modelo.objetosvalor.UF;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(required = true, example = "RUA", dataType = "string")
    private TipoLogradouro tipo;
    @ApiModelProperty(required = true, example = "Rua Leocádio Pereira", dataType = "string")
    private String logradouro;
    @ApiModelProperty(required = true, example = "1A", dataType = "string")
    private String numero;
    @ApiModelProperty(required = false, example = "BLOCO A", dataType = "string")
    private String complemento;
    @ApiModelProperty(required = true, example = "09350420", dataType = "integer")
    private Integer cep;
    @ApiModelProperty(required = true, example = "Vila Flórida", dataType = "string")
    private String bairro;
    @ApiModelProperty(required = true, example = "Mauá", dataType = "string")
    private String municipio;
    @ApiModelProperty(required = true, example = "SP", dataType = "string")
    private UF uf;

    public static EnderecoResource aPartirDe(final Endereco endereco) {

        return EnderecoResource.
                builder().
                bairro(endereco.getBairro()).
                municipio(endereco.getMunicipio()).
                cep(endereco.getCep().getCep()).
                tipo(endereco.getLogradouro().getTipo()).
                logradouro(endereco.getLogradouro().getLogradouro()).
                numero(endereco.getLogradouro().getNumero()).
                complemento(endereco.getLogradouro().getComplemento()).
                uf(endereco.getUf()).
                build();

    }
}

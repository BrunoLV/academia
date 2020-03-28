package br.com.valhala.academia.alunos.interfaces.rest.dto;

import br.com.valhala.academia.alunos.modelo.entidades.Endereco;
import br.com.valhala.academia.alunos.modelo.objetosvalor.TipoLogradouro;
import br.com.valhala.academia.alunos.modelo.objetosvalor.UF;
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

    private TipoLogradouro tipo;
    private String logradouro;
    private String numero;
    private String complemento;
    private Integer cep;
    private String bairro;
    private String municipio;
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

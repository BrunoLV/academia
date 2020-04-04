package br.com.valhala.academia.buscacep.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {

    private String bairro;
    private String cep;
    private String cidade;
    private String endereco;
    private String uf;

}

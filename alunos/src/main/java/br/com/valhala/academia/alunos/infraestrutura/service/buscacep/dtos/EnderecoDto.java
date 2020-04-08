package br.com.valhala.academia.alunos.infraestrutura.service.buscacep.dtos;

import br.com.valhala.academia.alunos.modelo.objetosvalor.UF;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDto implements Serializable {

    private String bairro;
    private String cep;
    private String cidade;
    private String endereco;
    private UF uf;

}

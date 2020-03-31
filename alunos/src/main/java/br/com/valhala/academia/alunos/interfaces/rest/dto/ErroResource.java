package br.com.valhala.academia.alunos.interfaces.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErroResource {

    private Integer codigo;

    private String mensagem;

    private List<MensagemValidacaoResource> mensagensValidacao;

    public void adicionaMensagemValidacao(final String mensagem) {
        if (mensagensValidacao == null) {
            mensagensValidacao = new ArrayList<>();
        }
        mensagensValidacao.add(new MensagemValidacaoResource(mensagem));
    }

}

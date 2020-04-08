package br.com.valhala.academia.alunos.infraestrutura.service.buscacep;

import br.com.valhala.academia.alunos.infraestrutura.service.buscacep.dtos.EnderecoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "busca-cep")
public interface BuscaCepClient {

    @GetMapping("/api/buscaCep/{cep}")
    EnderecoDto buscaCep(@PathVariable("cep") String cep);

}

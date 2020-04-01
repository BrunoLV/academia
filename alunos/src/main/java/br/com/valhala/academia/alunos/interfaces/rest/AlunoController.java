package br.com.valhala.academia.alunos.interfaces.rest;

import br.com.valhala.academia.alunos.aplicacao.exceptions.AlunoNaoEncontradoException;
import br.com.valhala.academia.alunos.aplicacao.interno.comandos.AlunoCommandService;
import br.com.valhala.academia.alunos.aplicacao.interno.consultas.AlunoQueryService;
import br.com.valhala.academia.alunos.interfaces.rest.assembler.AtualizaAlunoCommandAssembler;
import br.com.valhala.academia.alunos.interfaces.rest.assembler.AtualizaEnderecoCommandAssembler;
import br.com.valhala.academia.alunos.interfaces.rest.assembler.ExcluiAlunoCommandAssembler;
import br.com.valhala.academia.alunos.interfaces.rest.assembler.NovoAlunoCommandAssembler;
import br.com.valhala.academia.alunos.interfaces.rest.dto.AlunoResource;
import br.com.valhala.academia.alunos.interfaces.rest.dto.EnderecoResource;
import br.com.valhala.academia.alunos.modelo.agregados.Aluno;
import br.com.valhala.academia.alunos.modelo.agregados.AlunoID;
import br.com.valhala.academia.alunos.modelo.comandos.AtualizaAlunoCommand;
import br.com.valhala.academia.alunos.modelo.comandos.AtualizaEnderecoCommand;
import br.com.valhala.academia.alunos.modelo.comandos.ExcluiAlunoCommand;
import br.com.valhala.academia.alunos.modelo.comandos.NovoAlunoCommand;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ResponseHeader;
import lombok.extern.java.Log;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.swagger.readers.operation.ResponseHeaders;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Log
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private AlunoQueryService alunoQueryService;
    private AlunoCommandService alunoCommandService;

    public AlunoController(AlunoCommandService alunoCommandService, AlunoQueryService alunoQueryService) {
        this.alunoCommandService = alunoCommandService;
        this.alunoQueryService = alunoQueryService;
    }

    @ApiOperation(value = "${AlunoController.obtemPorGuid.value}",
            notes = "${AlunoController.obtemPorGuid.notes}",
            response = AlunoResource.class)
    @GetMapping(value = "/{guid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity obtemPorGuid(@ApiParam(value = "${AlunoController.obtemPorGuid.guid}", required = true) @PathVariable("guid") String guid) {
        AlunoID alunoID = new AlunoID(guid);
        Aluno aluno = alunoQueryService.obtemPorAlunoID(alunoID);
        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(AlunoResource.aPartirDe(aluno));
    }

    @ApiOperation(value = "${AlunoController.obtemTodos.value}",
            notes = "${AlunoController.obtemTodos.notes}",
            responseContainer = "List",
            response = AlunoResource.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity obtemTodos() {
        List<Aluno> alunos = alunoQueryService.obtemTodos();
        if (CollectionUtils.isEmpty(alunos)) {
            return ResponseEntity.notFound().build();
        }
        List<AlunoResource> recursos = alunos.
                stream().map(a -> AlunoResource.aPartirDe(a)).
                collect(Collectors.toList());
        return ResponseEntity.ok(recursos);
    }

    @ApiOperation(value = "${AlunoController.cadastraAluno.value}",
            notes = "${AlunoController.cadastraAluno.notes}",
            response = AlunoResource.class)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity cadastraAluno(@Valid @RequestBody AlunoResource alunoResource, UriComponentsBuilder uriComponentsBuilder) {
        NovoAlunoCommand command = NovoAlunoCommandAssembler.toNovoAlunoCommand(alunoResource);
        Aluno aluno = alunoCommandService.cadastraAluno(command);
        UriComponents uriComponents = uriComponentsBuilder.path("/alunos/{guid}").buildAndExpand(aluno.getAlunoId().getGuid());
        return ResponseEntity.created(uriComponents.toUri()).body(AlunoResource.aPartirDe(aluno));
    }

    @ApiOperation(value = "${AlunoController.atualizaAluno.value}",
            notes = "${AlunoController.atualizaAluno.notes}",
            response = Void.class)
    @PutMapping(value = "/{guid}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity atualizaAluno(@PathVariable("guid") String guid, @RequestBody AlunoResource alunoResource) {
        AtualizaAlunoCommand command = AtualizaAlunoCommandAssembler.toAtualizaAlunoCommand(guid, alunoResource);
        try {
            alunoCommandService.atualizaAluno(command);
        } catch (AlunoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "${AlunoController.excluiAluno.value}",
            notes = "${AlunoController.excluiAluno.notes}",
            response = Void.class)
    @DeleteMapping(value = "/{guid}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity excluiAluno(@PathVariable("guid") String guid) {
        ExcluiAlunoCommand command = ExcluiAlunoCommandAssembler.toExcluiAlunoCommand(guid);
        try {
            alunoCommandService.excluiAluno(command);
        } catch (AlunoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "${AlunoController.atualizaEndereco.value}",
            notes = "${AlunoController.atualizaEndereco.notes}",
            response = Void.class)
    @PatchMapping(value = "/{guid}/endereco", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> atualizaEndereco(@PathVariable("guid") String guid, @RequestBody EnderecoResource enderecoResource) {
        AtualizaEnderecoCommand command = AtualizaEnderecoCommandAssembler.toAtualizaEnderecoCommand(guid, enderecoResource);
        try {
            alunoCommandService.atualizaEndereco(command);
        } catch (AlunoNaoEncontradoException e) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

}

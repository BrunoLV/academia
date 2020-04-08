package br.com.valhala.academia.alunos.aplicacao.interno.comandos;

import br.com.valhala.academia.alunos.aplicacao.exceptions.AlunoNaoEncontradoException;
import br.com.valhala.academia.alunos.aplicacao.exceptions.DadosInvalidosException;
import br.com.valhala.academia.alunos.aplicacao.externo.BuscaCepService;
import br.com.valhala.academia.alunos.aplicacao.validacao.grupos.Edicao;
import br.com.valhala.academia.alunos.aplicacao.validacao.grupos.Novo;
import br.com.valhala.academia.alunos.infraestrutura.repositorios.jpa.AlunoRepository;
import br.com.valhala.academia.alunos.modelo.agregados.Aluno;
import br.com.valhala.academia.alunos.modelo.comandos.AtualizaAlunoCommand;
import br.com.valhala.academia.alunos.modelo.comandos.AtualizaEnderecoCommand;
import br.com.valhala.academia.alunos.modelo.comandos.ExcluiAlunoCommand;
import br.com.valhala.academia.alunos.modelo.comandos.NovoAlunoCommand;
import br.com.valhala.academia.alunos.modelo.entidades.Endereco;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlunoCommandService {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private AlunoRepository alunoRepository;

    private BuscaCepService buscaCepService;

    public AlunoCommandService(AlunoRepository alunoRepository, BuscaCepService buscaCepService) {
        this.alunoRepository = alunoRepository;
        this.buscaCepService = buscaCepService;
    }

    @Transactional
    public Aluno cadastraAluno(final NovoAlunoCommand command) {

        Endereco endereco = buscaCepService.completaEndereco(command.getEndereco());

        Aluno aluno = new Aluno(command.getNome(), command.getDataNascimento(), endereco);

        valida(aluno, Default.class, Novo.class);
        alunoRepository.save(aluno);
        return aluno;
    }

    @Transactional
    public void atualizaAluno(final AtualizaAlunoCommand command) {
        Aluno aluno = alunoRepository.findByAlunoId(command.getAlunoID());
        if (aluno == null) {
            throw new AlunoNaoEncontradoException();
        }
        aluno.atualizaNome(command.getNome());
        aluno.atualizaDataNascimento(command.getDataNascimento());
        aluno.atualizaEndereco(command.getEndereco());
        valida(aluno, Edicao.class);
        alunoRepository.save(aluno);
    }

    @Transactional
    public void excluiAluno(ExcluiAlunoCommand command) {
        Aluno aluno = alunoRepository.findByAlunoId(command.getAlunoID());
        if (aluno == null) {
            throw new AlunoNaoEncontradoException();
        }
        alunoRepository.delete(aluno);
    }

    @Transactional
    public void atualizaEndereco(AtualizaEnderecoCommand command) {
        Aluno aluno = alunoRepository.findByAlunoId(command.getAlunoID());
        if (aluno == null) {
            throw new AlunoNaoEncontradoException();
        }
        aluno.atualizaEndereco(command.getEndereco());
        valida(aluno, Edicao.class);
        alunoRepository.save(aluno);
    }

    private void valida(final Aluno aluno, Class... grupos) {
        Set<ConstraintViolation<Aluno>> constraintViolations = validator.validate(aluno, grupos);
        if (!constraintViolations.isEmpty()) {
            throw new DadosInvalidosException("Dados inválidos", constraintViolations.
                    stream().
                    map(c -> c.getMessage()).
                    collect(Collectors.toSet()));
        }
    }

}

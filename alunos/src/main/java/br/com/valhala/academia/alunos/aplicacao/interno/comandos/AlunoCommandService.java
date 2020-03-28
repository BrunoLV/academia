package br.com.valhala.academia.alunos.aplicacao.interno.comandos;

import br.com.valhala.academia.alunos.aplicacao.exceptions.AlunoNaoEncontradoException;
import br.com.valhala.academia.alunos.infraestrutura.repositorios.jpa.AlunoRepository;
import br.com.valhala.academia.alunos.modelo.agregados.Aluno;
import br.com.valhala.academia.alunos.modelo.comandos.AtualizaAlunoCommand;
import br.com.valhala.academia.alunos.modelo.comandos.AtualizaEnderecoCommand;
import br.com.valhala.academia.alunos.modelo.comandos.ExcluiAlunoCommand;
import br.com.valhala.academia.alunos.modelo.comandos.NovoAlunoCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlunoCommandService {

    private AlunoRepository alunoRepository;

    public AlunoCommandService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public Aluno cadastraAluno(final NovoAlunoCommand command) {
        Aluno aluno = new Aluno(command.getNome(), command.getDataNascimento(), command.getEndereco());
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
        alunoRepository.save(aluno);
    }

}

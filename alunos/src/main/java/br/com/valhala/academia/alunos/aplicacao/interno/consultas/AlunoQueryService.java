package br.com.valhala.academia.alunos.aplicacao.interno.consultas;

import br.com.valhala.academia.alunos.infraestrutura.repositorios.jpa.AlunoRepository;
import br.com.valhala.academia.alunos.modelo.agregados.Aluno;
import br.com.valhala.academia.alunos.modelo.agregados.AlunoID;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoQueryService {

    private AlunoRepository repository;

    public AlunoQueryService(AlunoRepository repository) {
        this.repository = repository;
    }

    public Aluno obtemPorAlunoID(final AlunoID alunoID) {
        return repository.findByAlunoId(alunoID);
    }

    public List<Aluno> obtemTodos() {
        return repository.findAll();
    }
}

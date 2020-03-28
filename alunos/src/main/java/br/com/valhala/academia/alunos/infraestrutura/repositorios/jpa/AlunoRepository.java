package br.com.valhala.academia.alunos.infraestrutura.repositorios.jpa;

import br.com.valhala.academia.alunos.modelo.agregados.Aluno;
import br.com.valhala.academia.alunos.modelo.agregados.AlunoID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Aluno findByAlunoId(AlunoID alunoID);

}

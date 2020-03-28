package br.com.valhala.academia.alunos.modelo.agregados;

import br.com.valhala.academia.alunos.modelo.entidades.Endereco;
import br.com.valhala.academia.alunos.modelo.objetosvalor.Nome;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Table(name = "alunos")
public class Aluno {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @Embedded
    private AlunoID alunoId;

    @Getter
    @NotNull
    @Embedded
    private Nome nome;

    @Getter
    @NotNull
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Getter
    @NotNull
    @OneToOne(mappedBy = "aluno", cascade = CascadeType.ALL)
    private Endereco endereco;

    public Aluno(Nome nome, LocalDate dataNascimento, Endereco endereco) {
        this.alunoId = new AlunoID();
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.endereco.atrelaAluno(this);
    }

    public void atualizaEndereco(final Endereco endereco) {
        if (this.endereco == null) {
            this.endereco = endereco;
            this.endereco.atrelaAluno(this);
        } else {
            this.endereco.atualizaDados(endereco);
        }
    }

    public void atualizaNome(final Nome nome) {
        this.nome = nome;
    }

    public void atualizaDataNascimento(final LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}

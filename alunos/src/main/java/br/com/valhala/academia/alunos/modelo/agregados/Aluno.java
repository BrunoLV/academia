package br.com.valhala.academia.alunos.modelo.agregados;

import br.com.valhala.academia.alunos.aplicacao.validacao.grupos.Edicao;
import br.com.valhala.academia.alunos.aplicacao.validacao.grupos.Novo;
import br.com.valhala.academia.alunos.modelo.entidades.Endereco;
import br.com.valhala.academia.alunos.modelo.objetosvalor.CPF;
import br.com.valhala.academia.alunos.modelo.objetosvalor.Nome;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
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
    @Embedded
    @NotNull(message = "O nome é uma informação obrigatória", groups = {Novo.class, Edicao.class})
    @Valid
    private Nome nome;

    @Getter
    @Embedded
    @NotNull(message = "O cpf é um informação obrigatória", groups = {Novo.class, Edicao.class})
    @Valid
    private CPF cpf;

    @Getter
    @Column(name = "data_nascimento")
    @NotNull(message = "A data de nascimento é uma informação obrigatória", groups = {Novo.class, Edicao.class})
    private LocalDate dataNascimento;

    @Getter
    @OneToOne(mappedBy = "aluno", cascade = CascadeType.ALL)
    @NotNull(message = "Endereço é uma informação obrigatória", groups = {Novo.class, Edicao.class})
    @Valid
    private Endereco endereco;

    public Aluno(Nome nome, LocalDate dataNascimento, Endereco endereco, CPF cpf) {
        this.alunoId = new AlunoID();
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        if (endereco != null) {
            this.endereco = endereco;
            this.endereco.atrelaAluno(this);
        }
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

    public void atualizaCPF(CPF cpf) {
        this.cpf = cpf;
    }
}

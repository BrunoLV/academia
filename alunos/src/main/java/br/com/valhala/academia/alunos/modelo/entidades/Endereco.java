package br.com.valhala.academia.alunos.modelo.entidades;

import br.com.valhala.academia.alunos.modelo.agregados.Aluno;
import br.com.valhala.academia.alunos.modelo.objetosvalor.CEP;
import br.com.valhala.academia.alunos.modelo.objetosvalor.Logradouro;
import br.com.valhala.academia.alunos.modelo.objetosvalor.UF;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enderecos")
public class Endereco {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter
    @NotNull
    @Embedded
    private Logradouro logradouro;

    @Getter
    @NotNull
    @Embedded
    private CEP cep;

    @Getter
    @NotNull
    @Column(name = "bairro")
    private String bairro;

    @Getter
    @NotNull
    @Column(name = "municipio")
    private String municipio;

    @Getter
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "uf")
    private UF uf;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    public Endereco(Logradouro logradouro, CEP cep, String bairro, String municipio, UF uf) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.bairro = bairro;
        this.municipio = municipio;
        this.uf = uf;
    }

    public void atrelaAluno(final Aluno aluno) {
        this.aluno = aluno;
    }

    public void atualizaDados(final Endereco endereco) {
        this.logradouro = endereco.logradouro;
        this.cep = endereco.cep;
        this.bairro = endereco.bairro;
        this.municipio = endereco.municipio;
        this.uf = endereco.uf;
    }

}

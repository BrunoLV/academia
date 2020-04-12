package br.com.valhala.academia.alunos.modelo.entidades;

import br.com.valhala.academia.alunos.aplicacao.validacao.grupos.Edicao;
import br.com.valhala.academia.alunos.aplicacao.validacao.grupos.Novo;
import br.com.valhala.academia.alunos.modelo.agregados.Aluno;
import br.com.valhala.academia.alunos.modelo.objetosvalor.CEP;
import br.com.valhala.academia.alunos.modelo.objetosvalor.Logradouro;
import br.com.valhala.academia.alunos.modelo.objetosvalor.UF;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
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
    @Embedded
    @NotNull(message = "O logradouro é uma informação obrigatória", groups = {Novo.class, Edicao.class})
    @Valid
    private Logradouro logradouro;

    @Getter
    @Embedded
    @NotNull(message = "O CEP é uma informação obrigatória", groups = {Novo.class, Edicao.class})
    @Valid
    private CEP cep;

    @Getter
    @Column(name = "bairro")
    @NotNull(message = "O bairro é uma informação obrigatória", groups = {Novo.class, Edicao.class})
    private String bairro;

    @Getter
    @Column(name = "municipio")
    @NotNull(message = "O município é uma informação obrigatória", groups = {Novo.class, Edicao.class})
    private String municipio;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "uf")
    @NotNull(message = "A UF é uma informação obrigatória", groups = {Novo.class, Edicao.class})
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

    public boolean enderecoCompleto() {
        return this.cep != null && this.logradouro != null && this.bairro != null && this.municipio != null && this.uf != null;
    }

}

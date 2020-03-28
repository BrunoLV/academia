package br.com.valhala.academia.alunos.modelo.agregados;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

@AllArgsConstructor
@Embeddable
public class AlunoID {

    @Getter
    @Column(name = "guid", updatable = false)
    private String guid;

    public AlunoID() {
        this.guid = UUID.randomUUID().toString();
    }

}

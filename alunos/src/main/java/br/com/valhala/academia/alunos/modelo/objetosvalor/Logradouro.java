package br.com.valhala.academia.alunos.modelo.objetosvalor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Logradouro {

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    @NotNull(message = "Tipo de Logradouro é uma informação obrigatória")
    private TipoLogradouro tipo;

    @Getter
    @Column(name = "logradouro")
    @NotNull(message = "Logradouro é uma informação obrigatória")
    private String logradouro;

    @Getter
    @Column(name = "numero")
    private String numero;

    @Getter
    @Column(name = "complemento")
    private String complemento;

    public String completoFormatado() {
        StringBuilder builder = new StringBuilder();
        builder.append(tipo.getDescricao()).append(" ").append(logradouro);
        if (numero != null && !numero.isEmpty()) {
            builder.append(", ").append(numero);
        } else {
            builder.append(", S/N");
        }
        if (complemento != null) {
            builder.append(" - ").append(complemento);
        }
        return builder.toString();
    }

}

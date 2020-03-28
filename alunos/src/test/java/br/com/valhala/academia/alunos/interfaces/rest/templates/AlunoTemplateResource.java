package br.com.valhala.academia.alunos.interfaces.rest.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.valhala.academia.alunos.interfaces.rest.dto.AlunoResource;
import br.com.valhala.academia.alunos.interfaces.rest.dto.EnderecoResource;

import java.time.LocalDate;
import java.time.Month;

public class AlunoTemplateResource implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(AlunoResource.class).addTemplate("novo", new Rule() {
            {
                add("nome", "Douglas");
                add("nomeDoMeio", "Luiz");
                add("sobrenome", "Viana");
                add("dataNascimento", LocalDate.of(1991, Month.APRIL, 19));
                add("endereco", one(EnderecoResource.class, "novo"));
            }
        }).addTemplate("alunoAlterado", new Rule() {
            {
                add("nome", "Bruno");
                add("nomeDoMeio", "L.");
                add("sobrenome", "V.");
                add("dataNascimento", LocalDate.of(1990, Month.JANUARY, 02));
                add("endereco", one(EnderecoResource.class, "enderecoAlterado"));
            }
        } );
    }

}

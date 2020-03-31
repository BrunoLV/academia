package br.com.valhala.academia.alunos.interfaces.rest.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.valhala.academia.alunos.interfaces.rest.dto.EnderecoResource;
import br.com.valhala.academia.alunos.modelo.objetosvalor.TipoLogradouro;
import br.com.valhala.academia.alunos.modelo.objetosvalor.UF;

public class EnderecoTemplateResource implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(EnderecoResource.class).addTemplate("novo", new Rule() {
            {
                add("tipo", TipoLogradouro.RUA);
                add("logradouro", "Pirassununga");
                add("numero", "1A");
                add("cep", 7859220);
                add("bairro", "Vila Carmela de Túlio");
                add("municipio", "Franco da Rocha");
                add("uf", UF.SP);
            }
        }).addTemplate("enderecoAlterado", new Rule() {
            {
                add("tipo", TipoLogradouro.RUA);
                add("logradouro", "Hercília Dártora Szcentes");
                add("numero", "1A");
                add("cep", 7743345);
                add("bairro", "Laranjeiras");
                add("municipio", "Caieiras");
                add("uf", UF.SP);
            }
        }).addTemplate("enderecoInvalido", new Rule() {
            {
                add("tipo", null);
                add("logradouro", null);
                add("numero", null);
                add("cep", null);
                add("bairro", null);
                add("municipio", null);
                add("uf", null);
            }
        });

    }

}

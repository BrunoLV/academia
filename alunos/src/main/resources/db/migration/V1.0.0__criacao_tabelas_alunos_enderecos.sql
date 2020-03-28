create table alunos (
	id bigint auto_increment not null,
	guid varchar(100) not null,
	primeiro_nome varchar(100) not null,
	nome_do_meio varchar(100),
	sobrenome varchar(100) not null,
	data_nascimento date not null,
	primary key(id),
	unique key(guid)
);

create table enderecos (
	id bigint auto_increment not null,
	tipo varchar(50) not null,
	logradouro varchar(200) not null,
	numero varchar(5),
	complemento varchar(200),
	cep int(8) not null,
	bairro varchar(100) not null,
	municipio varchar(100) not null,
	uf varchar(2) not null,
	id_aluno bigint not null,
	primary key(id),
	constraint fk_aluno_endereco foreign key (id_aluno) references alunos(id)
);
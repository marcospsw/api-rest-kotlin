create table curso
(
    id        bigint      not null auto_increment,
    nome      varchar(50) not null,
    categoria varchar(50) not null,
    primary key (id)
);

create table usuario
(
    id    bigint      not null auto_increment,
    nome  varchar(50) not null,
    email varchar(50) not null,
    primary key (id)
);

create table topico
(
    id           bigint      not null auto_increment,
    titulo       varchar(50) not null,
    mensagem     varchar(50) not null,
    data_criacao datetime    not null,
    status       varchar(20) not null,
    curso_id     bigint      not null,
    autor_id     bigint      not null,
    primary key (id),
    foreign key (curso_id) references curso (id),
    foreign key (autor_id) references usuario (id)
);

create table resposta
(
    id           bigint       not null auto_increment,
    mensagem     varchar(300) not null,
    data_criacao datetime     not null,
    topico_id    bigint       not null,
    autor_id     bigint       not null,
    solucao      int(1) not null,
    primary key (id),
    foreign key (topico_id) references topico (id),
    foreign key (autor_id) references usuario (id)
);
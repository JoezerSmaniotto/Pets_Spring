create table user (
                      id bigint not null auto_increment,
                      email varchar(255),
                      login varchar(255),
                      nome varchar(255),
                      senha varchar(255),
                      primary key (id)
);

create table role (
                      id bigint not null auto_increment,
                      nome varchar(255),
                      primary key (id)
);

create table user_roles (
                            user_id bigint not null,
                            role_id bigint not null
);


create table pet
(id bigint not null auto_increment,
 nome varchar(255),
 descricao varchar(255),
 raca varchar(255),
 url_foto varchar(255),
 url_video varchar(255),
 latitude varchar(255),
 longitude varchar(255),
 tipo varchar(255), primary key (id) );
# exercicio_banco_java

create database exercicio_java;
use exercicio_java;

create table usuario (
id int unsigned not null auto_increment,
nome varchar(100),
email varchar(100),
senha varchar(20),
primary key(id)
);

delete from usuario;
select \* from usuario;

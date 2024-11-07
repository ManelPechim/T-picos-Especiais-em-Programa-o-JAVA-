create table alunos (
    id int not null auto_increment primary key,
    nome varchar (100),
    email varchar (100),
    matricula varchar(20),
    data_nascimento date
);
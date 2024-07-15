create table tutores (

    id UUID default RANDOM_UUID() primary key,
    login varchar(255) not null unique ,
    password varchar(255) not null ,
    nome varchar(255) not null ,
    cidade varchar(255) not null ,
    telefone varchar(100) ,
    sobre_mim varchar(255) not null

);
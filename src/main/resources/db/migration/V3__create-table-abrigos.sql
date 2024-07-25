create table abrigos (

    id UUID default RANDOM_UUID() primary key ,
    nome varchar(255) not null ,
    cnpj varchar(14) not null unique ,
    telefone varchar(11) not null ,
    cep varchar(9) not null ,
    logradouro varchar(255) not null ,
    numero numeric(7) ,
    bairro varchar(255) not null ,
    cidade varchar(255) not null ,
    uf varchar(4) not null ,
    users_id UUID ,
    foreign key(users_id) references users(id)
);
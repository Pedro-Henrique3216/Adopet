create table pets (

    id UUID default RANDOM_UUID() primary key ,
    abrigo_id UUID,
    foreign key(abrigo_id) references abrigos(id) on delete cascade ,
    nome varchar(255) not null ,
    descricao varchar(100) not null ,
    adotado BOOLEAN not null ,
    idade varchar(100) not null ,
    cep varchar(9) not null ,
    logradouro varchar(255) not null ,
    numero numeric(7) ,
    bairro varchar(255) not null ,
    cidade varchar(255) not null ,
    uf varchar(4) not null ,
    imagem varchar(255) not null
);
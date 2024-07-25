create table tutores (

    id UUID default RANDOM_UUID() primary key,
    nome varchar(255) not null ,
    cidade varchar(255) not null ,
    telefone varchar(100) ,
    sobre_mim varchar(255) not null,
    users_id UUID ,
    foreign key (users_id) references users(id)

);
create table users(

    id UUID default RANDOM_UUID() primary key ,
    username varchar(255) not null unique ,
    password varchar(150) not null ,
    role varchar(155) not null
);
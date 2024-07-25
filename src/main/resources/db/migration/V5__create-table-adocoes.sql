create table adocoes(

    id UUID default RANDOM_UUID() primary key ,
    animal UUID,
    foreign key (animal) references pets(id) on delete cascade ,
    tutor UUID,
    foreign key (tutor) references tutores(id) on delete cascade ,
    data timestamp not null
);
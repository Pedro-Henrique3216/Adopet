create table adocoes(

    id UUID default RANDOM_UUID() primary key ,
    animal UUID,
    foreign key (animal) references pets(id),
    tutor UUID,
    foreign key (tutor) references tutores(id) ,
    data timestamp not null
);
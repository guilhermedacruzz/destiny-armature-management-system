use sgda1;

drop table if exists table_attributes;
create table table_attributes(
	cod_attributes int not null auto_increment,
    mobility int not null,
    resilience int not null,
    recovery int not null,
    dicipline int not null,
    intellect int not null,
    strenght int not null,
    PRIMARY KEY(cod_attributes)
);

insert into table_attributes(mobility, resilience, recovery, dicipline, intellect, strenght) values(9, 9, 20, 4, 27, 4);
select * from table_attributes;
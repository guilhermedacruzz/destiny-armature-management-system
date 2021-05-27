use sgda1;

drop table if exists table_armor_attributes;
create table table_armor_attributes(
	cod_armor INT not null,
    cod_attributes INT not null,
    
    FOREIGN KEY(cod_armor) references table_armor(cod_armor),
    FOREIGN KEY(cod_attributes) references table_attributes(cod_attributes)
);


use sgda1;

drop table if exists table_armor;
create table table_armor(
	cod_armor INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    guardian_class VARCHAR(45) NOT NULL,
    type VARCHAR(45) NOT NULL,
    rarity VARCHAR(45) NOT NULL,
    status TINYINT  NOT NULL,
    status_masterprice TINYINT NOT NULL,
    element VARCHAR(45) NOT NULL,
    
    PRIMARY KEY(cod_armor)
);

insert into table_armor(name, guardian_class, type, rarity, status, status_masterprice, element) values ("teste", "a", "b", "c", 1, 0, "d");
select * from table_armor;
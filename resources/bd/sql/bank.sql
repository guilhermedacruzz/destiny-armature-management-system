create database sgda6;
use sgda6;

DROP TABLE table_user;
CREATE TABLE table_user (
	cod_user INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    surname VARCHAR(255),
    username VARCHAR(255) NOT NULL,
    secret VARCHAR(45) NOT NULL,
    
    ehancement_module INT,
    improvement_core INT,
    enhancement_prism INT,
    ascendent_fragments INT,
    legendary_fragments INT,
    lumen INT,
    
    PRIMARY KEY(cod_user)
);

drop table if exists table_armor;
create table table_armor(
	cod_armor INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    guardian_class VARCHAR(45) NOT NULL,
    type VARCHAR(45) NOT NULL,
    rarity VARCHAR(45) NOT NULL,
    status TINYINT, 
    status_masterprice TINYINT NOT NULL,
    element VARCHAR(45) NOT NULL,
    cod_user INTEGER NOT NULL,
    
    PRIMARY KEY(cod_armor),
	FOREIGN KEY(cod_user) references table_user(cod_user)
);

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

drop table if exists table_armor_attributes;
create table table_armor_attributes(
	cod_armor INT not null,
    cod_attributes INT not null,
    
    FOREIGN KEY(cod_armor) references table_armor(cod_armor),
    FOREIGN KEY(cod_attributes) references table_attributes(cod_attributes)
);




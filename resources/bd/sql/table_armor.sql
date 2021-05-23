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
    cod_user INTEGER NOT NULL,
    
    PRIMARY KEY(cod_armor),
	FOREIGN KEY(cod_user) references table_user(cod_user)
);
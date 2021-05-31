
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
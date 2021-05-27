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

delimiter $$
drop function if exists sum_attributes $$
create function sum_attributes (index_attribute int)
returns INT
deterministic 
begin
	declare mob, res, rec, dic, inte, stre int;
    
    select mobility, resilience, recovery, dicipline, intellect, strenght
    into mob, res, rec, dic, inte, stre
    from table_attributes where cod_attributes = index_attribute;
    
	return mob + res + rec + dic + inte + stre;
end $$
delimiter ;

delimiter $$
drop function if exists random_integer $$
create function random_integer (start_number int, stop_number int)
returns int
READS SQL DATA
NOT DETERMINISTIC 
begin
	return FLOOR(start_number + RAND() * (stop_number - start_number + 1));
end $$
delimiter ;

delimiter $$
drop procedure if exists check_armor $$
create procedure check_armor(new_armor VARCHAR(45), out retorno VARCHAR(45))
begin

    declare result int default 0;
    declare bd_armor VARCHAR(45) default "";
    declare exist_more_lines int default 0;
    
    declare cursor_armors cursor for select name from table_armor;
    
    declare continue handler for not found set exist_more_lines = 1;
    
    open cursor_armors;
    
    myLoop:LOOP
		fetch cursor_armors into bd_armor;
        
        if exist_more_lines = 1 then
			leave myLoop;
		end if;
        
        if not strcmp(bd_armor, new_armor) then
			set result = 1;
        end if;
	end loop myLoop;
    
    close cursor_armors;
    set retorno = result;
    
end $$
delimiter ;

delimiter $$
drop procedure if exists check_username $$
create procedure check_username(new_username VARCHAR(45), out retorno VARCHAR(45))
begin

    declare result int default 0;
    declare bd_username VARCHAR(45) default "";
    declare exist_more_lines int default 0;
    
    declare cursor_usernames cursor for select username from table_user;
    
    declare continue handler for not found set exist_more_lines = 1;
    
    open cursor_usernames;
    
    myLoop:LOOP
		fetch cursor_usernames into bd_username;
        
        if exist_more_lines = 1 then
			leave myLoop;
		end if;
        
        if not strcmp(BINARY bd_username, new_username) then
			set result = 1;
        end if;
	end loop myLoop;
    
    close cursor_usernames;
    set retorno = result;
    
end $$
delimiter ;

delimiter $$
drop procedure if exists type_armor $$
create procedure type_armor(IN type_armor VARCHAR(25))
begin
	select a.name, a2.mobility, a2.resilience, a2.recovery, a2.dicipline, a2.intellect, a2.strenght
    from table_armor a , table_armor_attributes a1, table_attributes a2
    where a.cod_armor=a1.cod_armor and a1.cod_attributes=a2.cod_attributes
    and a.type=type_armor;
end $$
delimiter ;

delimiter $$
drop procedure if exists rarity_type_armor $$
create procedure rarity_type_armor(IN type_armor VARCHAR(25), IN rarity_armor VARCHAR(25))
begin
	select a.name, a2.mobility, a2.resilience, a2.recovery, a2.dicipline, a2.intellect, a2.strenght
    from table_armor a , table_armor_attributes a1, table_attributes a2
    where a.cod_armor=a1.cod_armor and a1.cod_attributes=a2.cod_attributes
    and a.type=type_armor and a.rarity=rarity_armor;
end $$
delimiter ;

delimiter $$
drop trigger if exists duplicate_armor $$
create trigger duplicate_armor before insert on table_armor for each
row
begin
    declare result int;
    
    call check_armor(new.name, result);
    
    set new.name = UPPER(new.name);
    set new.status = true;
    
    if result = 1 then
        set new.name = CONCAT(new.name, "#", CONVERT(random_integer(100, 1000), CHAR));
	end if;
end$$
delimiter ;

delimiter $$
drop trigger if exists duplicate_username $$
create trigger duplicate_username before insert on table_user for each
row
begin
	declare msg varchar(255);
    declare result int;
    
    call check_username(new.username, result);
    
    if result = 1 then
		set msg = "Username indisponível";
        signal sqlstate '45000' set message_text = msg;
	else
		set new.ehancement_module = 999;
		set new.improvement_core = 999;
		set new.enhancement_prism = 50;
		set new.ascendent_fragments = 10;
		set new.legendary_fragments = 9999;
		set new.lumen = 250000;
	end if;
end$$
delimiter ;



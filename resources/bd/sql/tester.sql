use sgda77;

SET FOREIGN_KEY_CHECKS = 0;
truncate table table_armor_attributes;
truncate table table_attributes;
truncate table table_armor;
truncate table table_user;
SET FOREIGN_KEY_CHECKS = 1;

insert into table_user(name, surname, username, secret) values("Roger", "Roger", "A Lenda Roger", "1234");
# Trigger 1 - Impede o cadastro de um usuário com mesmo username.
insert into table_user(name, surname, username, secret) values("Roger 5k", "Roger 6k", "A Lenda Roger", "12345678"); # Retorná um erro de username duplicado.alter
insert into table_user(name, surname, username, secret) values("Jeferson", "Jeferson", "A lenda Jeferson", "1234"); 

insert into table_attributes(mobility, resilience, recovery, dicipline, intellect, strenght) values(22, 2, 12, 2, 2, 2);
insert into table_armor(name, guardian_class, type, rarity, status, status_masterprice, element, cod_user) 
values("Fogo da Fênix", "Arcano", "Item de Classe", "Lendário", 1, 1, "Arco", 1);
insert into table_armor_attributes(cod_armor, cod_attributes) values(1, 1);

insert into table_attributes(mobility, resilience, recovery, dicipline, intellect, strenght) values(11, 11, 18, 14, 25, 8);
insert into table_attributes(mobility, resilience, recovery, dicipline, intellect, strenght) values(12, 10, 19, 14, 23, 11);
insert into table_armor(name, guardian_class, type, rarity, status, status_masterprice, element, cod_user) 
values("Vèu da Apoteose", "Arcano", "Capacete", "Exótico", 1, 1, "Arco", 1);
insert into table_armor_attributes(cod_armor, cod_attributes) values(2, 2);
insert into table_armor_attributes(cod_armor, cod_attributes) values(2, 3);

insert into table_attributes(mobility, resilience, recovery, dicipline, intellect, strenght) values(4, 8, 26, 18, 20, 10);
insert into table_armor(name, guardian_class, type, rarity, status, status_masterprice, element, cod_user) 
values("Ataduras da Canalização (CODA)", "Arcano", "Manopla", "Lendário", 1, 1, "Solar", 1);
insert into table_armor_attributes(cod_armor, cod_attributes) values(3, 4);

insert into table_attributes(mobility, resilience, recovery, dicipline, intellect, strenght) values(13, 11, 24, 4, 31, 24);
insert into table_armor(name, guardian_class, type, rarity, status, status_masterprice, element, cod_user) 
values("Sola da Canalização (CODA)", "Arcano", "Armadura de Perna", "Lendário", 1, 1, "Vácuo", 1);
insert into table_armor_attributes(cod_armor, cod_attributes) values(4, 5);

insert into table_attributes(mobility, resilience, recovery, dicipline, intellect, strenght) values(9, 9, 20, 14, 27, 4);
insert into table_armor(name, guardian_class, type, rarity, status, status_masterprice, element, cod_user) 
values("Traço do Tesserato IV", "Arcano", "Armadura de Torso", "Lendário", 1, 1, "Arco", 1);
insert into table_armor_attributes(cod_armor, cod_attributes) values(5, 6);

insert into table_attributes(mobility, resilience, recovery, dicipline, intellect, strenght) values(8, 4, 23, 4, 4, 38);
# Trigger 2 - Coloca uma # com um número aleatório nas armaduras com nomes duplicados. Ex: Traço do Tesserato IV#89
insert into table_armor(name, guardian_class, type, rarity, status, status_masterprice, element, cod_user) 
values("Traço do Tesserato IV", "Titan", "Capacete", "Lendário", 1, 1, "Solar", 2);  # Itens com o mesmo nome recebem um # de duplicata.
insert into table_armor_attributes(cod_armor, cod_attributes) values(6, 7);

insert into table_attributes(mobility, resilience, recovery, dicipline, intellect, strenght) values(14, 4, 20, 9, 14, 22);
insert into table_armor(name, guardian_class, type, rarity, status, status_masterprice, element, cod_user) 
values("Braçadeiras do Sol", "Titan", "Manoplas", "Exótico", 1, 1, "Vácuo", 2);
insert into table_armor_attributes(cod_armor, cod_attributes) values(7, 8);

insert into table_attributes(mobility, resilience, recovery, dicipline, intellect, strenght) values(31, 4, 30, 9, 8, 22);
insert into table_armor(name, guardian_class, type, rarity, status, status_masterprice, element, cod_user) 
values("Vestimentas da Caçada Selvagem", "Titan", "Armdura de Torso", "Lendário", 1, 1, "Arco", 2);
insert into table_armor_attributes(cod_armor, cod_attributes) values(8, 9);

insert into table_attributes(mobility, resilience, recovery, dicipline, intellect, strenght) values(18, 11, 20, 8, 12, 18);
insert into table_armor(name, guardian_class, type, rarity, status, status_masterprice, element, cod_user) 
values("Botas da Caçada Selvagem", "Titan", "Armdura de Perna", "Lendário", 1, 1, "Arco", 2);
insert into table_armor_attributes(cod_armor, cod_attributes) values(9, 10);

insert into table_attributes(mobility, resilience, recovery, dicipline, intellect, strenght) values(2, 2, 12, 2, 2, 2);
insert into table_armor(name, guardian_class, type, rarity, status, status_masterprice, element, cod_user) 
values("Voto da Cristocrene", "Titan", "Item de Classe", "Lendário", 1, 1, "Arco", 2);
insert into table_armor_attributes(cod_armor, cod_attributes) values(10, 11);


insert into table_attributes(mobility, resilience, recovery, dicipline, intellect, strenght) values(13, 13, 2, 9, 14, 7);
insert into table_armor(name, guardian_class, type, rarity, status, status_masterprice, element, cod_user)
values("Capuz do Exílio", "Arcano", "Capacete", "Lendário", 1, 0, "Solar", 1);
insert into table_armor_attributes(cod_armor, cod_attributes) values(11, 12);

# Ver os Cadastros
select * from table_user;
select * from table_armor;
select * from table_attributes;
select * from table_armor_attributes;

# Functions
select sum_attributes(1);
select sum_attributes(9);

select random_integer(0, 100);
select random_integer(46, 257);
select random_integer(999, 1000);

# Stored 
# As Stored com Cursores são usadas nas Triggers.
call rarity_armor(1, "Lendário");
call rarity_armor(2, "Lendário");
call rarity_armor(1, "Exótico");
call rarity_armor(2, "Exótico");

call rarity_type_armor(1, "Manopla", "Lendário");
call rarity_type_armor(1, "Capacete", "Exótico");

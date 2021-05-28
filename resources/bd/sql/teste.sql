use sgda6;
delimiter $$
drop procedure if exists rarity_armor $$
create procedure rarity_armor(IN id int, IN rarity_armor VARCHAR(25))
begin
	select *
    from table_armor a , table_armor_attributes a1, table_attributes a2
    where a.cod_armor=a1.cod_armor and a1.cod_attributes=a2.cod_attributes
    and a.rarity=rarity_armor and a.cod_user=id;
end $$
delimiter ;
call rarity_armor(1, "Exótico");
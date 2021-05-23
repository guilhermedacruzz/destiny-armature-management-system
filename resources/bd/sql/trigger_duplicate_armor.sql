use sgda1;

delimiter $$
drop trigger if exists duplicate_armor $$
create trigger duplicate_armor before insert on table_armor for each
row
begin
    declare result int;
    
    call check_armor(new.name, result);
    
    if result = 1 then
		set new.name = UPPER(new.name);
        set new.name = CONCAT(new.name, "67");
	end if;
end$$
delimiter ;

insert into table_armor(name, guardian_class, type, rarity, status, status_masterprice, element) values ("teste", "a", "b", "c", 1, 0, "d");
select * from table_armor;
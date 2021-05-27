
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

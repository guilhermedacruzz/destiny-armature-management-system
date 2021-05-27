
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
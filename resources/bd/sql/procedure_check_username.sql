
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
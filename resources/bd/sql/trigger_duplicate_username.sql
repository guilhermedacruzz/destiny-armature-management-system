
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
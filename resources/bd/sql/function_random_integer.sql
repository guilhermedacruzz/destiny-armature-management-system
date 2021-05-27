
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


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



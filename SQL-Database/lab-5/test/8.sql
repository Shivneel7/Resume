select count(distinct ps_suppkey)
from partsupp, part
where ps_partkey = p_partkey
and instr(p_type, 'POLISHED') > 0
and (p_size = 3 
    or p_size = 23 
    or p_size = 36 
    or p_size = 49)
;

-- select count(distinct ps_suppkey)
-- from partsupp, part
-- where ps_partkey = p_partkey
-- and instr(p_type, 'POLISHED') > 0
-- and (p_size = 3 
--     or p_size = 23 
--     or p_size = 36 
--     or p_size = 49)
-- and ps_partkey != 'fish'
-- ;


-- and p_size = 3
-- or p_size = 23
-- or p_size = 36
-- or p_size = 49;
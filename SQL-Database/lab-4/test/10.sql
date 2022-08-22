-- select p_type, MIN(l_discount), MAX(l_discount)
-- from part, lineitem
-- where (SELECT INSTR(p_type, 'ECONOMY') from part) > 0
-- and (SELECT INSTR(p_type, 'COPPER') from part) > 0
-- and l_partkey = p_partkey;

-- SELECT distinct p_type, INSTR(p_type, 'ECONOMY') as econ, INSTR(p_type, 'COPPER') as cop
-- from part
-- where econ > 0
-- and cop > 0;

SELECT p_type, MIN(l_discount), MAX(l_discount)
from part, lineitem
where INSTR(p_type, 'ECONOMY') > 0
and INSTR(p_type, 'COPPER')
and l_partkey = p_partkey
group by p_type;
-- select p_type
-- from part
-- where INSTR(p_type, 'STEEL') > 0;

select s_name, p_size, min(ps_supplycost)
from part, region, nation, supplier, partsupp
where INSTR(p_type, 'STEEL') > 0
and r_name = 'ASIA'
and r_regionkey = n_regionkey
and n_nationkey = s_nationkey
and ps_suppkey = s_suppkey
and ps_partkey = p_partkey
group by p_size;
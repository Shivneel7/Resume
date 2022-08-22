select p_mfgr 
from part, partsupp, supplier
where p_partkey = ps_partkey
and ps_suppkey = s_suppkey
and s_name = 'Supplier#000000010'
and ps_availqty = 
    (select min(ps_availqty) 
    from part, partsupp, supplier
    where p_partkey = ps_partkey
    and ps_suppkey = s_suppkey
    and s_name = 'Supplier#000000010');
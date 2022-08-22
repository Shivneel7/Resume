select p_name
from nation, part, partsupp, supplier
where n_name = 'UNITED STATES'
and ps_suppkey = s_suppkey
and p_partkey = ps_partkey
and s_nationkey = n_nationkey
order by (ps_supplycost * ps_availqty) desc
LIMIT 
    cast (.01 * (select count(distinct ps_partkey)
        from part, nation, supplier, partsupp
        where n_name = 'UNITED STATES'
        and ps_suppkey = s_suppkey
        and p_partkey = ps_partkey
        and s_nationkey = n_nationkey)
    as INTEGER);

-- select count(distinct ps_partkey)
--         from part, nation, supplier, partsupp
--         where n_name = 'UNITED STATES'
--         and ps_suppkey = s_suppkey
--         and p_partkey = ps_partkey
--         and s_nationkey = n_nationkey
    
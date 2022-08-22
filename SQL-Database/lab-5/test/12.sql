-- select sum(ps_supplycost)
-- from partsupp, part, lineitem, supplier
-- where l_partkey = ps_partkey
-- and ps_partkey = p_partkey
-- and s_suppkey = ps_suppkey
-- and l_suppkey = s_suppkey
-- and p_retailprice < 1000
-- and strftime('%Y',l_shipdate) = '1997'
-- and s_suppkey not in 
--     (select distinct l_suppkey from lineitem, orders
--     where l_extendedprice >= 2000
--     and o_orderkey = l_orderkey
--     and strftime('%Y',o_orderdate) = '1996');

select sum(ps_supplycost)
from partsupp, part, lineitem, supplier
where l_partkey = ps_partkey
and ps_partkey = p_partkey
and s_suppkey = ps_suppkey
-- and l_suppkey = s_suppkey
and p_retailprice < 1000
and strftime('%Y',l_shipdate) = '1997'
and s_suppkey not in 
    (select distinct l_suppkey from lineitem
    where l_extendedprice < 2000
    and strftime('%Y',l_shipdate) = '1996');

-- select sum(ps_supplycost)
-- from partsupp, part, lineitem, supplier
-- where l_partkey = ps_partkey
-- and ps_partkey = p_partkey
-- and s_suppkey = ps_suppkey
-- and l_suppkey = s_suppkey
-- and p_retailprice < 1000
-- and strftime('%Y',l_shipdate) = '1997';

-- select distinct l_suppkey, l_extendedprice,strftime('%Y',l_shipdate) from lineitem
--     where l_extendedprice > 2000
--     and strftime('%Y',l_shipdate) = '1996';

-- select l_suppkey from lineitem, orders
--     where l_extendedprice >= 2000
--     and o_orderkey = l_orderkey
--     and strftime('%Y',o_orderdate) = '1996';
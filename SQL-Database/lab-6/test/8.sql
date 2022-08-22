-- First find orders supplied exclusively by american suppliers

-- select count(distinct o_custkey) from 
--     (select o_custkey, o_orderkey 
--     from supplier, nation, orders, region, lineitem
--         WHERE s_nationkey = n_nationkey
--         and r_regionkey = n_regionkey
--         and l_orderkey = o_orderkey
--         and s_suppkey = l_suppkey
--         and r_name = 'AMERICA')
-- ;


select count(distinct o_custkey) from orders, customer
where o_orderkey not in(select distinct o_orderkey
from region, orders, supplier, lineitem, nation
where s_nationkey = n_nationkey
and r_regionkey = n_regionkey
and l_orderkey = o_orderkey
and s_suppkey = l_suppkey
and r_name != 'AMERICA')
and c_custkey = o_custkey;

-- find the orders supplied exclusively by suppliers from america
-- select o_orderkey not in(select distinct o_orderkey
-- from region, orders, supplier, lineitem, nation
-- where s_nationkey = n_nationkey
--     and r_regionkey = n_regionkey
--     and l_orderkey = o_orderkey
--     and s_suppkey = l_suppkey
--     and r_name != 'AMERICA');
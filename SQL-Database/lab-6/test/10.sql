--Find the region where customers spend the smallest amount of
--  money (lextendedprice) buying itemsfrom suppliers in the same 
--  region.


-- select r1.r_name, min(l_extendedprice)
-- from region r1, nation n1, region r2, nation n2, supplier, customer, orders, lineitem
-- where o_orderkey = l_orderkey
-- and l_suppkey = s_suppkey
-- and s_nationkey = n1.n_nationkey
-- and n1.n_regionkey = r1.r_regionkey
-- and r1.r_regionkey = r2.r_regionkey
-- and r2.r_regionkey = n2.n_regionkey
-- and n2.n_nationkey = c_nationkey
-- and c_custkey = o_custkey
-- group by r1.r_name
-- ;

-- select r_name, min(l_extendedprice)
-- from region, nation n1, nation n2, supplier, customer, orders, lineitem
-- where o_orderkey = l_orderkey
-- and l_suppkey = s_suppkey
-- and s_nationkey = n1.n_nationkey
-- and n1.n_regionkey = n2.n_regionkey
-- and n2.n_nationkey = c_nationkey
-- and c_custkey = o_custkey
-- and n1.n_nationkey = r_regionkey
-- group by r_name
-- ;
select r_name from (
select r_name, min(sumofprice) from (
select r1.r_name, sum(l_extendedprice) as sumofprice
from region r1, nation n1, region r2, nation n2, supplier, customer, orders, lineitem
where o_orderkey = l_orderkey
and l_suppkey = s_suppkey
and s_nationkey = n1.n_nationkey
and n1.n_regionkey = r1.r_regionkey
and r1.r_regionkey = r2.r_regionkey
and r2.r_regionkey = n2.n_regionkey
and n2.n_nationkey = c_nationkey
and c_custkey = o_custkey
group by r1.r_name))
;
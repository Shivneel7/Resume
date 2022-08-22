select SUBSTR(o_orderdate, 1, 4) as YEAR, count(o_orderkey) 
from orders, nation, supplier, lineitem
where o_orderpriority = '3-MEDIUM' 
and l_suppkey = s_suppkey
and l_orderkey = o_orderkey
and n_nationkey = s_nationkey
and n_name = 'CANADA'
group by YEAR;

-- select strftime('%Y',o_orderdate) as YEAR, count(o_orderkey) 
-- from orders, nation, supplier, lineitem
-- where o_orderpriority = '3-MEDIUM' 
-- and l_suppkey = s_suppkey
-- and l_orderkey = o_orderkey
-- and n_nationkey = s_nationkey
-- and n_name = 'CANADA'
-- group by YEAR;
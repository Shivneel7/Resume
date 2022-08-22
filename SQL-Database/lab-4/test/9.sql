select count(DISTINCT o_clerk) 
from orders, nation, lineitem, supplier
where n_name = 'UNITED STATES'
and l_orderkey = o_orderkey
and s_nationkey = n_nationkey
and s_suppkey = l_suppkey;
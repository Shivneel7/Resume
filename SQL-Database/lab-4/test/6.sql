select s_name, o_orderpriority, count(distinct p_partkey)
from part, supplier, orders, nation, lineitem
where n_name = 'CANADA'
and s_nationkey = n_nationkey
and s_suppkey = l_suppkey
and l_partkey = p_partkey
and l_orderkey = o_orderkey
group by o_orderpriority, s_name;
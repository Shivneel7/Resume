select count(distinct o_orderkey)
from supplier, customer, orders, lineitem
where l_orderkey = o_orderkey
and s_suppkey = l_suppkey
and o_custkey = c_custkey
and c_acctbal > 0
and s_acctbal < 0;


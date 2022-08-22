select count(*)
from lineitem, nation n1, nation n2, region, customer, supplier, orders
where l_suppkey = s_suppkey
and s_nationkey = n1.n_nationkey
and n1.n_regionkey = r_regionkey
and r_name='AFRICA'
and l_orderkey = o_orderkey
and o_custkey = c_custkey
and c_nationkey = n2.n_nationkey
and n2.n_name = 'UNITED STATES';
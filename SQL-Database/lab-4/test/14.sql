select r1.r_name, r2.r_name, max(o_totalprice)
from region r1, region r2, orders, lineitem, supplier, nation n1, nation n2, customer
where r1.r_regionkey = n1.n_regionkey
and s_nationkey = n1.n_nationkey
and l_suppkey = s_suppkey
and l_orderkey = o_orderkey
and o_custkey = c_custkey
and c_nationkey = n2.n_nationkey
and r2.r_regionkey = n2.n_regionkey
group by r1.r_name, r2.r_name;

select n_name, o_orderstatus, count(distinct o_orderkey)
from orders, nation, customer, region
where r_name = 'AMERICA'
and r_regionkey = n_regionkey
and c_nationkey = n_nationkey
and c_custkey = o_custkey
group by o_orderstatus, n_name;
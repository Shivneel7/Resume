select distinct n_name, count(o_orderkey) 
from orders, region ,nation, customer
where c_nationkey = n_nationkey
and c_custkey = o_custkey
and r_name = 'AMERICA'
and r_regionkey = n_regionkey
group by n_name;
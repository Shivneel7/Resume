select c_name, count(o_orderkey) 
from orders, customer, nation
where c_nationkey = n_nationkey
and o_custkey = c_custkey
and n_name = 'GERMANY'
and CAST(strftime('%Y',o_orderdate) AS integer)  =  1993
group by c_name;
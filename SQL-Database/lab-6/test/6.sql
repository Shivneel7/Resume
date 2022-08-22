select s_name, c_name, o_totalprice
from supplier, customer, orders, lineitem
where s_suppkey = l_suppkey
and l_orderkey = o_orderkey
and o_custkey = c_custkey
and o_orderstatus = 'F'
and o_totalprice = (select min(o_totalprice) from orders);
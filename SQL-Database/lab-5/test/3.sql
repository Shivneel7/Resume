select min(l_discount)
from lineitem, orders
where o_orderkey = l_orderkey
and l_discount > 
    (select avg(l_discount) from lineitem, orders
    where o_orderkey = l_orderkey
    and strftime('%Y%m',o_orderdate) = '199610'
    )
and strftime('%Y%m',o_orderdate) = '199610';

-- select strftime('%Y-%m',o_orderdate) from orders;
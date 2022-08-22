select o_orderpriority, count(o_orderkey)
from orders, lineitem
where strftime('%Y', o_orderdate) = '1997'
and l_orderkey = o_orderkey
and l_receiptdate > l_commitdate
group by o_orderpriority;
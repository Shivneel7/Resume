select o_orderpriority, count(distinct o_orderkey)
FROM orders, lineitem
where o_orderdate between '1997-10-01' and '1997-12-31'
and l_orderkey = o_orderkey
and l_receiptdate < l_commitdate
group by o_orderpriority;

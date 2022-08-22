select n_name, numOrders from (
    select n_name, count(distinct o_orderkey) as numOrders
    from orders, supplier, nation, lineitem
    where CAST(strftime('%Y',o_orderdate) AS INT) = 1995
    and o_orderstatus = 'F'
    and s_nationkey = n_nationkey
    and l_suppkey = s_suppkey
    and l_orderkey = o_orderkey
    group by n_name)
where numOrders > 50;
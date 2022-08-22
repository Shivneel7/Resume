select count(distinct l_suppkey)
from (select l_suppkey, count(distinct o_orderkey) as num_orders
        from nation, orders, customer, lineitem
        where o_custkey = c_custkey
        and c_nationkey = n_nationkey
        and (n_name = 'FRANCE' or n_name = 'GERMANY')
        and l_orderkey = o_orderkey
        group by l_suppkey)
where num_orders < 50;


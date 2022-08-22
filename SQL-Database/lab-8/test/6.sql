.eqp on
-- .expert
-- CREATE INDEX nation_idx_n_nationkey_n_name ON nation(n_nationkey, n_name);
-- CREATE INDEX customer_idx_c_custkey ON customer(c_custkey);
-- CREATE INDEX orders_idx_o_orderdate ON orders(o_orderdate);
select distinct n_name
from customer, nation, orders
where c_nationkey=n_nationkey and c_custkey=o_custkey
    and o_orderdate>='1996-09-10' and o_orderdate<='1996-09-12'
order by n_name;

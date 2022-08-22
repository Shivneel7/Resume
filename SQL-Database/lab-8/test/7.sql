.eqp on
-- .expert
-- CREATE INDEX lineitem_idx_l_orderkey ON lineitem(l_orderkey);
-- CREATE INDEX orders_idx_o_custkey_o_orderkey ON orders(o_custkey, o_orderkey);
-- CREATE INDEX customer_idx_c_name_c_custkey ON customer(c_name, c_custkey);

select substr(l_receiptdate,1,7) as month, count(*)
from lineitem, orders, customer
where l_orderkey = o_orderkey and o_custkey = c_custkey
    and c_name = 'Customer#000000010' and l_receiptdate like '1993-%'
group by month;

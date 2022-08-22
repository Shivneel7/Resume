CREATE TRIGGER t1 AFTER INSERT ON orders
Begin 
update orders
set o_orderdate = '2021-12-01'
where o_orderkey = NEW.o_orderkey;
END
;

Insert into orders
select 
((SELECT MAX(o_orderkey) FROM orders) + 1) AS o_orderkey, 
o_custkey,o_orderstatus,o_orderstatus,o_orderdate, 
o_orderpriority, o_clerk, o_shippriority, o_comment
from orders
where o_orderdate LIKE '1996-12-%';

Select count(*)
from orders 
where o_orderdate LIKE '2021-%';
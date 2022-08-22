-- --Create triggers that update the attribute o_orderpriority to HIGH every time a new lineitem tuple
-- is added to or deleted from that order. Delete all the line items corresponding to orders from December
-- 1995. 
-- Put all the SQL statements in file test/4.sql. (3 points)


CREATE TRIGGER t41 AFTER INSERT ON lineitem
Begin 
update orders
set o_orderpriority = 'HIGH'
where NEW.l_orderkey = o_orderkey;
END
;

CREATE TRIGGER t42 AFTER DELETE ON lineitem
Begin 
update orders
set o_orderpriority = 'HIGH'
where OLD.l_orderkey = o_orderkey;
END
;

--It is actually slower with the index
-- CREATE INDEX lineitem_l_orderkey ON lineitem(l_orderkey);

Delete from lineitem
where l_orderkey in 
    (select o_orderkey
    from orders
    where o_orderdate LIKE '1995-12-%');

-- select o_orderkey
--     from orders
--     where o_orderdate LIKE '1995-12-%';


--Write a query that returns the number of HIGH priority orders in the fourth trimester of 1995.

Select count(*)
from orders
where o_orderpriority = 'HIGH'
and o_orderdate BETWEEN '1995-09-01' and '1995-12-31'
;
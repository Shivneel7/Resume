-- select n_name
-- from (select n_name, min(customers) as min_cust
--         from (select n_name, count(c_custkey) as customers
--             from nation, customer
--             where c_nationkey = n_nationkey
--             group by n_name
--             )
--         )
-- ;


select n_name from (
select n_name, min(price) from (
select n_name, c_custkey, sum(o_totalprice) as price
from nation, customer, orders
where o_custkey = c_custkey
and n_nationkey = c_nationkey 
group by n_name));

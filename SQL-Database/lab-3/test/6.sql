-- select distinct n_name from nation, customer, orders
-- where customer.c_nationkey = nation.n_nationkey
-- and o_custkey = c_custkey
-- and o_orderdate >= '1996-09-10'
-- and o_orderdate <= '1996-09-12'
-- order by n_name ASC;

select distinct n_name from nation, customer, orders
where customer.c_nationkey = nation.n_nationkey
and o_custkey = c_custkey
and o_orderdate BETWEEN '1996-09-10' and '1996-09-12'
order by n_name ASC;
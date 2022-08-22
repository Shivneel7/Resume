-- select count(*) from (
--     select distinct o_orderkey from orders, customer, nation
--     where o_orderpriority = '1-URGENT'
--     and o_custkey = c_custkey
--     and c_nationkey = n_nationkey
--     and n_name = 'BRAZIL'
--     and CAST(SUBSTR(o_orderdate, 1, 4) AS integer) >= 1994
--     and CAST(SUBSTR(o_orderdate, 1, 4) AS integer) <= 1997
-- );

select count(o_orderkey) from orders, customer, nation
where o_orderpriority = '1-URGENT'
and o_custkey = c_custkey
and c_nationkey = n_nationkey
and n_name = 'BRAZIL'
and CAST(SUBSTR(o_orderdate, 1, 4) AS integer) >= 1994
and CAST(SUBSTR(o_orderdate, 1, 4) AS integer) <= 1997;

-- select count(o_orderkey) from orders, customer, nation
-- where o_orderpriority = '1-URGENT'
-- and o_custkey = c_custkey
-- and c_nationkey = n_nationkey
-- and n_name = 'BRAZIL'
-- and o_orderdate BETWEEN '1994-01-01' and '1997-12-31';
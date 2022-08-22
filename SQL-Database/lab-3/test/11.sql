-- select count(*) from (
--     select DISTINCT c_custkey from customer, orders, lineitem
--     where c_custkey = o_custkey
--     and o_orderkey = l_orderkey
--     and l_discount >= .1
-- );

select count(distinct c_custkey) from customer, orders, lineitem
where c_custkey = o_custkey
and o_orderkey = l_orderkey
and l_discount >= .1;

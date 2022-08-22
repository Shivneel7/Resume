-- select distinct p_name
-- from customer, orders, nation cust_nation, region cust_region, nation supp_nation, region supp_region, lineitem, supplier
-- where c_custkey = o_custkey
-- and c_nationkey = cust_nation.n_nationkey
-- and cust_nation.r_regionkey = cust_nation.n_regionkey
-- and s_nationkey = supp_nation.n_nationkey
-- and supp_nation.r_regionkey = supp_nation.n_regionkey
-- and o_orderkey = l_orderkey
-- and l_suppkey = s_suppkey
-- and  supp_nation.r_name = 'ASIA'
-- and cust_nation.r_name = 'AMERICA'
-- ;

-- select distinct p_name
-- from customer, nation, region,
-- where c_custkey = o_custkey
-- and c_nationkey = n_nationkey
-- and r_regionkey = n_regionkey
-- and cust_nation.r_name = 'AMERICA'
-- ;

-- This query gives count of all suppliers per part
-- select p_name
--     from lineitem, supplier, nation, region, part
--     where r_regionkey = n_regionkey
--     and n_nationkey = s_nationkey
--     and r_name = 'ASIA'
--     and l_suppkey = s_suppkey
--     and p_partkey = l_partkey
--     group by l_partkey
--     having count(s_suppkey) = 3;

-- select p_name from (
-- select distinct l_partkey 
-- from nation, customer, region, orders, lineitem
-- where n_nationkey = c_nationkey
-- and r_regionkey = n_regionkey
-- and r_name = 'AMERICA'
-- and o_custkey = c_custkey
-- and l_orderkey = c_custkey
-- INTERSECT
-- select l_partkey
-- from lineitem, supplier, nation, region
-- where r_regionkey = n_regionkey
-- and n_nationkey = s_nationkey
-- and r_name = 'ASIA'
-- and l_suppkey = s_suppkey
-- group by l_partkey
-- having count(l_suppkey) = 3), part
-- where p_partkey = l_partkey;

-- select distinct p_name
-- from (
--     select l_partkey as parts, count(distinct s_suppkey) as num_sup 
--     from lineitem, supplier, nation, region
--     where r_regionkey = n_regionkey
--     and n_nationkey = s_nationkey
--     and r_name = 'ASIA'
--     and l_suppkey = s_suppkey
--     group by l_partkey
--     having num_sup = 3), customer, nation, region, orders, lineitem, part
-- where c_nationkey = n_nationkey
-- and r_regionkey = n_regionkey
-- and c_custkey = o_custkey
-- and r_name = 'AMERICA'
-- and parts = l_partkey
-- and l_orderkey = o_orderkey
-- and l_partkey = p_partkey;

select distinct p_name from part, lineitem
where p_partkey in (
    select l_partkey
    from lineitem, supplier, nation, region
    where r_regionkey = n_regionkey
    and n_nationkey = s_nationkey
    and r_name = 'ASIA'
    and l_suppkey = s_suppkey
    group by l_partkey
    having count(s_suppkey) = 3)
and l_orderkey in (
    select o_orderkey
    from nation, customer, region, orders
    where n_nationkey = c_nationkey
    and r_regionkey = n_regionkey
    and r_name = 'AMERICA'
    and o_custkey = c_custkey)
and l_partkey = p_partkey;
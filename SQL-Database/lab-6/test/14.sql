-- numitemssold - numitems bought
-- supplier lextendedprice - customer lextendedprice

-- select cust_nation, s_sum - c_sum from
--     (select n_name as sup_nation, sum(l_extendedprice) as s_sum
--     from nation, lineitem, supplier
--     where s_suppkey = l_suppkey
--     and n_nationkey = s_nationkey
--     and strftime('%Y', l_shipdate) = '1994'
--     group by n_name)
-- ,
--     (select n_name as cust_nation, sum(l_extendedprice) as c_sum
--     from nation, lineitem, orders, customer
--     where o_orderkey = l_orderkey
--     and c_custkey = o_custkey
--     and n_nationkey = c_nationkey
--     and strftime('%Y', l_shipdate) = '1994'
--     group by n_name)
--     where cust_nation = sup_nation;

-- select cust_nation, s_sum - c_sum from
--     (select n_name as sup_nation, sum(o_orderkey) as s_sum
--     from nation, lineitem, supplier, orders
--     where s_suppkey = l_suppkey
--     and n_nationkey = s_nationkey
--     and l_orderkey = o_orderkey
--     and strftime('%Y', l_shipdate) = '1994'
--     group by n_name)
-- ,
--     (select n_name as cust_nation, sum(o_orderkey) as c_sum
--     from nation, lineitem, orders, customer
--     where o_orderkey = l_orderkey
--     and c_custkey = o_custkey
--     and n_nationkey = c_nationkey
--     and l_orderkey = o_orderkey
--     and strftime('%Y', l_shipdate) = '1994'
--     group by n_name)
--     where cust_nation = sup_nation;

    -- select n_name as sup_nation, sum(l_extendedprice) as s_sum
    -- from nation, lineitem, supplier
    -- where s_suppkey = l_suppkey
    -- and n_nationkey = s_nationkey
    -- and strftime('%Y', l_shipdate) = '1994'
    -- group by n_name;

    -- select n_name as cust_nation, sum(l_extendedprice) as c_sum
    -- from nation, lineitem, orders, customer
    -- where o_orderkey = l_orderkey
    -- and c_custkey = o_custkey
    -- and n_nationkey = c_nationkey
    -- and strftime('%Y', l_shipdate) = '1994'
    -- group by n_name;



    select sup_nation, num_out - num_in from
    (select s.n_name as sup_nation, count(o_orderkey) as num_out
    from nation c, nation s, customer, lineitem, supplier, orders
    where s_suppkey = l_suppkey
    and s.n_nationkey = s_nationkey
    and l_orderkey = o_orderkey
    and o_custkey = c_custkey
    and c_nationkey = c.n_nationkey
    and c.n_nationkey != s.n_nationkey
    and strftime('%Y', l_shipdate) = '1994'
    group by s.n_name)
    ,
    (select c.n_name as cust_nation, count(o_orderkey) as num_in
    from nation c, nation s, lineitem, orders, customer, supplier
    where s_suppkey = l_suppkey
    and s.n_nationkey = s_nationkey
    and l_orderkey = o_orderkey
    and o_custkey = c_custkey
    and c_nationkey = c.n_nationkey
    and c.n_nationkey != s.n_nationkey
    and strftime('%Y', l_shipdate) = '1994'
    group by c.n_name)
where cust_nation = sup_nation;


-- select c.n_name as cust_nation, count(o_orderkey) as num_in
--     from nation c, nation s, lineitem, orders, customer, supplier
--     where s_suppkey = l_suppkey
--     and c.n_nationkey = s_nationkey
--     and l_orderkey = o_orderkey
--     and o_custkey = c_custkey
--     and c_nationkey = c.n_nationkey
--     and c.n_nationkey != s.n_nationkey
--     and strftime('%Y', l_shipdate) = '1994'
--     group by c.n_name;

--     select s.n_name as sup_nation, count(o_orderkey) as num_out
--     from nation c, nation s, customer, lineitem, supplier, orders
--     where s_suppkey = l_suppkey
--     and c.n_nationkey = s_nationkey
--     and l_orderkey = o_orderkey
--     and o_custkey = c_custkey
--     and c_nationkey = c.n_nationkey
--     and c.n_nationkey != s.n_nationkey
--     and strftime('%Y', l_shipdate) = '1994'
--     group by s.n_name;
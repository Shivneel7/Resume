select four.sup_nation, five.exchange - four.exchange, six.exchange - five.exchange
from 
    (select sup_nation, num_out - num_in as exchange from
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
    where cust_nation = sup_nation) as four,

    (select sup_nation, num_out - num_in as exchange from
        (select s.n_name as sup_nation, count(o_orderkey) as num_out
        from nation c, nation s, customer, lineitem, supplier, orders
        where s_suppkey = l_suppkey
        and s.n_nationkey = s_nationkey
        and l_orderkey = o_orderkey
        and o_custkey = c_custkey
        and c_nationkey = c.n_nationkey
        and c.n_nationkey != s.n_nationkey
        and strftime('%Y', l_shipdate) = '1995'
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
        and strftime('%Y', l_shipdate) = '1995'
        group by c.n_name)
    where cust_nation = sup_nation) as five,

    (select sup_nation, num_out - num_in as exchange from
        (select s.n_name as sup_nation, count(o_orderkey) as num_out
        from nation c, nation s, customer, lineitem, supplier, orders
        where s_suppkey = l_suppkey
        and s.n_nationkey = s_nationkey
        and l_orderkey = o_orderkey
        and o_custkey = c_custkey
        and c_nationkey = c.n_nationkey
        and c.n_nationkey != s.n_nationkey
        and strftime('%Y', l_shipdate) = '1996'
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
        and strftime('%Y', l_shipdate) = '1996'
        group by c.n_name)
    where cust_nation = sup_nation) as six
where four.sup_nation = five.sup_nation
and four.sup_nation = six.sup_nation;



    -- select sup_nation, num_out - num_in as exchange
    -- from
    --     (select s.n_name as sup_nation, count(o_orderkey) as num_out ,
    --         CASE
    --         when strftime('%Y', l_shipdate) = '1994'
    --             then '1994'
    --         when strftime('%Y', l_shipdate) = '1995'
    --             then '1995'
    --         when strftime('%Y', l_shipdate) = '1996'
    --             then '1996'
    --         END
    --     from nation c, nation s, customer, lineitem, supplier, orders
    --     where s_suppkey = l_suppkey
    --     and s.n_nationkey = s_nationkey
    --     and l_orderkey = o_orderkey
    --     and o_custkey = c_custkey
    --     and c_nationkey = c.n_nationkey
    --     and c.n_nationkey != s.n_nationkey
    --     and strftime('%Y', l_shipdate) = '1994'
    --     group by s.n_name)
    --     ,
    --     (select c.n_name as cust_nation, count(o_orderkey) as num_in ,
    --     CASE
    --         when strftime('%Y', l_shipdate) = '1994'
    --             then '1994'
    --         when strftime('%Y', l_shipdate) = '1995'
    --             then '1995'
    --         when strftime('%Y', l_shipdate) = '1996'
    --             then '1996'
    --         END
    --     from nation c, nation s, lineitem, orders, customer, supplier
    --     where s_suppkey = l_suppkey
    --     and s.n_nationkey = s_nationkey
    --     and l_orderkey = o_orderkey
    --     and o_custkey = c_custkey
    --     and c_nationkey = c.n_nationkey
    --     and c.n_nationkey != s.n_nationkey
    --     group by c.n_name)
    -- where cust_nation = sup_nation;


-- select s.n_name as sup_nation, count(o_orderkey) as num_out ,
--             CASE
--             when strftime('%Y', l_shipdate) = '1994'
--                 then '1994'
--             when strftime('%Y', l_shipdate) = '1995'
--                 then '1995'
--             when strftime('%Y', l_shipdate) = '1996'
--                 then '1996'
--             END
--         from nation c, nation s, customer, lineitem, supplier, orders
--         where s_suppkey = l_suppkey
--         and s.n_nationkey = s_nationkey
--         and l_orderkey = o_orderkey
--         and o_custkey = c_custkey
--         and c_nationkey = c.n_nationkey
--         and c.n_nationkey != s.n_nationkey
--         group by s.n_name;

-- select sup_nation, num_out - num_in as exchange from
--         (select s.n_name as sup_nation, count(o_orderkey) as num_out
--         from nation c, nation s, customer, lineitem, supplier, orders
--         where s_suppkey = l_suppkey
--         and s.n_nationkey = s_nationkey
--         and l_orderkey = o_orderkey
--         and o_custkey = c_custkey
--         and c_nationkey = c.n_nationkey
--         and c.n_nationkey != s.n_nationkey
        
--         group by s.n_name)
--         ,
--         (select c.n_name as cust_nation, count(o_orderkey) as num_in
--         from nation c, nation s, lineitem, orders, customer, supplier
--         where s_suppkey = l_suppkey
--         and s.n_nationkey = s_nationkey
--         and l_orderkey = o_orderkey
--         and o_custkey = c_custkey
--         and c_nationkey = c.n_nationkey
--         and c.n_nationkey != s.n_nationkey
       
--         group by c.n_name)
--     where cust_nation = sup_nation
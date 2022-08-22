-- the fraction of the revenue fromthe line items ordered by 
-- customers in the given region that are supplied by suppliers 
-- from the given nation.

-- revenue = l_extendedprice*(1-l_discount)

-- of all the lineitems ordered, how much of the revenue comes from 
-- united states
-- revenue from line items from US / Total revenue from lineitems
-- total revenue from lineitem = total revenue from lineitems with customer in asia

-- the fraction of the revenue from the line items ordered by Asians that 
--are supplied by US suppliers

select numerator / total
from 
    (select sum(l_extendedprice*(1-l_discount)) as numerator
    from nation s, nation c, region, lineitem, customer, supplier, orders
    where r_name = 'ASIA'
    and s.n_name = 'UNITED STATES'
    and strftime('%Y', l_shipdate) = '1997'
    and l_suppkey = s_suppkey
    and s_nationkey = s.n_nationkey
    and l_orderkey = o_orderkey
    and c_custkey = o_custkey
    and c.n_nationkey = c_nationkey
    and c.n_regionkey = r_regionkey) 
    ,
    (select sum(l_extendedprice*(1-l_discount)) as total
    from nation, region, lineitem, customer, orders
    where r_name = 'ASIA'
    and strftime('%Y', l_shipdate) = '1997'
    and o_custkey = c_custkey
    and c_nationkey = n_nationkey
    and l_orderkey = o_orderkey
    and r_regionkey = n_regionkey)
;


-- select sum(l_extendedprice*(1-l_discount)) as numerator
-- from nation s, nation c, region, lineitem, customer, supplier, orders
-- where r_name = 'ASIA'
-- and s.n_name = 'UNITED STATES'
-- and strftime('%Y', l_shipdate) = '1997'
-- and l_suppkey = s_suppkey
-- and s_nationkey = s.n_nationkey
-- and l_orderkey = o_orderkey
-- and c_custkey = o_custkey
-- and c.n_nationkey = c_nationkey
-- ;

-- select sum(l_extendedprice*(1-l_discount)) as total
-- from nation s, region, lineitem, supplier
-- where r_name = 'ASIA'
-- and strftime('%Y', l_shipdate) = '1997'
-- and l_suppkey = s_suppkey
-- and s_nationkey = s.n_nationkey
-- ;

-- select sum(l_extendedprice*(1-l_discount)) as total
--     from nation, region, lineitem, customer, orders
--     where r_name = 'ASIA'
--     and strftime('%Y', l_shipdate) = '1997'
--     and o_custkey = c_custkey
--     and c_nationkey = n_nationkey
--     and l_orderkey = o_orderkey
--     and r_regionkey = n_nationkey;

--     select sum(l_extendedprice*(1-l_discount)) as numerator
--     from nation s, nation c, region, lineitem, customer, supplier, orders
--     where r_name = 'ASIA'
--     and s.n_name = 'UNITED STATES'
--     and strftime('%Y', l_shipdate) = '1997'
--     and l_suppkey = s_suppkey
--     and s_nationkey = s.n_nationkey
--     and l_orderkey = o_orderkey
--     and c_custkey = o_custkey
--     and c.n_nationkey = c_nationkey
--     and c.n_regionkey = r_regionkey;
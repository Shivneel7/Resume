select sr.r_name, cr.r_name, strftime('%Y',l_shipdate) as year, sum(l_extendedprice*(1-l_discount))
from region sr, region cr, orders, lineitem, customer, supplier, nation cn, nation sn 
where sr.r_regionkey = sn.n_regionkey
and cr.r_regionkey = cn.n_regionkey
and c_nationkey = cn.n_nationkey
and s_nationkey = sn.n_nationkey
and l_suppkey = s_suppkey
and o_orderkey = l_orderkey
and o_custkey = c_custkey
and (year = '1997'
    or year = '1996')
group by sr.r_name, cr.r_name, year;

-- select sr.r_name, cr.r_name, l_shipdate, (l_extendedprice*(1-l_discount))
-- from region sr, region cr, orders, lineitem, customer, supplier, nation cn, nation sn 
-- where sr.r_regionkey = sn.n_regionkey
-- and cr.r_regionkey = cn.n_regionkey
-- and c_nationkey = cn.n_nationkey
-- and s_nationkey = sn.n_nationkey
-- and l_suppkey = s_suppkey
-- and o_orderkey = l_orderkey
-- and (strftime('%Y',l_shipdate) = '1997'
--     or strftime('%Y',l_shipdate) = '1996');
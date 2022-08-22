select SUM(o_totalprice) from orders, customer, nation, region
where o_custkey = c_custkey
and c_nationkey = n_nationkey
and n_regionkey = r_regionkey
and r_name = 'AMERICA'
and CAST(SUBSTR(o_orderdate, 1, 4) AS integer)  =  1996;

-- select SUM(o_totalprice) from orders, customer, nation, region
-- where o_custkey = c_custkey
-- and c_nationkey = n_nationkey
-- and n_regionkey = r_regionkey
-- and r_name = 'AMERICA'
-- and CAST(strftime('%Y',o_orderdate) AS integer)  =  1996;

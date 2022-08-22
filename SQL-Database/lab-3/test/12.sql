SELECT r_name, count(o_orderstatus) from region, nation, customer, orders
where o_orderstatus = 'F'
and o_custkey = c_custkey
and c_nationkey = n_nationkey
and n_regionkey = r_regionkey
group by r_regionkey;
select sum(c_acctbal) from customer, nation, region
WHERE r_name = 'EUROPE'
and r_regionkey = n_regionkey
and n_nationkey = c_nationkey
and c_mktsegment = 'MACHINERY';
select s_name,s_acctbal from supplier, region, nation
where s_acctbal > 5000
and r_name = 'AMERICA'
and r_regionkey = n_regionkey
and s_nationkey = n_nationkey;
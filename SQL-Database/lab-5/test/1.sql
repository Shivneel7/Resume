select count(*) from customer, nation, region
where r_name != 'EUROPE'
and r_name != 'AFRICA'
and r_name != 'ASIA'
and c_nationkey = n_nationkey
and n_regionkey = r_regionkey;
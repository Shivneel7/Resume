select r_name, count(s_name) from supplier,region,nation
where r_regionkey = n_regionkey
and s_nationkey = n_nationkey
group by r_name;
select r_name,s_name, max(s_acctbal)
from region, supplier, nation
where n_regionkey = r_regionkey
and s_nationkey = n_nationkey
group by r_name;
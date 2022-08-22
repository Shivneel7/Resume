select n_name, count(distinct c_custkey), count(distinct s_suppkey)
from supplier, customer, region, nation
where r_name = 'AFRICA'
and n_nationkey = s_nationkey
and n_nationkey = c_nationkey
and n_regionkey = r_regionkey
group by n_name;

-- select n_name, count(distinct c_custkey), count(distinct s_suppkey)
-- from nation
-- inner join customer on n_nationkey = c_nationkey
-- inner join region on r_regionkey = n_nationkey
-- inner join supplier on n_nationkey = s_nationkey
-- where r_name = 'AFRICA'
-- group by n_name;
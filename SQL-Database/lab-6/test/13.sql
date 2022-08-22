
select n_name from (
select n_name, sum(l_extendedprice)
from nation, lineitem, supplier
where s_suppkey = l_suppkey
and n_nationkey = s_nationkey
and strftime('%Y', l_shipdate) = '1994');
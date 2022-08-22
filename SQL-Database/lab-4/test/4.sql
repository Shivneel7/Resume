select s_name, count(p_partkey)
from supplier, part, nation, partsupp
where p_size < 20 
ANd s_nationkey = n_nationkey
and ps_partkey = p_partkey
and ps_suppkey = s_suppkey
ANd n_name = 'CANADA'
group by s_name;
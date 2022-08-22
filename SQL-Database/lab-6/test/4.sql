select count(distinct s_suppkey)
from (select s_suppkey
    from part, supplier, nation, partsupp
    where p_partkey = ps_partkey
    and ps_suppkey = s_suppkey
    and s_nationkey = n_nationkey
    and n_name = 'UNITED STATES'
    GROUP BY
	ps_suppkey
    HAVING
	count(p_partkey) > 40);
;
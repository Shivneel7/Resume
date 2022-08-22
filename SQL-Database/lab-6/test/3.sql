-- select count(s_suppkey)
-- from (select p_partkey
--     from part, supplier s1, nation n1, partsupp, supplier s2, nation n2
--     where p_partkey = ps_partkey
--     and ps_suppkey = s_suppkey
--     and s2.s_nationkey = n2.n_nationkey
--     and s1.s_nationkey = n1.n_nationkey
--     and n1.n_name = 'UNITED STATES'
--     and n2.n_name = 'UNITED STATES'
--     and s1.suppkey != s2.suppkey;
--     )
-- ;

select count(distinct p_partkey)
from (select p_partkey
    from part, supplier, nation, partsupp
    where p_partkey = ps_partkey
    and ps_suppkey = s_suppkey
    and s_nationkey = n_nationkey
    and n_name = 'UNITED STATES'
    GROUP BY
	p_partkey
    HAVING
	count(ps_suppkey) =2);
;

-- select p_partkey
--     from part, supplier, nation, partsupp
--     where p_partkey = ps_partkey
--     and ps_suppkey = s_suppkey
--     and s_nationkey = n_nationkey
--     and n_name = 'UNITED STATES'
--     GROUP BY
-- 	p_partkey
--     HAVING
-- 	count(ps_suppkey) =2;
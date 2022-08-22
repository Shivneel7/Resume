select count(s_suppkey)
from supplier, part, partsupp
where p_retailprice = (select max(p_retailprice) from part)
and s_suppkey = ps_suppkey
and ps_partkey = p_partkey;

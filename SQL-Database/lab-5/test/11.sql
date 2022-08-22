select p_name
from part, lineitem, orders
where l_orderkey = o_orderkey
and p_partkey = l_partkey
AND o_orderdate > 1996-10-02
and l_extendedprice*(1-l_discount) = 
    (select min(l_extendedprice*(1-l_discount))
    from lineitem, orders
    where l_orderkey = o_orderkey
    AND o_orderdate > 1996-10-02 );


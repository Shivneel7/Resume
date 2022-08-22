-- 5. Create a trigger t5 that removes all the tuples from partsupp and lineitem corresponding to a part
-- being deleted. Delete all the parts supplied by suppliers from UNITED STATES or CANADA. Write a query
-- that returns the number of parts supplied by every supplier in AMERICA grouped by their country in
-- increasing order. Put all the SQL statements in file test/5.sql. (3 points)


CREATE TRIGGER t5 AFTER DELETE ON part
Begin 

delete FROM partsupp
where ps_partkey = OLD.p_partkey;

delete FROM lineitem
where l_partkey = OLD.p_partkey;

END;

DELETE from part
where p_partkey in (
    select ps_partkey
    from partsupp, supplier, nation
    where n_nationkey = s_nationkey
    and ps_suppkey = s_suppkey
    and (n_name = 'UNITED STATES'
        or n_name = 'CANADA')
    );

select n_name, count(ps_partkey) as numParts
from supplier, nation, region, partsupp
where s_nationkey = n_nationkey
and r_regionkey = n_regionkey
and ps_suppkey = s_suppkey
and r_name = 'AMERICA'
group by n_name order by numParts Desc;
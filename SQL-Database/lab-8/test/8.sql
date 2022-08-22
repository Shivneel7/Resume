.eqp on
-- .expert
-- CREATE INDEX supplier_idx_s_nationkey_s_acctbal ON supplier(s_nationkey, s_acctbal);
-- CREATE INDEX nation_idx_n_regionkey_n_nationkey ON nation(n_regionkey, n_nationkey);
-- CREATE INDEX region_idx_r_name_r_regionkey ON region(r_name, r_regionkey);
select s_name, s_acctbal
from supplier, nation, region
where n_regionkey=r_regionkey and s_nationkey=n_nationkey
    and r_name='AMERICA' and s_acctbal>5000;

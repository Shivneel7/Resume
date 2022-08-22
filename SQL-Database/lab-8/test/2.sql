.eqp on
-- .expert
-- CREATE INDEX supplier_idx_s_nationkey_s_acctbal ON supplier(s_nationkey, s_acctbal);
-- Drop INDEX supplier_idx_s_nationkey_s_acctbal;

select min(s_acctbal)
from supplier;

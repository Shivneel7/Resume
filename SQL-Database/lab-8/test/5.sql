.eqp on
-- .expert
-- CREATE INDEX customer_idx_c_mktsegment ON customer(c_mktsegment);
select c_mktsegment, min(c_acctbal) as min, max(c_acctbal) as max, sum(c_acctbal) as total
from customer
group by c_mktsegment;

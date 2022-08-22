select c_mktsegment, min(c_acctbal), max(c_acctbal), sum(c_acctbal) FROM
customer group by c_mktsegment;
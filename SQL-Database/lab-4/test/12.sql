select n_name, maxBalance from (
    select n_name,s_name, max(s_acctbal) as maxBalance
    from supplier, nation
    where s_nationkey = n_nationkey
    group by n_name)
where maxBalance > 9000;
select r_name as region1, count(s_suppkey) from supplier, nation, region
where s_acctbal < 
    (select AVG(s_acctbal) as avg_bal
    from region, nation, supplier
    where r_regionkey = n_regionkey
    and n_nationkey = s_nationkey
    and region1 = r_name
    )
and r_regionkey = n_regionkey
and n_nationkey = s_nationkey
group by r_name;
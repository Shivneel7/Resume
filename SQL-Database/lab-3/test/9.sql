select n_name, count(s_nationkey), max(s_acctbal) from supplier, nation
where s_nationkey = n_nationkey
group by s_nationkey
having count(s_nationkey) > 5;
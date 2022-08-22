select n_name
from (select n_name, min(customers) as min_cust
        from (select n_name, count(c_custkey) as customers
            from nation, customer
            where c_nationkey = n_nationkey
            group by n_name
            )
        )
;
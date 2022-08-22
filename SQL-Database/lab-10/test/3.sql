CREATE TRIGGER t3 AFTER Update ON customer
when(NEW.c_acctbal > 0 AND OLD.c_acctbal < 0)
Begin 
update customer
set c_comment = 'Positive balance'
where c_custkey = NEW.c_custkey;
END
;

Update customer
SET c_acctbal = 100
where c_custkey in
    (select c_custkey
    from nation, customer
    where n_nationkey = c_nationkey
    and n_name = 'UNITED STATES')
;


Select count(*)
from nation, customer, region
where n_nationkey = c_nationkey
and r_regionkey = n_regionkey
and r_name = 'AMERICA'
and c_acctbal < 0
;

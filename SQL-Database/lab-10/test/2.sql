CREATE TRIGGER t2 AFTER Update ON customer
when(NEW.c_acctbal < 0 AND OLD.c_acctbal > 0)
Begin 
update customer
set c_comment = 'Negative balance!!!'
where c_custkey = NEW.c_custkey;
END
;

Update customer
SET c_acctbal = -100
where c_custkey in
    (select c_custkey
    from nation, customer, region
    where n_nationkey = c_nationkey
    and r_regionkey = n_regionkey
    and r_name = 'AMERICA')
;


Select count(*)
from customer, nation
where c_acctbal < 0
And n_nationkey = c_nationkey
and n_name = 'CANADA'
;

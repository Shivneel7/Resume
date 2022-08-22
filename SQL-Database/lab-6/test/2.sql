-- select count(c_custkey)
-- from (select c_custkey, o_orderkey as test2,count(strftime('%m%Y',o_orderdate)) as test 
--     from customer, orders
--     where o_custkey = c_custkey
--     and strftime('%m%Y',o_orderdate) = '111995')
--     where  test2 >= 3;
select count(*)
    from (select c_custkey, count(strftime('%m%Y',o_orderdate)) as customers
    from customer, orders
    where o_custkey = c_custkey
    and strftime('%m%Y',o_orderdate) = '111995'
    group by c_custkey)
where customers >= 3;
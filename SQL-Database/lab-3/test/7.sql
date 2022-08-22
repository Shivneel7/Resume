select SUBSTR(l_receiptdate, 1, 7), count(l_receiptdate) from lineitem, customer, orders
where c_name = 'Customer#000000010'
and o_custkey = c_custkey
and o_orderkey = l_orderkey
and CAST(SUBSTR(l_receiptdate, 1, 4) AS integer)  =  1993
group by CAST(SUBSTR(l_receiptdate, 6, 2) AS integer);

-- select strftime('%Y-%m',l_receiptdate), count(l_receiptdate) from lineitem, customer, orders
-- where c_name = 'Customer#000000010'
-- and o_custkey = c_custkey
-- and o_orderkey = l_orderkey
-- and CAST(strftime('%Y',l_receiptdate) as integer) = 1993
-- group by strftime('%m',l_receiptdate);

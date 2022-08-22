select c_name, sum(o_totalprice) FROM
customer, orders, nation
WHERe c_custkey = o_custkey and 
n_name = 'FRANCE' AND 
CAST(SUBSTR(o_orderdate, 1, 4) AS integer)  =  1995 AND
c_nationkey = n_nationkey
group by c_name;
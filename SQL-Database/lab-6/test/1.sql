select strftime('%m', l_shipdate) as month, sum(l_quantity)
from lineitem
where strftime('%Y', l_shipdate) = '1995'
group by month;
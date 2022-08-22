-- SQLite
.headers on
-- .schema
-- select * from Product;
-- select * from PC;
-- select * from Laptop;
-- select * from Printer;

--1 ---------------------------------------------------------------
select maker
from Product, Printer
where Printer.price < 120 AND Printer.COLOR = 1
AND Printer.Model = Product.Model;

--2 ---------------------------------------------------------------
-- select distinct maker from  Product where type  = 'pc';
-- select maker
-- from Product
-- where type = 'pc'
-- EXCEPT
-- select maker
-- from Product
-- where type = 'laptop'
-- EXCEPT
-- select maker
-- from Product
-- where type = 'printer';

select distinct maker
from Product
where type = 'pc'
and maker != (select maker
from Product
where type = 'laptop'
INTERSECT
select maker
from Product
where type = 'printer');

--3 ---------------------------------------------------------------
select distinct P.maker, PC.model as PC_Model, L.model as Laptop_Model, 
max(PC.price + L.price) as combined_Price
from Product P, Laptop L, PC
where P.maker in (select maker from Product where type = 'pc' and model = PC.model
INTERSECT 
select maker from Product where type = 'laptop' and model = L.model)
group by maker;


-- select distinct P.maker, PC.model as PC_Model, L.model as Laptop_Model, 
-- (PC.price + L.price) as combined_Price
-- from Product P, Laptop L, PC
-- where P.maker in
-- (select maker from Product where type = 'pc' and model = PC_Model
-- INTERSECT 
-- select maker from Product where type = 'laptop' and model = Laptop_Model)
-- group by combined_Price;


--4 ---------------------------------------------------------------
--Here I take the list, join it with itself, and then look for
-- anywhere where the screens are equal and the model is different
SELECT distinct l1.screen as screenSize from Laptop l1, Laptop l2
where  l1.model != l2.model AND screenSize = l2.screen;

--TWO WAYS TO DO THE SAME THING FOR FUN
-- SELECT distinct l1.screen as s1 from Laptop l1 JOIN Laptop l2
-- on l1.model != l2.model AND s1 = l2.screen;

--5 ---------------------------------------------------------------
SELECT Laptop.model, Laptop.price from Laptop
where Laptop.price > (select MAX(PC.price) from PC);

--6 ---------------------------------------------------------------
select distinct maker from Product where type = 'pc' or type = 'laptop'
INTERSECT
SELECT p1.maker from Product p1, Product p2
where  p1.model != p2.model AND p1.type = 'printer';
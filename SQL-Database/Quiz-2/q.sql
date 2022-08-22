-- -- SQLite
-- SELECT distinct maker from product, laptop 
-- where price < 2000 
-- and laptop.model = product.model 
-- and screen > 16;

-- select distinct maker from Product where type = 'pc' or type = 'laptop'
-- INTERSECT
SELECT distinct p1.maker from Product p1, Product p2
where  p1.model != p2.model AND p1.type = 'printer' AND p2.type='printer';
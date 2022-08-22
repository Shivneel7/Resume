SELECT "1----------";
.headers on
CREATE TABLE Classes (
    class           char(25),
    type            char(2),
    country         char(25),
    numGuns         decimal(2,0),
    bore            decimal(2,0),
    displacement    decimal(5,0)
);
CREATE TABLE Ships(
    name        char(25),
    class       char(25),
    launched    decimal(4,0)
);
CREATE TABLE Battles(
    name char(25),
    date date
);

CREATE TABLE Outcomes(
    ship    char(25),
    battle  char(25),
    result  char(10)
);

.schema

;
.headers off

SELECT "2----------";
.headers on

INSERT INTO Classes
Values
    ('Bismarck','bb','Germany',8,15,42000),
    ('Iowa','bb','USA',9,16,46000),
    ('Kongo', 'bc','Japan', 8, 14, 32000),
    ('North Carolina','bb','USA',9,16,37000),
    ('Renown' ,'bc', 'Britain' ,6,15,32000),
    ('Revenge', 'bb', 'Britain', 8 ,15 ,29000),
    ('Tennessee', 'bb', 'USA', 12, 14, 32000),
    ('Yamato', 'bb', 'Japan', 9, 18, 65000);

INSERT INTO Ships
VALUES
    ('California', 'Tennessee', 1915),
    ('Haruna', 'Kongo', 1915),
    ('Hiei', 'Kongo', 1915),
    ('Iowa', 'Iowa', 1933),
    ('Kirishima','Kongo', 1915),
    ('Kongo', 'Kongo', 1913),
    ('Missouri', 'Iowa', 1935),
    ('Musashi', 'Yamato', 1942),
    ('New Jersey', 'Iowa', 1936),
    ('North Carolina', 'North Carolina', 1941),
    ('Ramillies', 'Revenge', 1917),
    ('Renown', 'Renown', 1916),
    ('Repulse', 'Renown' ,1916),
    ('Resolution', 'Revenge', 1916),
    ('Revenge', 'Revenge', 1916),
    ('Royal Oak', 'Revenge', 1916),
    ('Royal Sovereign', 'Revenge' ,1916),
    ('Tennessee', 'Tennessee' ,1915),
    ('Washington', 'North Carolina', 1941),
    ('Wisconsin', 'Iowa' ,1940),
    ('Yamato', 'Yamato', 1941);

INSERT INTO Battles
VALUES 
    ('Denmark Strait' ,'1941-05-24'),
    ('Guadalcanal','1942-11-15'),
    ('North Cape' ,'1943-12-26'),
    ('Surigao Strait', '1944-10-25');

INSERT INTO Outcomes
VALUES
    ('California', 'Surigao Strait', 'ok'),
    ('Kirishima', 'Guadalcanal', 'sunk'),
    ('Resolution', 'Denmark Strait', 'ok'),
    ('Wisconsin', 'Guadalcanal', 'damaged'),
    ('Tennessee', 'Surigao Strait', 'ok'),
    ('Washington', 'Guadalcanal', 'ok'),
    ('New Jersey', 'Surigao Strait', 'ok'),
    ('Yamato', 'Surigao Strait', 'sunk'),
    ('Wisconsin', 'Surigao Strait', 'damaged');

SELECT * from Classes;
SELECT * from Ships;
SELECT * from Battles;
SELECT * from Outcomes;
;
.headers off

SELECT "3----------";
.headers on

SELECT country, count(DISTINCT s.name)
from Ships s, Classes c
where s.class = c.class
and launched BETWEEN 1930 AND 1940
group by country
;
.headers off

SELECT "4----------";
.headers on

INSERT INTO Outcomes
SELECT name, 'Denmark Strait', 'damaged'
from Ships
where launched <= 1920
and name not in (select ship from outcomes
                 where battle = 'Denmark Strait')
;

select * from Outcomes;

.headers off

SELECT "5----------";
.headers on
--For every country, find the number 
--of damaged ships it has in battles.
Select country, count(Distinct o.ship)
from Classes c , Outcomes o, Ships s
where s.name = o.ship
and c.class = s.class
and o.result = 'damaged'
group by c.country
-- select ship from Outcomes, Ships, Classes where result = 'damaged'
-- and ship = name
-- and Classes.class = Ships.class;
;
.headers off

SELECT "6----------";
.headers on

select country from (
select country, min(numShips) from
    (Select country, count(Distinct o.ship) as numShips
    from Classes c , Outcomes o, Ships s
    where s.name = o.ship
    and c.class = s.class
    and o.result = 'damaged'
    group by c.country))
;
.headers off

SELECT "7----------";
.headers on
--Delete from the Outcomes table all the ships from
-- Japan that participate in the Denmark Strait battle.

DELETE FROM Outcomes
where ship in
    (SELECT s.name from Ships s, Classes c, Outcomes o
    where s.class = c.class
    and o.ship = s.name
    and battle = 'Denmark Strait'
    and c.country = 'Japan')
;

SELECT * from outcomes;

.headers off

SELECT "8----------";
.headers on

--Find the ships that fought after being damaged
-- SELECT distinct o2.ship from Outcomes o1, (
--     Select ship, battle as b1 from Outcomes
--     where result = 'damaged') as o2
-- where o1.battle != o2.b1

SELECT DISTINCT o1.ship 
FROM Outcomes o1, Outcomes o2, Battles b1, Battles b2
WHERE b1.name = o1.battle
and b2.name = o2.battle
and o1.ship = o2.ship
and o1.battle != o2.battle
and o1.result = 'damaged'
and b1.date < b2.date
;

.headers off

SELECT "9----------";
.headers on

SELECT country
 from Classes 
where type = 'bc'
and country in(
    SELECT country
    from Classes
    where type = 'bb')
;

-- SELECT country
--  from Classes 
-- where type = 'bc'
-- INTERSECT
-- SELECT country
-- from Classes
-- where type = 'bb'
-- ;
.headers off

SELECT "10---------";
.headers on

-- Double numGuns for the classes that 
-- have ships launched in 1940 or later.
UPDATE Classes 
SET numGuns = numGuns * 2
WHERE class in 
    (SELECT class from Ships
    where launched >= 1940)
;

SELECT * from Classes;


.headers off

SELECT "11---------";
.headers on

-- Find the classes that have exactly two ships in the class.
-- SELECT class, count(name) as num from Ships
--     group by class;

SELECT class from (
    SELECT class, count(name) as num from Ships
    group by class)
where num = 2;

.headers off

SELECT "12---------";
.headers on

SELECT class from (
    SELECT class, count(name) as num 
    from Ships
    where name not in 
        (SELECT ship from Outcomes
        where result = 'sunk')
    group by class)
where num = 2;


-- SELECT ship from Outcomes
-- where result = 'sunk';

.headers off

SELECT "13---------";
.headers on

DELETE from Ships
where name in 
    (SELECT ship from Outcomes
    where result = 'sunk');
;

SELECT * from Ships;


.headers off

SELECT "14---------";
.headers on

--Find the total numGuns across all the ships for every country.
    SELECT country, sum(num * numGuns)
    from Classes c , (SELECT class, count(name) as num
                    from Ships 
                    group by class) as d
    where d.class = c.class
    group by country;


-- SELECT class, count(name) as num
-- from Ships 
-- group by class;
;
.headers off

SELECT "15---------";
.headers on



SELECT country, sum(guns) from (
    SELECT country,
    CASE
    when isDam = 1
    then numGuns - 1
    ELSE
        numGuns
    end as guns
    from Classes c ,(
            Select ship, 1 as isDam, class as sc
            from Outcomes, Ships
            where ship = name
            and result = 'damaged'
            UNION
            SELECT name, 0 as isDam, class
            from Ships
            where name not in (Select ship 
                                from Outcomes
                                where result = 'damaged'))
    where sc = c.class)
group by country
;


.headers off

SELECT "16---------";
.headers on

INSERT INTO Ships
SELECT c.class, c.class, min(launched)
from Classes c, Ships s
where c.class not in (SELECT class from Ships 
                    WHERE name == class)
and s.class = c.class
group by c.class;

-- UNION
-- This one adds the classes that were 
--not present in the Ships table to begin with, any 
-- ships added in the previous step will not be readded
--since they would have name == class.
INSERT INTO Ships
SELECT c.class, c.class, min(launched)
from Classes c, Ships s
where c.class not in (SELECT class from Ships 
                    WHERE name == class)
group by c.class;


--THIS BOTTOM ONE underneath this comment
-- WORKS BUT IS LESS EFFICIENT THEN HAVING TWO
--seperate queries as shown above.

-- INSERT INTO Ships
-- SELECT c.class, c.class, 
--     CASE
--     WHEN c.class not in (SELECT class from Ships)
--         THEN min(launched)
--     ELSE (SELECT min(launched) from Classes c, Ships s
--         where c.class not in (SELECT class from Ships 
--                            WHERE name == class) 
--         and  c.class = s.class
--         group by c.class)
--     END
-- from Classes c, Ships s
-- where c.class not in (SELECT class from Ships 
--                     WHERE name == class)
-- group by c.class;



SELECT * from Ships;


.headers off

SELECT "17---------";
.headers on

SELECT cn as country, sum(one) as '1911-1920', sum(two) as '1921-1930',
         sum(three) as '1931-1940', sum(four) as '1941-1950'
from  
    (SELECT s.name as sn, country as cn,
        CASE
        WHEN launched BETWEEN 1911 and 1920
        THEN 1
        ELSE
            0
        END as 'one',
        
        CASE
        WHEN launched BETWEEN 1921 and 1930
        THEN 1
        ELSE
            0
        END as 'two',
        
        CASE
        WHEN launched BETWEEN 1931 and 1940
        THEN 1
        ELSE
            0
        END as 'three',
        
        CASE
        WHEN launched BETWEEN 1941 and 1950
        THEN 1
        ELSE
            0
        END as 'four'
    from Ships s , Classes c
    where c.class = s.class
    group by country, s.name)
group by cn
;

-- Gives the launchdate of all the ships
-- SELECT s.name as sn, country,
--         CASE
--         WHEN launched BETWEEN 1911 and 1920
--         THEN 1
--         ELSE
--             0
--         END as '1911-1920',
--             CASE
--         WHEN launched BETWEEN 1921 and 1930
--         THEN 1
--         ELSE
--             0
--         END as '1921-1930',
--         CASE
--         WHEN launched BETWEEN 1931 and 1940
--         THEN 1
--         ELSE
--             0
--         END as '1931-1940',
--         CASE
--         WHEN launched BETWEEN 1941 and 1950
--         THEN 1
--         ELSE
--             0
--         END as '1941-1950'
-- from Ships s , Classes c
-- where c.class = s.class
-- group by country, s.name;



-- SELECT country,
--  count(name) as '1911-1920',
--  count(name) as '1921-1930',
--  count(name) as '1931-1940',
--  count(name) as '1941-1950'
-- from Ships s, Classes c
-- where s.class = c.class
-- group by country ;


-- count(name)
-- from Ships s, Classes c
-- where s.class = c.class
-- group by country ;

-- SELECT country 
-- CASE
-- WHEN launched BETWEEN 1911 and 1920
-- THEN 

-- WHEN launched BETWEEN 1921 and 1930
-- THEN

-- WHEN launched BETWEEN 1931 and 1940
-- THEN

-- ELSE

-- END

-- SELECT country, 

--  as '1911-1920',
--  as '1921-1930',
--  as '1931-1940',
--  as '1941-1950'

-- SELECT country,
--     (SELECT count(name)
--     from Ships s, Classes c
--     where s.class = c.class
--     and launched BETWEEN 1911 and 1920
--     group by country) 
--     as '1911-1920',

--     (SELECT count(name)
--     from Ships s, Classes c
--     where s.class = c.class
--     and launched BETWEEN 1921 and 1930
--     group by country)  as '1921-1930',

--  count(name) as '1931-1940',
--  count(name) as '1941-1950'
-- from Ships s, Classes c
-- where s.class = c.class
-- group by country ;

-- SELECT country, sum(tree), 
--         sum('1921-1930'),sum('1931-1940'), sum('1941-1950')
-- from 
--     (SELECT class as sc,
--         CASE
--         WHEN launched BETWEEN 1911 and 1920
--         THEN 1
--         ELSE
--             0
--         END as 'tree',
--             CASE
--         WHEN launched BETWEEN 1921 and 1930
--         THEN 1
--         ELSE
--             0
--         END as '1921-1930',
--         CASE
--         WHEN launched BETWEEN 1931 and 1940
--         THEN 1
--         ELSE
--             0
--         END as '1931-1940',
--         CASE
--         WHEN launched BETWEEN 1941 and 1950
--         THEN 1
--         ELSE
--             0
--         END as '1941-1950'
--     from Ships
--     group by class), Classes c
-- where sc = c.class
-- group by country

;
.headers off

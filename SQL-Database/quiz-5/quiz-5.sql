PRAGMA foreign_keys = on;

SELECT "1----------";
.headers on
CREATE TABLE Classes (
    class           char(25) UNIQUE,
    type            char(2),
    country         char(25) NOT NULL,
    numGuns         decimal(2,0),
    bore            decimal(2,0),
    displacement    decimal(5,0),
    CHECK (type in ('bb','bc'))
);
CREATE TABLE Ships(
    name        char(25) UNIQUE,
    class       char(25) REFERENCES Classes(class) ON DELETE SET NULL ON UPDATE SET NULL,
    launched    decimal(4,0)
);
CREATE TABLE Battles(
    name char(25) UNIQUE,
    date date
);

CREATE TABLE Outcomes(
    ship    char(25) REFERENCES Ships(name) ON DELETE CASCADE ON UPDATE CASCADE,
    battle  char(25) REFERENCES Battles(name) ON DELETE CASCADE ON UPDATE CASCADE,
    result  char(10),
    CHECK (result in ('ok','sunk', 'damaged'))
);

-- .schema

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

select * from Classes;
select * from Ships;
select * from Battles;
select * from Outcomes;
.headers off

SELECT "3----------";
.headers on

DELETE from Classes
where displacement < 30000
OR numGuns < 8;


select * from Classes;
select * from Ships;
.headers off

SELECT "4----------";
.headers on

Delete from Battles 
where name = 'Guadalcanal'
;

select * from Battles;
select * from Outcomes;
.headers off

SELECT "5----------";
.headers on

UPDATE Battles
SET name = 'Strait of Surigao'
where name = 'Surigao Strait'

;

select * from Battles;
select * from Outcomes;
.headers off

SELECT "6----------";
.headers on

DELETE FROM Ships 
where class is null
;

select * from Ships;
select * from Outcomes;
.headers off

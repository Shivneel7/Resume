import sqlite3
from sqlite3 import Error


def openConnection(_dbFile):
    print("++++++++++++++++++++++++++++++++++")
    print("Open database: ", _dbFile)

    conn = None
    try:
        conn = sqlite3.connect(_dbFile)
        print("success")
    except Error as e:
        print(e)

    print("++++++++++++++++++++++++++++++++++")

    return conn


def closeConnection(_conn, _dbFile):
    print("++++++++++++++++++++++++++++++++++")
    print("Close database: ", _dbFile)

    try:
        _conn.close()
        print("success")
    except Error as e:
        print(e)

    print("++++++++++++++++++++++++++++++++++")


def createPriceRange(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Create PriceRange")
    try:
        sql = "DROP VIEW if exists PriceRange;"
        _conn.execute(sql)
        _conn.commit()

        sql = """
                Create View PriceRange(maker, type, minPrice, maxPrice) as 
                Select maker, type, min(price) as minPrice, max(price) as maxPrice
                from PC, Product
                where Product.model = PC.model
                and type = 'pc'
                group by maker
                UNION
                Select maker, type, min(price) as minPrice, max(price) as maxPrice
                from Laptop, Product
                where Product.model = Laptop.model
                and type = 'laptop'
                group by maker
                UNION
                Select maker, Product.type, min(price) as minPrice, max(price) as maxPrice
                from Printer, Product
                where Product.model = Printer.model
                and Product.type = 'printer'
                group by maker
                """
        _conn.execute(sql)

        _conn.commit()
        print("success")
    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def printPriceRange(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Print PriceRange")
    try:
        cur = _conn.cursor()
        q = """SELECT * from PriceRange
                order by maker ASC, type ASC;
            """
        cur.execute(q)
        rows = cur.fetchall()

        l = '{:<10} {:<20} {:>20} {:>20}'.format("maker", "product", "minPrice", "maxPrice")
        print(l)
        for row in rows:
            print('{:<10} {:<20} {:>20} {:>20}'.format(row[0], row[1], row[2], row[3]))
        print("success")

    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def insertPC(_conn, _maker, _model, _speed, _ram, _hd, _price):
    print("++++++++++++++++++++++++++++++++++")
    try:
        sql = """INSERT into PC (model,speed,ram,hd,price) 
                values (?,?,?,?,?)
            """
        args = [_model, _speed, _ram, _hd, _price]
        _conn.execute(sql,args)
        _conn.commit()

        sql = """INSERT into Product (maker,model,type) 
                values (?,?,?)
            """
        args = [_maker, _model, 'pc']
        _conn.execute(sql,args)
        _conn.commit()

        l = 'Insert PC ({}, {}, {}, {}, {}, {})'.format(_maker, _model, _speed, _ram, _hd, _price)
        print(l)
    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def updatePrinter(_conn, _model, _price):
    print("++++++++++++++++++++++++++++++++++")
    try:
        sql = """Update Printer 
                set price = ? 
                where model = ?            
            """
        args = [_price, _model]
        _conn.execute(sql,args)
        _conn.commit()

        l = 'Update Printer ({}, {})'.format(_model, _price)
        print(l)

    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def deleteLaptop(_conn, _model):
    print("++++++++++++++++++++++++++++++++++")

    try:
        sql = """DELETE FROM Laptop
                where model = ?
            """
        args = [_model]
        _conn.execute(sql,args)
        _conn.commit()

        sql = """DELETE FROM Product
                where model = ?
                """
        args = [_model]
        _conn.execute(sql,args)
        _conn.commit()


        l = 'Delete Laptop ({})'.format(_model)
        print(l)
    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def main():
    database = r"data.sqlite"

    # create a database connection
    conn = openConnection(database)
    with conn:
        createPriceRange(conn)
        printPriceRange(conn)

        file = open('input.in', 'r')
        lines = file.readlines()
        for line in lines:
            print(line.strip())

            tok = line.strip().split(' ')
            if tok[0] == 'I':
                insertPC(conn, tok[2], tok[3], tok[4], tok[5], tok[6], tok[7])
            elif tok[0] == 'U':
                updatePrinter(conn, tok[2], tok[3])
            elif tok[0] == 'D':
                deleteLaptop(conn, tok[2])

            printPriceRange(conn)

        file.close()

    closeConnection(conn, database)


if __name__ == '__main__':
    main()

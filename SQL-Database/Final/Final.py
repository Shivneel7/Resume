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


def T1(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("T1")

    cur = _conn.cursor()

    q = """
        Select count(distinct l1.l_orderkey)
        from lineitem l1, lineitem l2
        where l2.l_orderkey = l1.l_orderkey
        and l1.l_partkey != l2.l_partkey
        and l1.l_suppkey = l2.l_suppkey
            """
    cur.execute(q)
    rows = cur.fetchall()

    with open("output/1.out", "w") as file:
        header = "{:>10}\n".format("orders")
        file.write(header)
        for temp in rows:
            file.writelines(f'{temp[0]:10}\n')
        
    print("++++++++++++++++++++++++++++++++++")


def T2(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("T2")

    cur = _conn.cursor()

    # q = """
    #     Select n_name, count(distinct l2.l_orderkey)
    #     from lineitem l1, lineitem l2, supplier, nation
    #     where l1.l_suppkey = l2.l_suppkey
    #     and l1.l_suppkey = s_suppkey
    #     and l1.l_orderkey = l2.l_orderkey
    #     and s_nationkey = n_nationkey
    #     and l1.l_linenumber != l2.l_linenumber
    #     group by n_name
    #     """

    q="""SELECT n_name, count(numOrders) from (
            Select n_name, l_suppkey, l_orderkey, count(*) as numOrders
            from lineitem, supplier, nation
            where l_suppkey = s_suppkey
            and s_nationkey = n_nationkey
            group by l_orderkey, l_suppkey
            having count(*) >= 2)
        group by n_name"""

    cur.execute(q)
    rows = cur.fetchall()
    with open("output/2.out", "w") as file:
        header = "{:<40} {:>10}\n".format("nation", "orders")
        file.write(header)
        for temp in rows:
            file.writelines(f'{temp[0]:40}{temp[1]:10}\n')
    print("++++++++++++++++++++++++++++++++++")


def T3(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("T3")

    with open("input/3.in", "r") as file:
        k = int(file.readline().strip())

    cur = _conn.cursor()
    q="""SELECT n_name, count(numOrders) from (
            Select n_name, l_suppkey, l_orderkey, count(*) as numOrders
            from lineitem, supplier, nation
            where l_suppkey = s_suppkey
            and s_nationkey = n_nationkey
            group by l_orderkey, l_suppkey
            having count(*) = ?)
        group by n_name"""

    # # USED THIS QUERY TO TEST IF MY SOLUTION WAS CORRECT
    # q = """        
    # Select n_name, count(distinct l1.l_orderkey)
    #     from lineitem l1, lineitem l2, lineitem l3, supplier, nation
    #     where l1.l_suppkey = l2.l_suppkey
    #     and l3.l_suppkey = l2.l_suppkey
    #     and l1.l_suppkey = s_suppkey
    #     and l1.l_orderkey = l2.l_orderkey
    #     and l3.l_orderkey = l2.l_orderkey
    #     and s_nationkey = n_nationkey
    #     and l1.l_linenumber != l2.l_linenumber 
    #     and l3.l_linenumber != l2.l_linenumber 
    #     and l3.l_linenumber != l1.l_linenumber 
    #     group by n_name
    #     """

    cur.execute(q, [k])
    # cur.execute(q)
    rows = cur.fetchall()



    with open("output/3.out", "w") as file:
        header = "{:<40} {:>10}\n".format("nation", "orders")
        file.write(header)
        for temp in rows:
            file.writelines(f'{temp[0]:40}{temp[1]:10}\n')


    print("++++++++++++++++++++++++++++++++++")


def T4(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("T4")

    sql = """CREATE TABLE RegionItems (
        supReg char(25,0) not null,
        custReg char(25,0) not null,
        itemNo decimal(6,0) not null
        )
        """
    _conn.execute(sql)

    _conn.commit()

    sql = """
        Insert into RegionItems
        Select r1.r_name, r2.r_name, count(*)
        from region r1, supplier, region r2, customer, nation n1, nation n2, lineitem, orders
        where r1.r_regionkey = n1.n_regionkey
        and r2.r_regionkey = n2.n_regionkey
        and n1.n_nationkey = s_nationkey
        and n2.n_nationkey = c_nationkey
        and o_custkey = c_custkey
        and o_orderkey = l_orderkey
        and l_suppkey = s_suppkey
        group by r1.r_name, r2.r_name
        """
    _conn.execute(sql)

    _conn.commit()


    cur = _conn.cursor()

    q = """
        Select *
        from RegionItems
        """
    cur.execute(q)
    rows = cur.fetchall()


    with open("output/4.out", "w") as file:
        header = "{:<40} {:<40} {:>10}\n".format("supReg", "custReg", "items")
        file.write(header)  
        for temp in rows:
            file.writelines(f'{temp[0]:40}{temp[1]:40}{temp[2]:10}\n')
    print("++++++++++++++++++++++++++++++++++")


def T5(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("T5")

    with open("input/5.in", "r") as file:
        nation = file.readline().strip()

    ##################This trigger works but it is less efficient
    # sql = """
    #     Create Trigger updateRegionItems_afterDelete after delete on lineitem
    #     for each row
    #     Begin 

    #     update RegionItems
    #     set itemNo = itemNo - 1
    #     where supReg in (
    #         select r_name from supplier, region, nation
    #         where r_regionkey = n_regionkey
    #         and s_suppkey = OLD.l_suppkey
    #         and s_nationkey = n_nationkey

    #     )
    #     and custReg in (
    #         Select r_name from customer, orders, nation, region
    #         where OLD.l_orderkey = o_orderkey
    #         and c_custkey = o_custkey
    #         and c_nationkey = n_nationkey
    #         and r_regionkey = n_regionkey
    #     );

    #     END;
    #     """
    # _conn.execute(sql)
    # _conn.commit()


    sql = """
        Delete from lineitem
        where l_suppkey in (
            select s_suppkey from nation, supplier
            where s_nationkey = n_nationkey
            and n_name = ?
        )
        """

    _conn.execute(sql, [nation])
    _conn.commit()


    # if 0: #Used this to test my trigger since this should give the correct output
    #     #dropping the table then remaking it actually runs faster, then updating the view
    #     sql = """
    #         DROP TABLE RegionItems

    #         """
    #     _conn.execute(sql)
    #     _conn.commit()

    #     sql = """CREATE TABLE RegionItems (
    #         supReg char(25,0) not null,
    #         custReg char(25,0) not null,
    #         itemNo decimal(6,0) not null
    #         )
    #         """
    #     _conn.execute(sql)

    #     # _conn.commit()

    #     sql = """
    #         Insert into RegionItems
    #         Select r1.r_name, r2.r_name, count(*)
    #         from region r1, supplier, region r2, customer, nation n1, nation n2, lineitem, orders
    #         where r1.r_regionkey = n1.n_regionkey
    #         and r2.r_regionkey = n2.n_regionkey
    #         and n1.n_nationkey = s_nationkey
    #         and n2.n_nationkey = c_nationkey
    #         and o_custkey = c_custkey
    #         and o_orderkey = l_orderkey
    #         and l_suppkey = s_suppkey
    #         group by r1.r_name, r2.r_name 
    #         """
    #     _conn.execute(sql)

    #     _conn.commit()


    if 1: #Used this to test my trigger since this should give the correct output
        #dropping the table then remaking it actually runs faster, then updating the view

        sql = """
            Delete From RegionItems
            """
        _conn.execute(sql)
        _conn.commit()

        sql = """
            Insert into RegionItems
            Select r1.r_name, r2.r_name, count(*)
            from region r1, supplier, region r2, customer, nation n1, nation n2, lineitem, orders
            where r1.r_regionkey = n1.n_regionkey
            and r2.r_regionkey = n2.n_regionkey
            and n1.n_nationkey = s_nationkey
            and n2.n_nationkey = c_nationkey
            and o_custkey = c_custkey
            and o_orderkey = l_orderkey
            and l_suppkey = s_suppkey
            group by r1.r_name, r2.r_name 
            """
        _conn.execute(sql)
        _conn.commit()
    cur = _conn.cursor()
    q = """
        Select *
        from RegionItems
        """

    cur.execute(q)
    rows = cur.fetchall()
    
    with open("output/5.out", "w") as file:
        header = "{:<40} {:<40} {:>10}\n".format("supReg", "custReg", "items")
        file.write(header)
        for temp in rows:
            file.writelines(f'{temp[0]:40}{temp[1]:40}{temp[2]:10}\n')

    print("++++++++++++++++++++++++++++++++++")


def T6(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("T6")

    with open("input/6.in", "r") as file:
        oldNation = file.readline().strip()
        newNation = file.readline().strip()

    
    # sql = """
    #     Create Trigger updateRegionItems_afterUpdate after update on customer

    #     Begin 

    #     Delete from RegionItems;

    #     Insert into RegionItems
    #     Select r1.r_name, r2.r_name, count(*)
    #     from region r1, supplier, region r2, customer, nation n1, nation n2, lineitem, orders
    #     where r1.r_regionkey = n1.n_regionkey
    #     and r2.r_regionkey = n2.n_regionkey
    #     and n1.n_nationkey = s_nationkey
    #     and n2.n_nationkey = c_nationkey
    #     and o_custkey = c_custkey
    #     and o_orderkey = l_orderkey
    #     and l_suppkey = s_suppkey
    #     group by r1.r_name, r2.r_name;

    #     END;
    #     """
    # _conn.execute(sql)
    # _conn.commit()


    sql = """
        update customer
        set c_nationkey = (select n_nationkey from nation where n_name = ?)
        where c_nationkey = (select n_nationkey from nation where n_name = ?)
        """
    _conn.execute(sql, [newNation, oldNation])
    _conn.commit()


    # if 0: #Used this to test my trigger since this should give the correct output
    #     #dropping the table then remaking it actually runs faster, then updating the view
    #     sql = """
    #         DROP TABLE RegionItems

    #         """
    #     _conn.execute(sql)
    #     _conn.commit()

    #     sql = """CREATE TABLE RegionItems (
    #         supReg char(25,0) not null,
    #         custReg char(25,0) not null,
    #         itemNo decimal(6,0) not null
    #         )
    #         """
    #     _conn.execute(sql)

    #     # _conn.commit()

    #     sql = """
    #         Insert into RegionItems
    #         Select r1.r_name, r2.r_name, count(*)
    #         from region r1, supplier, region r2, customer, nation n1, nation n2, lineitem, orders
    #         where r1.r_regionkey = n1.n_regionkey
    #         and r2.r_regionkey = n2.n_regionkey
    #         and n1.n_nationkey = s_nationkey
    #         and n2.n_nationkey = c_nationkey
    #         and o_custkey = c_custkey
    #         and o_orderkey = l_orderkey
    #         and l_suppkey = s_suppkey
    #         group by r1.r_name, r2.r_name 
    #         """
    #     _conn.execute(sql)

    #     _conn.commit()

    if 1: #Used this to test my trigger since this should give the correct output
        #dropping the table then remaking it actually runs faster, then updating the view

        sql = """
            Delete From RegionItems
            """
        _conn.execute(sql)
        _conn.commit()

        sql = """
            Insert into RegionItems
            Select r1.r_name, r2.r_name, count(*)
            from region r1, supplier, region r2, customer, nation n1, nation n2, lineitem, orders
            where r1.r_regionkey = n1.n_regionkey
            and r2.r_regionkey = n2.n_regionkey
            and n1.n_nationkey = s_nationkey
            and n2.n_nationkey = c_nationkey
            and o_custkey = c_custkey
            and o_orderkey = l_orderkey
            and l_suppkey = s_suppkey
            group by r1.r_name, r2.r_name 
            """
        _conn.execute(sql)
        _conn.commit()


    cur = _conn.cursor()
    q = """
        Select *
        from RegionItems
        """

    cur.execute(q)
    rows = cur.fetchall()
    

    with open("output/6.out", "w") as file:
        header = "{:<40} {:<40} {:>10}\n".format("supReg", "custReg", "items")
        file.write(header)
        for temp in rows:
            file.writelines(f'{temp[0]:40}{temp[1]:40}{temp[2]:10}\n')

    print("++++++++++++++++++++++++++++++++++")


def main():
    database = r"tpch.sqlite"

    # create a database connection
    conn = openConnection(database)
    with conn:
        T1(conn)
        T2(conn)
        T3(conn)
        T4(conn)
        T5(conn)
        T6(conn)

    closeConnection(conn, database)


if __name__ == '__main__':
    main()

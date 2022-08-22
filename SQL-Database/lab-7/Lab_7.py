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


def createTable(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Create table")
    try:
        sql = """CREATE TABLE warehouse (
                w_warehousekey decimal(9,0) not null,
                w_name char(100) not null,
                w_capacity decimal(6,0) not null,
                w_suppkey decimal(9,0) not null,
                w_nationkey decimal(2,0) not null
                )
                """
        _conn.execute(sql)

        _conn.commit()
        print("success")
    except Error as e:
        _conn.rollback()
        print(e)

    print("++++++++++++++++++++++++++++++++++")


def dropTable(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Drop tables")
    try:
        sql = "DROP TABLE warehouse"
        _conn.execute(sql)

        _conn.commit()
        print("success")
    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def populateTable(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Populate table")
    try:



        cur = _conn.cursor()
        q = """SELECT s_name from supplier
            """
        cur.execute(q)
        suppliers = cur.fetchall()
        rows2 = suppliers + suppliers
        rows2.sort()
        # print(type(rows2))



        # test = []
        # i = 1
        # for s in suppliers:
        #     q = """
        #     SELECT s_name, n_name, count(l_suppkey) as numItems from supplier, lineitem, nation, customer, orders
        #         where n_nationkey = c_nationkey
        #         and l_suppkey = s_suppkey
        #         and l_orderkey = o_orderkey
        #         and c_custkey = o_custkey
        #         and s_suppkey = %d
        #         group by n_name
        #         order by  numItems Desc
        #         limit 2
        #         """ % i
        #     cur.execute(q)
        #     i+=1
        #     test.append(cur.fetchall())

        # print(test)
        # q = """
        # SELECT s_name, n_name, count(l_suppkey) as numItems from supplier, lineitem, nation, customer, orders
        #     where n_nationkey = c_nationkey
        #     and l_suppkey = s_suppkey
        #     and l_orderkey = o_orderkey
        #     and c_custkey = o_custkey
        #     group by n_name, s_name
        #     order by s_name ASC, numItems Desc
        #     """
        # cur.execute(q)

        q = """
          SELECT s_name, n_name, count(l_suppkey) as numItems,
                 s_suppkey, n_nationkey
            from supplier, lineitem, nation, customer, orders, part
            where n_nationkey = c_nationkey
            and l_suppkey = s_suppkey
            and l_orderkey = o_orderkey
            and c_custkey = o_custkey
            and p_partkey = l_partkey
            group by n_name, s_name
            order by s_name ASC, numItems Desc
            """
        cur.execute(q)
        results = cur.fetchall()
        # print(results)

        q = """
        Select max(size) from
            (SELECT s_name, n_name, sum(p_size) as size
            from supplier, lineitem, nation, customer, orders, part
            where n_nationkey = c_nationkey
            and l_suppkey = s_suppkey
            and l_orderkey = o_orderkey
            and c_custkey = o_custkey
            and p_partkey = l_partkey
            group by n_name, s_name
            order by s_name ASC) group by s_name
            """
        cur.execute(q)
        results2 = cur.fetchall()
        # print(results2)
        # f = open('test.out', 'w')

        insertion = []
        new_supplier = 0
        s = 0
        i=1
        for res in results:
            if new_supplier == 1:
                #update current supplier
                s = res[0]
                new_supplier=0

            else:
                #get the location when supplier changes
                if s == res[0]:
                    #seen this supplier more than twice, so we skip
                    continue
                else:
                    # we see a new supplier, so 
                    new_supplier=1
            
            temp = (i, res[0] + '___' + res[1],2*results2[int((i-1)/2)][0],res[3],res[4])
            # temp = (i, res[0] + '___' + res[1],2*(results2[int(res[3]-1)]),res[3],res[4])
            
            i+=1
            # f.writelines(f'{temp[0]:3} {temp[1]:40} {temp[2]:6} {temp[3]:5} {temp[4]:5}\n')
            insertion.append(temp)

        # for res in results:
        #     if new_supplier == 0:
        #         if s == res[0]:
        #             #seen this supplier more than twice, so we skip
        #             continue
        #         else:
        #             # we see a new supplier, so 
        #             new_supplier=1
        #     else :
        #         #update current supplier
        #         s = res[0]
        #         new_supplier=0
    
        #     temp = (res[0] + '___' + res[1],8,res[2],1,1)
        #     f.writelines(str(temp)+'\n')
        #     insertion.append(temp)
        # print(cur.fetchall())

        # Testing the print function
        # f = open('test.out', 'w')

        # for supplier, nation, items in results:
        #     # print(f'{supplier:20} {nation:20} {items}')
        #     f.writelines(f'{supplier:20} {nation:20} {items}\n')

        # print(rows2)


        sql = """INSERT INTO warehouse 
                Values(?,?,?,?,?)
                """

        _conn.executemany(sql,insertion)
        

        # sql = """INSERT INTO warehouse 
                
                    
        #         """
                
        # _conn.execute(sql)


        _conn.commit()
        print('success')
    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def Q1(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Q1")
    try:
        cur = _conn.cursor()

        q = """Select * from warehouse
                order by w_warehousekey
                """
        cur.execute(q)
        rows = cur.fetchall()

        # print(str(rows))
        f = open('output/1.out', 'w')
        f.writelines("{:10} {:40} {:10} {:10} {:10}\n".format("wId","wName","wCap","sId","nId"))
        for temp in rows:
            f.writelines(f'{temp[0]:10} {temp[1]:40} {temp[2]:10} {temp[3]:10} {temp[4]:10}\n')
        
        _conn.commit()
        print('success')
    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def Q2(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Q2")
    try:
        cur = _conn.cursor()

        q = """Select n_name, count(w_nationkey) as num, sum(w_capacity) as tot from warehouse, nation
                where w_nationkey = n_nationkey
                group by n_nationkey
                order by num DESC, tot DESC
                """
        cur.execute(q)
        rows = cur.fetchall()

        # print(str(rows))
        f = open('output/2.out', 'w')
        f.writelines("{:40} {:10} {:10}\n".format("nation", "numW", "totCap"))
        for temp in rows:
            f.writelines(f'{temp[0]:40} {temp[1]:10} {temp[2]:10}\n')
        
        _conn.commit()
        print("success")
    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def Q3(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Q3")
    try:
        cur = _conn.cursor()

        inputFile = open('input/3.in', 'r')
        nation = [inputFile.readline().strip()]
        # print(nation)
        q = """Select s_name, n2.n_name, w_name
                from warehouse, nation n1, supplier, nation n2
                where s_suppkey = w_suppkey
                and w_nationkey = n1.n_nationkey
                and n1.n_name = ?
                and n2.n_nationkey = s_nationkey
                order by s_name
                """
        cur.execute(q, nation)
        rows = cur.fetchall()

        # print(str(rows))
        f = open('output/3.out', 'w')
        f.writelines("{:20} {:20} {:40}\n".format("supplier", "nation", "warehouse"))
        for temp in rows:
            f.writelines(f'{temp[0]:20} {temp[1]:20} {temp[2]:40}\n')
        
        _conn.commit()
        print("success")
    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def Q4(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Q4")
    try:
        cur = _conn.cursor()

        inputFile = open('input/4.in', 'r')
        inp = []
        i = 0
        for line in inputFile:
            if i > 1:
                break
            inp.append(line.strip())
            i += 1 
        print(inp)
        q = """Select w_name, w_capacity
                from warehouse, region, nation
                where w_nationkey = n_nationkey
                and r_regionkey = n_regionkey
                and r_name = ?
                and w_capacity > ?
                order by w_capacity DEsc
                """
        cur.execute(q, inp)
        rows = cur.fetchall()

        # print(str(rows))
        f = open('output/4.out', 'w')
        f.writelines("{:40} {:>10}\n".format("warehouse", "capacity"))
        for temp in rows:
            f.writelines(f'{temp[0]:40} {temp[1]:10} \n')
        
        _conn.commit()
        print("success")
    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def Q5(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Q5")
    try:
        cur = _conn.cursor()
 
        inputFile = open('input/5.in', 'r')
        nation = [inputFile.readline().strip()]
        # print(nation)

        q = """Select r_name, max(total) from
                (Select r_name, sum(w_capacity) as total
                from region, warehouse, nation n1, supplier, nation n2
                where w_suppkey = s_suppkey
                and s_nationkey = n2.n_nationkey
                and n2.n_name = ?
                and w_nationkey = n1.n_nationkey
                and r_regionkey = n1.n_regionkey
                group by r_name
                UNION Select r_name, 0 from region)
                group by r_name

                """
        cur.execute(q, nation)
        rows = cur.fetchall()

        # print(str(rows))
        f = open('output/5.out', 'w')
        f.writelines("{:20} {:>20}\n".format("region", "capacity"))
        for temp in rows:
            f.writelines(f'{temp[0]:20} {temp[1]:20}\n')
        
        _conn.commit()
        print("success")
    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def main():
    database = r"tpch.sqlite"

    # create a database connection
    conn = openConnection(database)
    with conn:
        dropTable(conn)
        createTable(conn)
        populateTable(conn)

        Q1(conn)
        Q2(conn)
        Q3(conn)
        Q4(conn)
        Q5(conn)

    closeConnection(conn, database)


if __name__ == '__main__':
    main()

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


def create_View1(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Create V1")
    try:
        sql = """DROP VIEW IF EXISTS V1"""
        _conn.execute(sql)
        _conn.commit()

        sql = """Create View V1(c_custkey, c_name, c_address, c_phone, c_acctbal, c_mktsegment, c_comment, c_nation,c_region) as 
                    SELECT c_custkey, c_name, c_address, c_phone, c_acctbal, c_mktsegment, c_comment, n_name, r_name
                    from customer, nation, region
                    where c_nationkey = n_nationkey
                    and r_regionkey = n_regionkey
                """
        _conn.execute(sql)
        _conn.commit()
        print("success")

    except Error as e:
        _conn.rollback()
        print(e)

    print("++++++++++++++++++++++++++++++++++")


def create_View2(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Create V2")
    try:
        sql = """DROP VIEW IF EXISTS V2"""
        _conn.execute(sql)
        _conn.commit()

        sql = """Create View V2(s_suppkey, s_name, s_address, s_phone, s_acctbal, s_comment, s_nation, s_region) as 
                    SELECT s_suppkey, s_name, s_address, s_phone, s_acctbal, s_comment, n_name, r_name
                    from supplier, nation, region
                    where s_nationkey = n_nationkey
                    and r_regionkey = n_regionkey
                """
        _conn.execute(sql)
        _conn.commit()
        print("success")

    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def create_View5(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Create V5")
    try:
        sql = """DROP VIEW IF EXISTS V5"""
        _conn.execute(sql)
        _conn.commit()

        sql = """Create View V5(o_orderkey, o_custkey, o_orderstatus, o_totalprice, o_orderyear, o_orderpriority, o_clerk, o_shippriority, o_comment) as 
                    SELECT o_orderkey, o_custkey, o_orderstatus, o_totalprice, CAST(SUBSTR(o_orderdate, 1, 4) AS integer) as o_orderyear, o_orderpriority, o_clerk, o_shippriority, o_comment
                    from orders
                """
        _conn.execute(sql)
        _conn.commit()
        print("success")

    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def create_View10(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Create V10")
    try:
        sql = """DROP VIEW IF EXISTS V10"""
        _conn.execute(sql)
        _conn.commit()

        sql = """Create View V10(p_type, min_discount, max_discount) as 
                    SELECT p_type, min(l_discount) as min_discount, max(l_discount) as max_discount
                    from part, lineitem
                    where l_partkey = p_partkey
                    group by p_type
                """
        _conn.execute(sql)
        _conn.commit()
        print("success")

    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def create_View151(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Create V151")
    try:
        sql = """DROP VIEW IF EXISTS V151"""
        _conn.execute(sql)
        _conn.commit()

        sql = """Create View V151(c_custkey, c_name, c_nationkey, c_acctbal) as 
            SELECT c_custkey, c_name, n_nationkey, c_acctbal
            from customer, nation
            where c_nationkey = n_nationkey
            and c_acctbal > 0
            """
        _conn.execute(sql)
        _conn.commit()
        print("success")

    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def create_View152(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Create V152")
    try:
        sql = """DROP VIEW IF EXISTS V152"""
        _conn.execute(sql)
        _conn.commit()

        sql = """Create View V152(s_suppkey, s_name, s_nationkey, s_acctbal) as 
            SELECT s_suppkey, s_name, n_nationkey, s_acctbal
            from supplier, nation
            where s_nationkey = n_nationkey
            and s_acctbal < 0
            """
        _conn.execute(sql)
        _conn.commit()
        print("success")

    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def Q1(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Q1")
    try:
        cur = _conn.cursor()
        sql = """
            Select c_name, round(sum(o_totalprice), 2)
            from V1, orders
            where o_custkey = c_custkey
            and c_nation = 'FRANCE'
            and o_orderdate like '1995-__-__'
            group by c_name
            """
        cur.execute(sql)
        rows = cur.fetchall()

        f = open('output/1.out', 'w')
        for row in rows:
            f.writelines(f'{row[0]}|{row[1]}\n')
        print("success")

    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def Q2(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Q2")
    try:
        cur = _conn.cursor()
        sql = """
            select s_region, count(*)
            from V2
            group by s_region;
            """
        cur.execute(sql)
        rows = cur.fetchall()

        f = open('output/2.out', 'w')
        for row in rows:
            f.writelines(f'{row[0]}|{row[1]}\n')
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
        sql = """
            select c_nation, count(*)
            from orders, V1
            where c_custkey = o_custkey
                and c_region='AMERICA'
                group by c_nation
            """
        cur.execute(sql)
        rows = cur.fetchall()

        f = open('output/3.out', 'w')
        for row in rows:
            f.writelines(f'{row[0]}|{row[1]}\n')
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
        sql = """
            select s_name, count(ps_partkey)
            from partsupp, V2, part
            where p_partkey = ps_partkey
                and ps_suppkey = s_suppkey
                and s_nation = 'CANADA'
                and p_size < 20
            group by s_name;
            """
        cur.execute(sql)
        rows = cur.fetchall()

        f = open('output/4.out', 'w')
        for row in rows:
            f.writelines(f'{row[0]}|{row[1]}\n')
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
        sql = """
            select c_name, count(*)
            from V1, V5
            where o_custkey = c_custkey
                and c_nation = 'GERMANY'
                and o_orderyear = 1993
            group by c_name;
            """
        cur.execute(sql)
        rows = cur.fetchall()

        f = open('output/5.out', 'w')
        for row in rows:
            f.writelines(f'{row[0]}|{row[1]}\n')
        print("success")
    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def Q6(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Q6")
    try:
        cur = _conn.cursor()
        sql = """
            select s_name, o_orderpriority, count(distinct ps_partkey)
            from partsupp, V5, lineitem, V2
            where l_orderkey = o_orderkey
                and l_partkey = ps_partkey
                and l_suppkey = ps_suppkey
                and ps_suppkey = s_suppkey
                and s_nation = 'CANADA'
            group by s_name, o_orderpriority;
            """
        cur.execute(sql)
        rows = cur.fetchall()

        f = open('output/6.out', 'w')
        for row in rows:
            f.writelines(f'{row[0]}|{row[1]}|{row[2]}\n')
        print("success")

    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def Q7(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Q7")
    try:
        cur = _conn.cursor()
        sql = """
            select c_nation, o_orderstatus, count(*)
            from V1, V5
            where o_custkey = c_custkey
                and c_region='AMERICA'
            group by c_nation, o_orderstatus
            """
        cur.execute(sql)
        rows = cur.fetchall()

        f = open('output/7.out', 'w')
        for row in rows:
            f.writelines(f'{row[0]}|{row[1]}|{row[2]}\n')
        print("success")

    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def Q8(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Q8")
    try:
        cur = _conn.cursor()
        sql = """
            select s_nation, count(distinct l_orderkey) as co
            from V2, V5, lineitem
            where o_orderkey = l_orderkey
                and l_suppkey = s_suppkey
                and o_orderstatus = 'F'
                and o_orderyear = 1995
            group by s_nation
            having co > 50
            """
        cur.execute(sql)
        rows = cur.fetchall()

        f = open('output/8.out', 'w')
        for row in rows:
            f.writelines(f'{row[0]}|{row[1]}\n')
        print("success")

    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def Q9(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Q9")
    try:
        cur = _conn.cursor()
        sql = """
            select count(distinct o_clerk)
            from V5, V2, lineitem
            where o_orderkey = l_orderkey
                and l_suppkey = s_suppkey
                and s_nation = 'UNITED STATES';
            """
        cur.execute(sql)
        rows = cur.fetchall()

        f = open('output/9.out', 'w')
        for row in rows:
            f.writelines(f'{row[0]}\n')
        print("success")

    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def Q10(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Q10")
    try:
        cur = _conn.cursor()
        sql = """
            select p_type, min_discount, max_discount
            from V10
            where p_type like '%ECONOMY%'
                and p_type like '%COPPER%'
            """
        cur.execute(sql)
        rows = cur.fetchall()

        f = open('output/10.out', 'w')
        for row in rows:
            f.writelines(f'{row[0]}|{row[1]}|{row[2]}\n')
        print("success")

    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def Q11(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Q11")
    try:
        cur = _conn.cursor()
        sql = """
            select s_region, s_name, s_acctbal
            from V2 one
            where s_acctbal = (select max(s_acctbal) from V2 two
                                where one.s_region = two.s_region)
            group by s_region
            """
        cur.execute(sql)
        rows = cur.fetchall()

        f = open('output/11.out', 'w')
        for row in rows:
            f.writelines(f'{row[0]}|{row[1]}|{row[2]}\n')
        print("success")

    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def Q12(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Q12")
    try:
        cur = _conn.cursor()
        sql = """
            select s_nation, max(s_acctbal) as mb
            from V2
            group by s_nation
            having mb > 9000;

            """
        cur.execute(sql)
        rows = cur.fetchall()

        f = open('output/12.out', 'w')
        for row in rows:
            f.writelines(f'{row[0]}|{row[1]}\n')
        print("success")

    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def Q13(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Q13")
    try:
        cur = _conn.cursor()
        sql = """
            select count(*)
            from orders, lineitem, V1, V2
            where o_orderkey = l_orderkey
                and o_custkey = c_custkey
                and l_suppkey = s_suppkey
                and s_region = 'AFRICA'
                and c_nation = 'UNITED STATES';
            """
        cur.execute(sql)
        rows = cur.fetchall()

        f = open('output/13.out', 'w')
        for row in rows:
            f.writelines(f'{row[0]}\n')
        print("success")

    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def Q14(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Q14")
    try:
        cur = _conn.cursor()
        sql = """
            select s_region as suppRegion, c_region as custRegion, max(o_totalprice)
            from lineitem, orders, V1, V2
            where l_suppkey = s_suppkey
                and l_orderkey = o_orderkey
                and o_custkey = c_custkey
            group by s_region, c_region;
            
            """
        cur.execute(sql)
        rows = cur.fetchall()

        f = open('output/14.out', 'w')
        for row in rows:
            f.writelines(f'{row[0]}|{row[1]}|{row[2]}\n')
        print("success")

    except Error as e:
        _conn.rollback()
        print(e)
    print("++++++++++++++++++++++++++++++++++")


def Q15(_conn):
    print("++++++++++++++++++++++++++++++++++")
    print("Q15")
    try:
        cur = _conn.cursor()
        sql = """
            select count(distinct l_orderkey)
            from lineitem, V151, orders, V152
            where l_suppkey = s_suppkey
                and l_orderkey = o_orderkey
                and o_custkey = c_custkey
            """
        cur.execute(sql)
        rows = cur.fetchall()

        f = open('output/15.out', 'w')
        for row in rows:
            f.writelines(f'{row[0]}\n')
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
        create_View1(conn)
        Q1(conn)

        create_View2(conn)
        Q2(conn)

        Q3(conn)
        Q4(conn)

        create_View5(conn)
        Q5(conn)

        Q6(conn)
        Q7(conn)
        Q8(conn)
        Q9(conn)

        create_View10(conn)
        Q10(conn)

        Q11(conn)
        Q12(conn)
        Q13(conn)
        Q14(conn)

        create_View151(conn)
        create_View152(conn)
        Q15(conn)

    closeConnection(conn, database)


if __name__ == '__main__':
    main()

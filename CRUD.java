package WestTwoTest03;

//由于在CRUD中，注册驱动和获取链接、释放资源这三个步骤的代码复用率较高，所以将这三个步骤封装在Tools类里面

import java.sql.*;

public class CRUD {
    private CRUD() {
    }

    static Connection conn = null;
    static PreparedStatement ps = null;//这里使用PreparedStatement（预编译的数据库操作对象）可以防止SQL注入现象！！！
    static ResultSet rs = null;

    /**
     * 创建班级
     *
     * @param cno
     * @param cname
     */
    public static void newClass(int cno, String cname) {
        try {
            //1、注册驱动  2、获取连接
            conn = Tools.getConnection();
            //3、获取预编译的数据库操作对象
            String sql = "insert into class(cno,cname) values(?,?)";//SQL语句的框架，其中，一个?代表一个占位符，一个?将来接收一个值 注意：占位符不能用''括起来
            ps = conn.prepareStatement(sql);//sql这条语句到这儿就编译了
            //给占位符?传值（第1个问号下标是1，第2个下标是2，JDBC中所有下标从1开始）
            ps.setInt(1, cno);
            ps.setString(2, cname);
            //4、执行sql
            ps.executeUpdate();
            System.out.println("成功创建班级：" + cname);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            Tools.close(conn, ps, rs);
        }
    }

    /**
     * 录入学生
     *
     * @param sno
     * @param sname
     * @param sclass
     */
    public static void newStudent(String sno, String sname, int sclass) {
        try {
            //1、注册驱动  2、获取连接
            conn = Tools.getConnection();
            //3、获取预编译的数据库操作对象
            String sql = "insert into student(sno,sname,sclass) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            //给占位符传值
            ps.setString(1, sno);
            ps.setString(2, sname);
            ps.setInt(3, sclass);
            //4、执行sql
            int num = ps.executeUpdate();
            System.out.println("成功录入" + num + "名学生\n姓名：" + sname + "\n学号：" + sno + "\n" + "录入班级：" + sclass + "\n");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            Tools.close(conn, ps, rs);
        }
    }

    /**
     * 退学
     *
     * @param sno
     */
    public static void deleteStudent(String sno) {
        try {
            //1、注册驱动  2、获取连接
            conn = Tools.getConnection();
            //3、获取预编译的数据库操作对象
            String sql = "delete from student where sno =?";
            ps = conn.prepareStatement(sql);
            //给占位符传值
            ps.setString(1, sno);
            //4、执行sql
            ps.executeUpdate();
            System.out.println("成功劝退学号为：" + sno + " 的学生");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            Tools.close(conn, ps, rs);
        }
    }

    /**
     * 这个方法简单地实现转班级的功能（更改学生的班级）
     *
     * @param sno
     * @param sclass
     */
    public static void updateStudentClass(String sno, int sclass) {
        try {
            //1、注册驱动  2、获取连接
            conn = Tools.getConnection();
            //3、获取预编译的数据库操作对象
            String sql = "update student set sclass = ? where sno = ?";
            ps = conn.prepareStatement(sql);
            //给占位符传值
            ps.setInt(1, sclass);
            ps.setString(2, sno);
            //4、执行sql
            ps.executeUpdate();
            System.out.println("成功将学号为：" + sno + " 的学生转入编号为" + sclass + "的班级");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            Tools.close(conn, ps, rs);
        }
    }

    /**
     * 查看班级编号和对应的班级名
     */
    public static void selectClass() {
        try {
            //1、注册驱动  2、获取连接
            conn = Tools.getConnection();
            //3、获取预编译的数据库操作对象
            String sql = "select cno,cname from class";
            ps = conn.prepareStatement(sql);
            //执行sql
            rs = ps.executeQuery();
            System.out.println("班级编号        班级");
            while (rs.next()) {
                System.out.print(rs.getString("cno") + "              ");
                System.out.print(rs.getString("cname") + "  ");
                System.out.println();
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            Tools.close(conn, ps, rs);
        }
    }

    /**
     * 连接查询
     */
    public static void select() {
        try {
            //1、注册驱动  2、获取连接
            conn = Tools.getConnection();
            //3、获取预编译的数据库操作对象
            String sql = "select s.sno,s.sname,c.cname from student s join class c on s.sclass = c.cno order by sclass";//连接查询
            ps = conn.prepareStatement(sql);
            //执行sql
            rs = ps.executeQuery();
            System.out.println("学号       姓名              班级");
            while (rs.next()) {
                System.out.print(rs.getString("s.sno") + "  ");
                System.out.print(rs.getString("s.sname") + "  ");
                System.out.print(rs.getString("c.cname") + "  ");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            Tools.close(conn, ps, rs);
        }
    }

    /**
     * 事务的简单体现
     * 随便写一个（就写一个学生想转班级，转完后不满意又想转回去了）
     */
    public static void statement(String sno, int sclassBefore, int sclassAfter) {
        try {
            //1、注册驱动  2、获取连接
            conn = Tools.getConnection();

            //开启事务，将自动提交机制改为手动提交
            conn.setAutoCommit(false);

            //3、获取预编译的数据库操作对象
            String sql = "update student set sclass = ? where sno = ?";
            ps = conn.prepareStatement(sql);

            //给占位符传值
            //一、想转去sclassAfter班
            ps.setInt(1, sclassAfter);
            ps.setString(2, sno);
            int count = ps.executeUpdate();//执行sql

            //模拟一个异常
            //String s = null;
            //s.toString();
            //System.out.println("你tm在搞什么骚操作？给我老老实实回去你原来班级！！！");

            //二、想回原来的sclassBefore班
            ps.setInt(1, sclassBefore);
            ps.setString(2, sno);
            count += ps.executeUpdate();//执行sql

            System.out.println(count == 2 ? "转出又转回成功" : "一顿操作失败");

            //程序如果能run到这里，说明以上程序没有问题，事务结束，手动提交数据
            conn.commit();//提交事务

        } catch (SQLException e) {
            //回滚事务
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            //释放资源
            Tools.close(conn, ps, rs);
        }
    }

}

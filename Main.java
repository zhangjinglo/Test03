package WestTwoTest03;
/**
 * 这个主方法主要用于对CRUD以及事务管理的测试
 * 将Create、Retrieve、Update、Delete写成方法封装起来以便于操作
 * 目前想到的功能：
 *      1、新增班级和学生（增）  ✔
 *      2、退学（删）（参数用要退学学生的学号即可）✔
 *      3、实现数据更新（改）（转班级）✔
 *      4、学生信息查询（查）✔
 *      5、事务管理：随便想了一个：一个学生想转班级，转完后不满意又想转回去了
 */
public class Main {
    public static void main(String[] args) {
        //new出一些班级和学生对象
        var class1 = new ClassTest(1, "Information Security class01");
        var class2 = new ClassTest(2, "Computer Science01");
        var student1 = new StudentTest("031903132", "Freddie Mercury", 1);
        var student2 = new StudentTest("031902127", "Michael Jackson", 2);

        //注意：student表中的sclass是依赖于class表中的cno的外键
        //所以要先创建出班级才能录入在该班级的学生
        //同理，要先删除在该班级的所有学生才能删除该班级

        //测试：

        //增
        //CRUD.newClass(class1.getCno(), class1.getCname());
        //CRUD.newClass(class2.getCno(), class2.getCname());
        //CRUD.newStudent(student1.getSno(), student1.getSname(), student1.getSclass());
        //CRUD.newStudent(student2.getSno(), student2.getSname(), student2.getSclass());

        //删
        //CRUD.deleteStudent("031903132");
        //CRUD.deleteStudent("031902127");

        //改
        //CRUD.selectClass();//帮助看班级名和编号
        //CRUD.updateStudentClass("031903132", 2);//将学号为xxx的学生转入y班级


        //查
        //CRUD.selectClass();//这个查询用于查看班级编号所对应的班级名
        //CRUD.select();//这个查询用于查看所有学生的学号，姓名和所在班级名

        //事务测试
        //CRUD.selectClass();//帮助看班级名和编号
        //CRUD.statement("031903132",1,2);
    }
}

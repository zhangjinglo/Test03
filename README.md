# Test03
这里是对我这次作业的介绍：

首先就是对MySQL数据库的使用，我在里面写了两个SQL文件，里面包含了我创建表格的语句和一些查询语句

然后就是用JDBC链接数据库，在连接的步骤中，我把代码复用率较高的几个步骤（注册驱动，获取数据库连接对象，释放资源）进行了封装

封装在Tools类当中，并将Tools类的构造方法设为Private，因为工具类只是用来调用里面的方法的，并不需要new出工具类的对象

接下来是CRUD类里的方法，我对一些功能（新增班级和学生，退学，数据更新等）也进行了封装，需要使用时只需用类名调用配合上相对应的参数即可

另外我还写了两个类：学生类和班级类，运用面向对象的思想，并将这两个类里面的成员变量都设置为Private，且只有相对应的get方法无set方法（在new对象时即对成员变量赋值且不再更改

在main方法里我new出了一些对象，并调用对应的方法将对象的数据增添到数据库中，并对CRUD类中所有封装的方法进行测试。

至于Navicat软件的使用，我简单地截了个图说明我有用过这个软件。。。

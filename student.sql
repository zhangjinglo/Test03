create table student(
sno char(9) primary key not null,
sname varchar(255) not null,
sclass int(2),
foreign key(sclass) references class(cno)
);

desc student;

insert into student(sno,sname,sclass)values('031903132','Freddie Mercury',1);

insert into student(sno,sname,sclass)values('031902127','Miclael Jackson',2);

select * from student;

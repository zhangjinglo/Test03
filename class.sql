create table class(
cno int(2) primary key auto_increment,
cname varchar(50) not null
);

desc class;

insert into class(cno,cname)values(1,'Information Security class01');

insert into class(cno,cname)values(2,'Computer Science01');

select * from class;
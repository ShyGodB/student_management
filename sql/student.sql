create table student (
	id int primary key auto_increment comment '主键',

    departmentId int not null default 0 comment '院系编号，对应department表的id字段',
    specialtyId int not null default 0 comment '专业编号，对应specialty表的id字段',
    classId int not null default 0 comment '班级编号，对应class表的id字段',
    realName varchar(32) not null default '' comment '真实姓名',
    gender varchar(16) not null default '' comment '性别: 男、女',
    birthday varchar(32) not null default '' comment '生日、出生日期',
    nationality varchar(32) not null default '' comment '国籍，默认为: 中国',
    nation varchar(32) not null default '' comment '民族，默认为: 汉',
    education varchar(32) not null default '' comment '学历，默认为: 高中',
    homeAddress varchar(128) not null default '' comment '家庭住址',
    phone varchar(16) not null default '' comment '手机号',
    homeTelephone varchar(16) not null default '' comment '家庭电话',
    schoolTelephone varchar(16) not null default '' comment '学校电话',

	isOff boolean not null default false comment '是否被禁用，默认为false',
	isDel boolean not null default false comment '是否被删除，默认为false',
	createTime Datetime comment '创建时间',
	updateTime Datetime comment '最近更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment = '学生信息表';

create index student_departmentId on student (departmentId);
create index student_specialtyId on student (specialtyId);
create index student_classId on student (classId);
create index student_realName on student (realName);
create index student_gender on student (gender);
create index student_phone on student (phone);
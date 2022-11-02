create table class (
	id int primary key auto_increment comment '主键',

    departmentId int not null default 0 comment '院系编号，对应department表的id字段',
    specialtyId int not null default 0 comment '专业编号，对应specialty表的id字段',
    cname varchar(64) not null default '' comment '班级名称，如: 140803班',
    remark varchar(512) not null default '' comment '班级介绍',

	isOff boolean not null default false comment '是否被禁用，默认为false',
	isDel boolean not null default false comment '是否被删除，默认为false',
	createTime Datetime comment '创建时间',
	updateTime Datetime comment '最近更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment = '班级表';

create index class_departmentId on class (departmentId);
create index class_specialtyId on class (specialtyId);
create index class_cname on class (cname);
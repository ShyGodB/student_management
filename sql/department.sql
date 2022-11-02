create table department (
	id int primary key auto_increment comment '主键',

    cname varchar(64) not null default '' comment '院系名称，如: 计算机学院',
    remark varchar(512) not null default '' comment '院系介绍',

	isOff boolean not null default false comment '是否被禁用，默认为false',
	isDel boolean not null default false comment '是否被删除，默认为false',
	createTime Datetime comment '创建时间',
	updateTime Datetime comment '最近更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment = '院系表，存储现有的所有院系';

create index department_cname on department (cname);
create table course (
	id int primary key auto_increment comment '主键，递增',

    cname varchar(64) not null default '' comment '课程名称',
    remark varchar(512) not null default '' comment '课程介绍',

	isOff boolean not null default false comment '是否被禁用，默认为false',
	isDel boolean not null default false comment '是否被删除，默认为false',
	createTime Datetime comment '创建时间',
	updateTime Datetime comment '最近更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment = '课程信息表';

create index course_cname on course (cname);
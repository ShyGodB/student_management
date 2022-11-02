create table adminuser (
	id int primary key auto_increment comment '编号，自增',

	username varchar(32) not null default '' comment '用户名、账号',
	cpassword varchar(16) not null default '' comment '登录密码',
	clevel int not null default 0 comment '权限等级',

	isOff boolean not null default false comment '是否被禁用，默认为false',
	isDel boolean not null default false comment '是否被删除，默认为false',
	createTime Datetime comment '创建时间',
	updateTime Datetime comment '最近更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment = '管理员信息表';

create index adminUser_username on adminuser (username);
create index adminUser_cpassword on adminuser (cpassword);
create index adminUser_clevel on adminuser (clevel);
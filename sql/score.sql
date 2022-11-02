create table score (
	id int primary key auto_increment comment '主键',

    studentId int not null default 0 comment '学生编号',
    courseId int not null default 0 comment '课程编号',
    score float(5, 2) not null default 0 comment '成绩',
    scoreLevel varchar(8) not null default '' comment '评级: 优秀、良好、及格、不及格',
    remark varchar(128) not null default '' comment '备注',

	isOff boolean not null default false comment '是否禁用，默认为false',
	isDel boolean not null default false comment '是否删除，默认为false',
	createTime Datetime comment '创建时间',
	updateTime Datetime comment '最近更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment = '成绩表';

create index score_studentId on score (studentId);
create index score_subjectId on score (courseId);
create index score_score on score (score);
create index score_scoreLevel on score (scoreLevel);
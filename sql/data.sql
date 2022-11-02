INSERT INTO adminuser ( username, cpassword, clevel )
VALUES
	( 1, '', 1 ),
	('admin01', 'admin123', 2),
	('admin02', 'admin123', 2),
	('admin03', 'admin123', 2),
	('admin04', 'admin123', 2),
	('admin05', 'admin123', 2),
	('admin06', 'admin123', 2),
	('admin07', 'admin123', 2),
	('admin08', 'admin123', 2),
	('admin09', 'admin123', 2),
	('admin10', 'admin123', 2);

insert into department (cname) values ('计算机学院'), ('文学院'), ('法学院'), ('外国语学院'), ('土木学院');

INSERT INTO specialty ( departmentId, cname )
VALUES
	( 1, '软件工程' ),
	( 1, '信息安全' ),
	( 2, '2222222' ),
	( 2, '2222222' ),
	( 2, '2222222' ),
	( 3, '3333333' ),
	( 3, '3333333' ),
	( 3, '3333333' ),
	( 4, '4444444' ),
	( 4, '4444444' ),
	( 4, '4444444' ),
	( 5, '5555555' ),
	( 5, '5555555' );


INSERT INTO class ( departmentId, specialtyId, cname )
VALUES
	( 1, 1, '140803' ),
	( 1, 1, '140804' ),
	( 2, 2, '156783' ),
	( 2, 2, '2222222' ),
	( 2, 2, '230291' ),
	( 3, 3, '3333333' ),
	( 3, 10, '3333333' ),
	( 3, 3, '3333333' ),
	( 4, 4, '4444444' ),
	( 4, 4, '4444444' ),
	( 4, 8, '4444444' ),
	( 5, 4, '5555555' ),
	( 5, 5, '5555555' );


insert into course (cname) values ('语文'), ('数学'), ('英语'), ('物理');

INSERT INTO student (
	departmentId,
	specialtyId,
	classId,
	realName,
	gender,
	birthday,
	nationality,
	nation,
	education,
	homeAddress,
	phone) 
VALUES 
	(1, 1, 1, '张三', '男', '1998-02-01', '中国', '汉', '高中', '广东省江门市', '19999999999'),
	(1, 1, 1, '李四', '男', '1998-02-01', '中国', '汉', '高中', '广东省江门市', '13789087654'),
	(1, 1, 1, '王武', '男', '1998-02-01', '中国', '汉', '高中', '广东省江门市', '19999999999'),
	(2, 1, 1, '小明', '男', '1998-02-01', '中国', '汉', '高中', '广东省江门市', '16666666666'),
	(2, 1, 1, '小刚', '男', '1998-02-01', '中国', '汉', '高中', '广东省江门市', '18888888888'),
	(2, 1, 1, '小强', '男', '1998-05-01', '中国', '汉', '高中', '广东省江门市', '19999999999'),
	(3, 2, 2, '小花', '女', '1998-02-01', '中国', '汉', '高中', '广东省江门市', '18888888888'),
	(3, 2, 2, '费兵', '男', '1998-02-01', '中国', '汉', '高中', '广东省江门市', '18888888888'),
	(3, 2, 2, '章飞', '男', '1998-04-01', '中国', '汉', '高中', '广东省江门市', '19999999999'),
	(4, 2, 2, '马明', '男', '1998-04-01', '中国', '汉', '高中', '广东省江门市', '19999999999'),
	(4, 2, 2, '铁无涯', '男', '1998-02-01', '中国', '汉', '高中', '广东省江门市', '19999999999'),
	(5, 2, 2, '赵云', '男', '1998-02-01', '中国', '汉', '高中', '广东省江门市', '18888888888'),
	(5, 2, 2, '刘关张', '男', '2000-05-01', '中国', '汉', '高中', '广东省江门市', '17777777777');

insert into score (studentId, courseId, score) values 
(1, 1, 87),
(1, 2, 77),
(1, 3, 90),
(1, 4, 67),
(2, 1, 45),
(2, 2, 98),
(2, 3, 56),
(2, 4, 89),
(3, 1, 99),
(3, 2, 88),
(3, 3, 79),
(3, 4, 90);
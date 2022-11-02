package stuManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

import Class.ClassAddFrame;
import Class.ClassManageFrame;
import Cource.CourceAddFrame;
import Cource.CourceManageFrame;
import Depart.DepartAddFrame;
import Depart.DepartManager;
import Specialty.SpecialtyAddFrame;
import Specialty.SpecialtyManageFrame;
import Score.ScoreAddFrame;
import Score.ScoreManager;
import Student.StudentInfoAddFrame;
import Student.StudentManager;
import User.UserAddFrame;
import User.UserDelete;
import User.UserPasswordFrame;

// 主界面模块，有对学生、班级、院系、课程、成绩、用户的录入与管理的菜单

public class MainFrame extends JFrame {
	JPanel contentPane;
	String adminId;
	String adminLevel;
	String adminUsername;
	
	URL url = getClass().getResource("/img/bg.jpg");
	ImageIcon mainBg = new ImageIcon(url);
	
	JLabel welcomeTitle = new JLabel();
	JLabel welcomeTitle2 = new JLabel();
	JLabel jLabelHelp1 = new JLabel();
	JLabel jLabelHelp2 = new JLabel();
	JLabel jLabelHelp3 = new JLabel();

	JMenuBar jMenuBar = new JMenuBar();

	// 学生管理
	JMenu stuMainMenu = new JMenu();
	JMenuItem stuAddMenu = new JMenuItem();
	JMenuItem stuManageMenu = new JMenuItem();

	// 院系管理
	JMenu departmentMainMenu = new JMenu();
	JMenuItem departmentAddMenu = new JMenuItem();
	JMenuItem departmentManagerMenu = new JMenuItem();

	// 专业管理
	JMenu specialtyMainMenu = new JMenu();
	JMenuItem specialtyAddMenu = new JMenuItem();
	JMenuItem specialtyManagerMenu = new JMenuItem();

	// 班级管理
	JMenu classMainMenu = new JMenu();
	JMenuItem classAddMenu = new JMenuItem();
	JMenuItem classManagerMenu = new JMenuItem();

	// 课程管理
	JMenu courseMainMenu = new JMenu();
	JMenuItem courseAddMenu = new JMenuItem();
	JMenuItem courseManagerMenu = new JMenuItem();

	// 成绩管理
	JMenu scoreMainMenu = new JMenu();
	JMenuItem scoreAddMenu = new JMenuItem();
	JMenuItem scoreManagerMenu = new JMenuItem();

	// 管理员管理
	JMenu userMainMenu = new JMenu();
	JMenuItem userAddMenu = new JMenuItem();
	JMenuItem userChangePasswordMenu = new JMenuItem();
	JMenuItem userDeleteMenu = new JMenuItem();

	// 关于
	JMenu aboutMainMenu = new JMenu();
	JMenuItem aboutMenuItem = new JMenuItem();

	// 退出
	JMenu quitMainMenu = new JMenu();
	JMenuItem quitMenu = new JMenuItem();
	JMenuItem rebootMenu = new JMenuItem();

	JPanel paneBox = new JPanel();
	JLabel mainBgBox = new JLabel();
	BorderLayout borderLayout = new BorderLayout();
	JOptionPane loginFeedback = new JOptionPane();

	public MainFrame(String adminId, String adminLevel, String adminUsername) {
		this.adminId = adminId;
		this.adminLevel = adminLevel;
		this.adminUsername = adminUsername;
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			init();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void init() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		this.setJMenuBar(jMenuBar);
		setSize(new Dimension(911, 698));
		setTitle("欢迎登录学生管理系统");

		welcomeTitle.setText("欢 迎 使 用 学 生 信 息 管 理 系 统");
		welcomeTitle.setBounds(new Rectangle(180, 130, 600, 33));
		welcomeTitle.setFont(new java.awt.Font("Dialog", Font.BOLD, 30));
		welcomeTitle.setForeground(Color.lightGray);

		welcomeTitle2.setFont(new java.awt.Font("Dialog", Font.BOLD, 30));
		welcomeTitle2.setText("欢 迎 使 用 学 生 信 息 管 理 系 统");
		welcomeTitle2.setBounds(new Rectangle(181, 131, 608, 33));

		jLabelHelp1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		jLabelHelp1.setText("您可以对：");
		jLabelHelp1.setBounds(new Rectangle(500, 340, 400, 30));
		
		jLabelHelp2.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		jLabelHelp2.setText("学生、班级、院系、课程、成绩");
		jLabelHelp2.setBounds(new Rectangle(500, 370, 400, 30));
		
		jLabelHelp3.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		jLabelHelp3.setText("进行录入、查询、修改、删除的操作");
		jLabelHelp3.setBounds(new Rectangle(500, 400, 400, 30));
		
		stuMainMenu.setText("学生管理");
		stuAddMenu.setText("  录  入");
		stuAddMenu.addActionListener(new AddStudent(this));
		stuManageMenu.setText("  管  理");
		stuManageMenu.addActionListener(new ManageStudent(this));

		departmentMainMenu.setText("院系管理");
		departmentAddMenu.setText("  录  入");
		departmentAddMenu.addActionListener(new AddDepartment(this));
		departmentManagerMenu.setText("  管  理");
		departmentManagerMenu.addActionListener(new ManageDepartment(this));

		specialtyMainMenu.setText("专业管理");
		specialtyAddMenu.setText("  录  入");
		specialtyAddMenu.addActionListener(new AddSpecialty(this));
		specialtyManagerMenu.setText("  管  理");
		specialtyManagerMenu.addActionListener(new ManageSpecialty(this));

		classMainMenu.setText("班级管理");
		classAddMenu.setText("  录  入");
		classAddMenu.addActionListener(new AddClass(this));
		classManagerMenu.setText("  管  理");
		classManagerMenu.addActionListener(new ManageClass(this));

		courseMainMenu.setText("课程管理");
		courseAddMenu.setText("  录  入");
		courseAddMenu.addActionListener(new AddCourse(this));
		courseManagerMenu.setText("  管  理");
		courseManagerMenu.addActionListener(new ManageCourse(this));

		scoreMainMenu.setText("成绩管理");
		scoreAddMenu.setText("  录  入");
		scoreAddMenu.addActionListener(new AddScore(this));
		scoreManagerMenu.setText("  管  理");
		scoreManagerMenu.addActionListener(new ManageScore(this));

		userMainMenu.setText("用户管理");
		userAddMenu.setText("添加用户");
		userAddMenu.addActionListener(new AddUser(this));
		userChangePasswordMenu.setText("修改密码");
		userChangePasswordMenu.addActionListener(new ChangeUserPassword(this));
		userDeleteMenu.setEnabled(true);
		userDeleteMenu.setText("删除用户");
		userDeleteMenu.addActionListener(new DeleteUser(this));

		aboutMainMenu.setText("关于");
		aboutMenuItem.setText("  关  于");
		aboutMenuItem.addActionListener(new About(this));

		quitMainMenu.setText("退出");
		quitMenu.setText("  退  出");
		quitMenu.addActionListener(new Quit(this));
		rebootMenu.setText("重新启动");
		rebootMenu.addActionListener(new Reboot(this));

		paneBox.setBounds(new Rectangle(-6, 0, 900, 700));
		paneBox.setLayout(borderLayout);
		mainBgBox.setIcon(mainBg);

		contentPane.add(welcomeTitle2);
		contentPane.add(welcomeTitle);
		contentPane.add(jLabelHelp1);
		contentPane.add(jLabelHelp2);
		contentPane.add(jLabelHelp3);
		contentPane.add(paneBox);

		paneBox.add(mainBgBox, java.awt.BorderLayout.NORTH);

		jMenuBar.add(stuMainMenu);
		jMenuBar.add(departmentMainMenu);
		jMenuBar.add(specialtyMainMenu);
		jMenuBar.add(classMainMenu);
		jMenuBar.add(courseMainMenu);
		jMenuBar.add(scoreMainMenu);
		jMenuBar.add(userMainMenu);
		jMenuBar.add(aboutMainMenu);
		jMenuBar.add(quitMainMenu);

		stuMainMenu.add(stuAddMenu);
		stuMainMenu.add(stuManageMenu);

		departmentMainMenu.add(departmentAddMenu);
		departmentMainMenu.add(departmentManagerMenu);

		specialtyMainMenu.add(specialtyAddMenu);
		specialtyMainMenu.add(specialtyManagerMenu);

		classMainMenu.add(classAddMenu);
		classMainMenu.add(classManagerMenu);

		courseMainMenu.add(courseAddMenu);
		courseMainMenu.add(courseManagerMenu);

		scoreMainMenu.add(scoreAddMenu);
		scoreMainMenu.add(scoreManagerMenu);

		userMainMenu.add(userAddMenu);
		userMainMenu.add(userDeleteMenu);
		userMainMenu.add(userChangePasswordMenu);

		aboutMainMenu.add(aboutMenuItem);

		quitMainMenu.add(quitMenu);
		quitMainMenu.add(rebootMenu);

		// 用户等级判断
		// 1为管理员
		// if (level.equals("1")) {
		// 	// 不隐藏任何功能
		// }
		// // 2为普通用户
		// else if (level.equals("2")) {
		// 	// 隐藏部分功能
		// 	this.userAddMenu.setVisible(false);
		// 	this.userDeleteMenu.setVisible(false);
		// 	this.stuAddMenu.setVisible(false);
		// 	this.classAddMenu.setVisible(false);
		// 	this.departmentAddMenu.setVisible(false);
		// 	this.courseAddMenu.setVisible(false);
		// 	this.scoreAddMenu.setVisible(false);
		// }
	}

	// 关于信息
	public void about(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "学生信息管理系统\n版本号：1.0\n作   者：qbfang\n邮   箱：qbfang@163.com", "关于", JOptionPane.INFORMATION_MESSAGE);
	}

	// 退出
	public void quit(ActionEvent e) {
		System.exit(0);
	}

	// 重启
	public void reboot(ActionEvent e) {
		this.dispose();
		LoginFrame login = new LoginFrame();
		login.setLocation(400, 200);
		login.setSize(504, 344);
		login.setVisible(true);
		login.setResizable(false);
		login.validate();
	}

	// 学生录入模块
	public void addStudent(ActionEvent e) {
		StudentInfoAddFrame newFrame = new StudentInfoAddFrame();
		newFrame.setLocation(400, 200);
		newFrame.setSize(1000, 600);
		newFrame.setVisible(true);
		newFrame.setResizable(true);
		newFrame.validate();
	}

	// 学生管理模块
	public void manageStudent(ActionEvent e) {
		StudentManager newFrame = new StudentManager();
		// newFrame.setLayout(new BorderLayout(30, 10));
		newFrame.setLocation(250, 60);
		newFrame.setSize(800, 620);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// 班级录入模块
	public void addClass(ActionEvent e) {
		ClassAddFrame newFrame = new ClassAddFrame();
		newFrame.setLocation(400, 200);
		newFrame.setSize(465, 410);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// 班级管理模块
	public void manageClass(ActionEvent e) {
		ClassManageFrame newFrame = new ClassManageFrame();
		newFrame.setLocation(400, 100);
		newFrame.setSize(530, 560);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// 院系录入模块
	public void addDepartment(ActionEvent e) {
		DepartAddFrame newFrame = new DepartAddFrame();
		newFrame.setLocation(400, 200);
		newFrame.setSize(465, 310);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// 院系管理模块
	public void manageDepartment(ActionEvent e) {
		DepartManager newFrame = new DepartManager();
		newFrame.setLocation(400, 100);
		newFrame.setSize(530, 560);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// 专业录入模块
	public void addSpecialty(ActionEvent e) {
		SpecialtyAddFrame newFrame = new SpecialtyAddFrame();
		newFrame.setLocation(400, 200);
		newFrame.setSize(465, 360);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// 专业管理模块
	public void manageSpecialty(ActionEvent e) {
		SpecialtyManageFrame newFrame = new SpecialtyManageFrame();
		newFrame.setLocation(400, 100);
		newFrame.setSize(530, 560);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// 课程录入模块
	public void addCourse(ActionEvent e) {
		CourceAddFrame newFrame = new CourceAddFrame();
		newFrame.setLocation(400, 200);
		newFrame.setSize(482, 320);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// 课程管理模块
	public void manageCourse(ActionEvent e) {
		CourceManageFrame newFrame = new CourceManageFrame();
		newFrame.setLocation(400, 100);
		newFrame.setSize(530, 560);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// 成绩录入模块
	public void addScore(ActionEvent e) {
		ScoreAddFrame newFrame = new ScoreAddFrame();
		newFrame.setLocation(400, 200);
		newFrame.setSize(420, 450);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();

	}

	// 成绩管理模块
	public void manageScore(ActionEvent e) {
		ScoreManager newFrame = new ScoreManager();
		newFrame.setLocation(400, 100);
		newFrame.setSize(550, 560);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// 添加用户模块
	public void addUser(ActionEvent e) {
		UserAddFrame newFrame = new UserAddFrame();
		newFrame.setLocation(400, 200);
		newFrame.setSize(469, 315);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// 删除用户模块
	public void deleteUser(ActionEvent e) {
		UserDelete newFrame = new UserDelete();
		newFrame.setLocation(400, 200);
		newFrame.setSize(444, 280);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// 修改密码模块
	public void changeUserPassword(ActionEvent e) {
		UserPasswordFrame newFrame = new UserPasswordFrame(adminId, adminLevel, adminUsername);
		newFrame.setLocation(400, 200);
		newFrame.setSize(444, 340);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}
}

class ManageCourse implements ActionListener {
	private MainFrame adaptee;

	ManageCourse(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.manageCourse(e);
	}
}

class ManageScore implements ActionListener {
	private MainFrame adaptee;

	ManageScore(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.manageScore(e);
	}
}

class ManageStudent implements ActionListener {
	private MainFrame adaptee;

	ManageStudent(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.manageStudent(e);
	}
}

class ManageClass implements ActionListener {
	private MainFrame adaptee;

	ManageClass(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.manageClass(e);
	}
}

class AddClass implements ActionListener {
	private MainFrame adaptee;

	AddClass(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.addClass(e);
	}
}

class ManageDepartment implements ActionListener {
	private MainFrame adaptee;

	ManageDepartment(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.manageDepartment(e);
	}
}

class AddDepartment implements ActionListener {
	private MainFrame adaptee;

	AddDepartment(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.addDepartment(e);
	}
}

class ManageSpecialty implements ActionListener {
	private MainFrame adaptee;

	ManageSpecialty(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.manageSpecialty(e);
	}
}

class AddSpecialty implements ActionListener {
	private MainFrame adaptee;

	AddSpecialty(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.addSpecialty(e);
	}
}

class AddCourse implements ActionListener {
	private MainFrame adaptee;

	AddCourse(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.addCourse(e);
	}
}

class AddScore implements ActionListener {
	private MainFrame adaptee;

	AddScore(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.addScore(e);
	}
}

class AddUser implements ActionListener {
	private MainFrame adaptee;

	AddUser(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.addUser(e);
	}
}

class ChangeUserPassword implements ActionListener {
	private MainFrame adaptee;

	ChangeUserPassword(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.changeUserPassword(e);
	}
}

class DeleteUser implements ActionListener {
	private MainFrame adaptee;

	DeleteUser(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.deleteUser(e);
	}
}

class About implements ActionListener {
	private MainFrame adaptee;

	About(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.about(e);
	}
}

class Quit implements ActionListener {
	private MainFrame adaptee;

	Quit(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.quit(e);
	}
}

class AddStudent implements ActionListener {
	private MainFrame adaptee;

	AddStudent(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.addStudent(e);
	}
}

class Reboot implements ActionListener {
	private MainFrame adaptee;

	Reboot(MainFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.reboot(e);
	}
}

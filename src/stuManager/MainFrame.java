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

// ������ģ�飬�ж�ѧ�����༶��Ժϵ���γ̡��ɼ����û���¼�������Ĳ˵�

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

	// ѧ������
	JMenu stuMainMenu = new JMenu();
	JMenuItem stuAddMenu = new JMenuItem();
	JMenuItem stuManageMenu = new JMenuItem();

	// Ժϵ����
	JMenu departmentMainMenu = new JMenu();
	JMenuItem departmentAddMenu = new JMenuItem();
	JMenuItem departmentManagerMenu = new JMenuItem();

	// רҵ����
	JMenu specialtyMainMenu = new JMenu();
	JMenuItem specialtyAddMenu = new JMenuItem();
	JMenuItem specialtyManagerMenu = new JMenuItem();

	// �༶����
	JMenu classMainMenu = new JMenu();
	JMenuItem classAddMenu = new JMenuItem();
	JMenuItem classManagerMenu = new JMenuItem();

	// �γ̹���
	JMenu courseMainMenu = new JMenu();
	JMenuItem courseAddMenu = new JMenuItem();
	JMenuItem courseManagerMenu = new JMenuItem();

	// �ɼ�����
	JMenu scoreMainMenu = new JMenu();
	JMenuItem scoreAddMenu = new JMenuItem();
	JMenuItem scoreManagerMenu = new JMenuItem();

	// ����Ա����
	JMenu userMainMenu = new JMenu();
	JMenuItem userAddMenu = new JMenuItem();
	JMenuItem userChangePasswordMenu = new JMenuItem();
	JMenuItem userDeleteMenu = new JMenuItem();

	// ����
	JMenu aboutMainMenu = new JMenu();
	JMenuItem aboutMenuItem = new JMenuItem();

	// �˳�
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
		setTitle("��ӭ��¼ѧ������ϵͳ");

		welcomeTitle.setText("�� ӭ ʹ �� ѧ �� �� Ϣ �� �� ϵ ͳ");
		welcomeTitle.setBounds(new Rectangle(180, 130, 600, 33));
		welcomeTitle.setFont(new java.awt.Font("Dialog", Font.BOLD, 30));
		welcomeTitle.setForeground(Color.lightGray);

		welcomeTitle2.setFont(new java.awt.Font("Dialog", Font.BOLD, 30));
		welcomeTitle2.setText("�� ӭ ʹ �� ѧ �� �� Ϣ �� �� ϵ ͳ");
		welcomeTitle2.setBounds(new Rectangle(181, 131, 608, 33));

		jLabelHelp1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		jLabelHelp1.setText("�����Զԣ�");
		jLabelHelp1.setBounds(new Rectangle(500, 340, 400, 30));
		
		jLabelHelp2.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		jLabelHelp2.setText("ѧ�����༶��Ժϵ���γ̡��ɼ�");
		jLabelHelp2.setBounds(new Rectangle(500, 370, 400, 30));
		
		jLabelHelp3.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		jLabelHelp3.setText("����¼�롢��ѯ���޸ġ�ɾ���Ĳ���");
		jLabelHelp3.setBounds(new Rectangle(500, 400, 400, 30));
		
		stuMainMenu.setText("ѧ������");
		stuAddMenu.setText("  ¼  ��");
		stuAddMenu.addActionListener(new AddStudent(this));
		stuManageMenu.setText("  ��  ��");
		stuManageMenu.addActionListener(new ManageStudent(this));

		departmentMainMenu.setText("Ժϵ����");
		departmentAddMenu.setText("  ¼  ��");
		departmentAddMenu.addActionListener(new AddDepartment(this));
		departmentManagerMenu.setText("  ��  ��");
		departmentManagerMenu.addActionListener(new ManageDepartment(this));

		specialtyMainMenu.setText("רҵ����");
		specialtyAddMenu.setText("  ¼  ��");
		specialtyAddMenu.addActionListener(new AddSpecialty(this));
		specialtyManagerMenu.setText("  ��  ��");
		specialtyManagerMenu.addActionListener(new ManageSpecialty(this));

		classMainMenu.setText("�༶����");
		classAddMenu.setText("  ¼  ��");
		classAddMenu.addActionListener(new AddClass(this));
		classManagerMenu.setText("  ��  ��");
		classManagerMenu.addActionListener(new ManageClass(this));

		courseMainMenu.setText("�γ̹���");
		courseAddMenu.setText("  ¼  ��");
		courseAddMenu.addActionListener(new AddCourse(this));
		courseManagerMenu.setText("  ��  ��");
		courseManagerMenu.addActionListener(new ManageCourse(this));

		scoreMainMenu.setText("�ɼ�����");
		scoreAddMenu.setText("  ¼  ��");
		scoreAddMenu.addActionListener(new AddScore(this));
		scoreManagerMenu.setText("  ��  ��");
		scoreManagerMenu.addActionListener(new ManageScore(this));

		userMainMenu.setText("�û�����");
		userAddMenu.setText("����û�");
		userAddMenu.addActionListener(new AddUser(this));
		userChangePasswordMenu.setText("�޸�����");
		userChangePasswordMenu.addActionListener(new ChangeUserPassword(this));
		userDeleteMenu.setEnabled(true);
		userDeleteMenu.setText("ɾ���û�");
		userDeleteMenu.addActionListener(new DeleteUser(this));

		aboutMainMenu.setText("����");
		aboutMenuItem.setText("  ��  ��");
		aboutMenuItem.addActionListener(new About(this));

		quitMainMenu.setText("�˳�");
		quitMenu.setText("  ��  ��");
		quitMenu.addActionListener(new Quit(this));
		rebootMenu.setText("��������");
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

		// �û��ȼ��ж�
		// 1Ϊ����Ա
		// if (level.equals("1")) {
		// 	// �������κι���
		// }
		// // 2Ϊ��ͨ�û�
		// else if (level.equals("2")) {
		// 	// ���ز��ֹ���
		// 	this.userAddMenu.setVisible(false);
		// 	this.userDeleteMenu.setVisible(false);
		// 	this.stuAddMenu.setVisible(false);
		// 	this.classAddMenu.setVisible(false);
		// 	this.departmentAddMenu.setVisible(false);
		// 	this.courseAddMenu.setVisible(false);
		// 	this.scoreAddMenu.setVisible(false);
		// }
	}

	// ������Ϣ
	public void about(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "ѧ����Ϣ����ϵͳ\n�汾�ţ�1.0\n��   �ߣ�qbfang\n��   �䣺qbfang@163.com", "����", JOptionPane.INFORMATION_MESSAGE);
	}

	// �˳�
	public void quit(ActionEvent e) {
		System.exit(0);
	}

	// ����
	public void reboot(ActionEvent e) {
		this.dispose();
		LoginFrame login = new LoginFrame();
		login.setLocation(400, 200);
		login.setSize(504, 344);
		login.setVisible(true);
		login.setResizable(false);
		login.validate();
	}

	// ѧ��¼��ģ��
	public void addStudent(ActionEvent e) {
		StudentInfoAddFrame newFrame = new StudentInfoAddFrame();
		newFrame.setLocation(400, 200);
		newFrame.setSize(1000, 600);
		newFrame.setVisible(true);
		newFrame.setResizable(true);
		newFrame.validate();
	}

	// ѧ������ģ��
	public void manageStudent(ActionEvent e) {
		StudentManager newFrame = new StudentManager();
		// newFrame.setLayout(new BorderLayout(30, 10));
		newFrame.setLocation(250, 60);
		newFrame.setSize(800, 620);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// �༶¼��ģ��
	public void addClass(ActionEvent e) {
		ClassAddFrame newFrame = new ClassAddFrame();
		newFrame.setLocation(400, 200);
		newFrame.setSize(465, 410);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// �༶����ģ��
	public void manageClass(ActionEvent e) {
		ClassManageFrame newFrame = new ClassManageFrame();
		newFrame.setLocation(400, 100);
		newFrame.setSize(530, 560);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// Ժϵ¼��ģ��
	public void addDepartment(ActionEvent e) {
		DepartAddFrame newFrame = new DepartAddFrame();
		newFrame.setLocation(400, 200);
		newFrame.setSize(465, 310);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// Ժϵ����ģ��
	public void manageDepartment(ActionEvent e) {
		DepartManager newFrame = new DepartManager();
		newFrame.setLocation(400, 100);
		newFrame.setSize(530, 560);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// רҵ¼��ģ��
	public void addSpecialty(ActionEvent e) {
		SpecialtyAddFrame newFrame = new SpecialtyAddFrame();
		newFrame.setLocation(400, 200);
		newFrame.setSize(465, 360);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// רҵ����ģ��
	public void manageSpecialty(ActionEvent e) {
		SpecialtyManageFrame newFrame = new SpecialtyManageFrame();
		newFrame.setLocation(400, 100);
		newFrame.setSize(530, 560);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// �γ�¼��ģ��
	public void addCourse(ActionEvent e) {
		CourceAddFrame newFrame = new CourceAddFrame();
		newFrame.setLocation(400, 200);
		newFrame.setSize(482, 320);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// �γ̹���ģ��
	public void manageCourse(ActionEvent e) {
		CourceManageFrame newFrame = new CourceManageFrame();
		newFrame.setLocation(400, 100);
		newFrame.setSize(530, 560);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// �ɼ�¼��ģ��
	public void addScore(ActionEvent e) {
		ScoreAddFrame newFrame = new ScoreAddFrame();
		newFrame.setLocation(400, 200);
		newFrame.setSize(420, 450);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();

	}

	// �ɼ�����ģ��
	public void manageScore(ActionEvent e) {
		ScoreManager newFrame = new ScoreManager();
		newFrame.setLocation(400, 100);
		newFrame.setSize(550, 560);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// ����û�ģ��
	public void addUser(ActionEvent e) {
		UserAddFrame newFrame = new UserAddFrame();
		newFrame.setLocation(400, 200);
		newFrame.setSize(469, 315);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// ɾ���û�ģ��
	public void deleteUser(ActionEvent e) {
		UserDelete newFrame = new UserDelete();
		newFrame.setLocation(400, 200);
		newFrame.setSize(444, 280);
		newFrame.setVisible(true);
		newFrame.setResizable(false);
		newFrame.validate();
	}

	// �޸�����ģ��
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

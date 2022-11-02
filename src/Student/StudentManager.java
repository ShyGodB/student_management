package Student;

import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.awt.*;
import com.borland.jbcl.layout.XYLayout;

import db.dbConn;

import com.borland.jbcl.layout.*;
// import stuManager.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

// 学生信息管理模块，可对学生信息进行查询、修改、删除操作

public class StudentManager extends JFrame {
	String sql, zhy;
	JLabel jLabel1 = new JLabel();
	XYLayout xYLayout1 = new XYLayout();
	JLabel jLabel2 = new JLabel();
	JComboBox jComboBox1 = new JComboBox();
	JPanel jPanel1 = new JPanel();
	JLabel jLabel3 = new JLabel();
	JButton jButton1 = new JButton(); // 查询
	JPanel jPanel2 = new JPanel();
	XYLayout xYLayout2 = new XYLayout();
	JLabel jLabel4 = new JLabel();
	JTextField jTextField2 = new JTextField();
	JButton jButton2 = new JButton(); // 查询
	ButtonGroup buttonGroup1 = new ButtonGroup();
	JPanel jPanel3 = new JPanel();
	JLabel jLabel6 = new JLabel();
	XYLayout xYLayout4 = new XYLayout();
	JTextField jTextField3 = new JTextField();
	JButton jButton3 = new JButton();
	XYLayout xYLayout5 = new XYLayout();
	JTextField jTextField1 = new JTextField();
	JButton jButton4 = new JButton();
	JButton jButton5 = new JButton();
	JButton jButton7 = new JButton();
	JButton jButton8 = new JButton(); // 重置按钮
	JButton jButton9 = new JButton(); // 分班按钮
	JScrollPane jScrollPane1 = new JScrollPane();
	JTable jTable1 = new JTable();
	int intRow;
	dbConn conn = new dbConn();
	String[] arrField = { "学号", "姓名", "性别", "手机号", "家庭住址" };
	DefaultTableModel model = new DefaultTableModel();
	Object[][] arrData = {};
	static int id; // 学生信息在学生表的编号，即id字段，后续需要用到此字段来确定选择的学生是哪个
	JButton jButton6 = new JButton();
	JOptionPane loginFeedback = new JOptionPane();

	public StudentManager() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		setSize(800, 620);
		getContentPane().setLayout(xYLayout1);
		jLabel1.setFont(new java.awt.Font("新宋体", Font.BOLD, 27));
		jLabel1.setText("学生信息管理");
		jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jLabel2.setText("请选择查询方式：");
		this.setTitle("学生信息管理");
		jPanel1.setBorder(BorderFactory.createEtchedBorder());
		jPanel1.setLayout(xYLayout5);
		jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jLabel3.setText("请输入学号：");
		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jButton1.setText("查询");
		jButton1.addActionListener(new QueryStudentByStudentId(this));
		jPanel2.setBorder(BorderFactory.createEtchedBorder());
		jPanel2.setLayout(xYLayout2);
		jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jLabel4.setText("请输入学生姓名：");
		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jButton2.setText("查询");
		jButton2.addActionListener(new QueryStudentByRealName(this));
		jPanel3.setBorder(BorderFactory.createEtchedBorder());
		jPanel3.setLayout(xYLayout4);
		jLabel6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jLabel6.setText("请输入手机号：");
		jButton3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jButton3.setText("查询");
		jButton3.addActionListener(new QueryStudentByClass(this));
		jComboBox1.addActionListener(new ShowSelector(this));
		jComboBox1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jTextField3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jTextField2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jButton4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jButton4.setText("查看");
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lookStudentInfo(e);
			}
		});
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeStudentInfo(e);
			}
		});
		jButton5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jButton5.setText("修改");
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeStudentInfo(e);
			}
		});

		jButton7.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jButton7.setText("退出");
		jButton7.addActionListener(new StudentManager_Quit(this));

		jButton8.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jButton8.setText("重置");
		jButton8.addActionListener(new StudentManager_Reset(this));

		jButton9.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jButton9.setText("分班");
		jButton9.addActionListener(new StudentManager_Fenban(this));

		jScrollPane1.setBorder(BorderFactory.createEtchedBorder());
		jTable1.setCellSelectionEnabled(true);
		jButton6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jButton6.setText("删除");
		jButton6.addActionListener(new DeleteStudent(this));
		this.getContentPane().add(jPanel2, new XYConstraints(150, 125, 510, 50));
		jPanel2.add(jButton2, new XYConstraints(381, 8, 85, 27));
		this.getContentPane().add(jPanel3, new XYConstraints(150, 125, 510, 50));
		jPanel3.add(jButton3, new XYConstraints(388, 8, 89, 26));
		ShowPanel();
		jComboBox1.addItem("请选择查询方式");
		jComboBox1.addItem("按学号查询");
		jComboBox1.addItem("按姓名查询");
		jComboBox1.addItem("按手机号查询");
		jPanel1.add(jTextField1, new XYConstraints(164, 8, 149, 25));
		jPanel1.add(jButton1, new XYConstraints(360, 8, 80, 29));
		jPanel1.add(jLabel3, new XYConstraints(29, 8, 125, 26));
		jPanel2.add(jLabel4, new XYConstraints(21, 8, 164, 31));
		jPanel2.add(jTextField2, new XYConstraints(206, 8, 132, 27));
		jPanel3.add(jLabel6, new XYConstraints(16, 8, 201, 30));
		jPanel3.add(jTextField3, new XYConstraints(212, 8, 152, 28));
		this.getContentPane().add(jLabel1, new XYConstraints(321, 12, 180, 38));
		this.getContentPane().add(jComboBox1, new XYConstraints(370, 76, -1, 30));
		this.getContentPane().add(jButton8, new XYConstraints(600, 76, 90, 34));
		this.getContentPane().add(jLabel2, new XYConstraints(169, 74, 161, 32));
		this.getContentPane().add(jPanel1, new XYConstraints(150, 125, 510, 50));
		jScrollPane1.getViewport().add(jTable1, null);
		this.getContentPane().add(jScrollPane1, new XYConstraints(20, 193, 759, 325));
		this.getContentPane().add(jButton4, new XYConstraints(80, 545, 90, 34));
		this.getContentPane().add(jButton5, new XYConstraints(200, 545, 90, 34));
		this.getContentPane().add(jButton6, new XYConstraints(320, 545, 90, 34));
		this.getContentPane().add(jButton7, new XYConstraints(590, 545, 90, 34));
		this.getContentPane().add(jButton9, new XYConstraints(440, 545, 90, 34));

		// 查找所有学生
		sql = "select id, realName, gender, phone, homeAddress from student where isDel = false order by id asc";
		// 更新显示
		UpdateRecord();
	}

	// 更新显示
	public void UpdateRecord() {
		Object[][] arrTmp = {}; // 设定表格的字段
		Vector vec = new Vector(1, 1);
		model = new DefaultTableModel(arrTmp, arrField);
		jTable1 = new JTable(model);
		jScrollPane1.getViewport().add(jTable1, null);

		int studentCount = 0;
		try {
			ResultSet rs = conn.query(sql);
			while (rs.next()) {
				studentCount++;
				vec = new Vector();
				vec.add(rs.getString("id").trim());
				vec.add(rs.getString("realName").trim());
				vec.add(rs.getString("gender").trim());
				vec.add(rs.getString("phone").trim());
				vec.add(rs.getString("homeAddress").trim());
				model.addRow(vec);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (studentCount == 0) {
			JOptionPane.showMessageDialog(this, "未查询到符合条件的数据，您可以重新输入查询条件查找", "提 示", JOptionPane.INFORMATION_MESSAGE);
		}
		jScrollPane1.getHorizontalScrollBar();
		jTable1.setGridColor(Color.blue);
		jTable1.setDragEnabled(true);
		jTable1.setSelectionForeground(Color.red);
		jTable1.setSelectionBackground(Color.green);
		jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTable1.setRowSelectionAllowed(true);
		jTable1.setShowVerticalLines(true);
	}

	// 查询方式
	public void ShowPanel() {
		jPanel1.setVisible(false);
		jPanel2.setVisible(false);
		jPanel3.setVisible(false);
		if (jComboBox1.getSelectedIndex() == 1) {
			jPanel1.setVisible(true);
		} else if (jComboBox1.getSelectedIndex() == 2) {
			jPanel2.setVisible(true);
		} else if (jComboBox1.getSelectedIndex() == 3) {
			jPanel3.setVisible(true);
		}
			
	}

	// 获取选定的行
	public void getSelectedRow() {
		intRow = jTable1.getSelectedRow();
		if (intRow == -1)
			return;
		try {
			id = Integer.parseInt(model.getValueAt(intRow, 0).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 退出
	public void quit(ActionEvent e) {
		this.dispose();
	}

	// 清空所有筛选条件，重新获取数据
	public void reset(ActionEvent e) {
		sql = "select id, realName, gender, phone, homeAddress from student where isDel = false order by id asc";
		UpdateRecord();
		jPanel1.setVisible(false);
		jPanel2.setVisible(false);
		jPanel3.setVisible(false);
	}

	// 分班
	public void fenban(ActionEvent e) {
		getSelectedRow();
		if (intRow != -1) {
			StudentFenban newFrame = new StudentFenban(id);
			newFrame.setLocation(400, 200);
			newFrame.setSize(400, 420);
			newFrame.setVisible(true);
			newFrame.setResizable(true);
			newFrame.validate();
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "请选择要查看的信息！", "提 示", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// 按学号查询
	public void queryStudentByStudentId(ActionEvent e) {
		if (!jTextField1.getText().trim().equals("")) {
			sql = "select id, realName, gender, phone, homeAddress from student where isDel = false and id = '" + jTextField1.getText().trim() + "'";
			UpdateRecord();
		} else {
			JOptionPane.showMessageDialog(this, "请输入要查询的学生学号！", "提 示", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// 选择触发
	public void showSelector(ActionEvent e) {
		ShowPanel();
	}

	// 按姓名查询
	public void queryStudentByRealName(ActionEvent e) {
		if (!jTextField2.getText().trim().equals("")) {
			sql = "select id, realName, gender, phone, homeAddress from student where isDel = false and realName like'" + ( "%" + jTextField2.getText().trim() + "%") + "'";
			UpdateRecord();
		} else {
			JOptionPane.showMessageDialog(this, "请输入要查询的学生姓名！", "提 示", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// 按手机号查询
	public void queryStudentByPhone(ActionEvent e) {
		String phone = jTextField3.getText().trim();
		if (phone.length() != 11) {
			JOptionPane.showMessageDialog(this, "请输入11位手机号", "提 示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		sql = "select id, realName, gender, phone, homeAddress from student where isDel = false and phone ='" + jTextField3.getText().trim() + "'";
		UpdateRecord();	
	}

	// 查看学生的详细信息
	public void lookStudentInfo(ActionEvent e) {
		getSelectedRow();
		if (intRow != -1) {
			StudentLook newFrame = new StudentLook(id);
			newFrame.setLocation(400, 200);
			newFrame.setSize(1000, 600);
			newFrame.setVisible(true);
			newFrame.setResizable(true);
			newFrame.validate();
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "请选择要查看的信息！", "提 示", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// 修改
	public void changeStudentInfo(ActionEvent e) {
		getSelectedRow();
		if (intRow != -1) {
			StudentChange newFrame = new StudentChange(id);
			newFrame.setLocation(400, 200);
			newFrame.setSize(1000, 600);
			newFrame.setVisible(true);
			newFrame.setResizable(false);
			newFrame.validate();
			this.dispose();
		} else
			JOptionPane.showMessageDialog(this, "请选择要修改的信息！", "提 示", JOptionPane.INFORMATION_MESSAGE);
	}

	// 触发删除
	public void deleteStudent(ActionEvent e) {
		getSelectedRow();
		if (intRow != -1) {
			try {
				if (0 < conn.getUpdate("update student set isDel = true where id = '" + id + "'")) {
					UpdateRecord();
					JOptionPane.showMessageDialog(this, "删除学生信息成功！", "提 示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "删除学生信息失败！", "提 示", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (Exception error) {
				System.out.println("error is: " + error.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(this, "请选择要删除的信息！", "提 示", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

class DeleteStudent implements ActionListener {
	private StudentManager adaptee;

	DeleteStudent(StudentManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.deleteStudent(e);
	}
}

class QueryStudentByClass implements ActionListener {
	private StudentManager adaptee;

	QueryStudentByClass(StudentManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.queryStudentByPhone(e);
	}
}

class QueryStudentByRealName implements ActionListener {
	private StudentManager adaptee;

	QueryStudentByRealName(StudentManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.queryStudentByRealName(e);
	}
}

class QueryStudentByStudentId implements ActionListener {
	private StudentManager adaptee;

	QueryStudentByStudentId(StudentManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.queryStudentByStudentId(e);
	}
}

class StudentManager_Quit implements ActionListener {
	private StudentManager adaptee;

	StudentManager_Quit(StudentManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.quit(e);
	}
}

class StudentManager_Reset implements ActionListener {
	private StudentManager adaptee;

	StudentManager_Reset(StudentManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.reset(e);
	}
}

class StudentManager_Fenban implements ActionListener {
	private StudentManager adaptee;

	StudentManager_Fenban(StudentManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.fenban(e);
	}
}

class ShowSelector implements ActionListener {
	private StudentManager adaptee;

	ShowSelector(StudentManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.showSelector(e);
	}
}

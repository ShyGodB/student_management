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

// ѧ����Ϣ����ģ�飬�ɶ�ѧ����Ϣ���в�ѯ���޸ġ�ɾ������

public class StudentManager extends JFrame {
	String sql, zhy;
	JLabel jLabel1 = new JLabel();
	XYLayout xYLayout1 = new XYLayout();
	JLabel jLabel2 = new JLabel();
	JComboBox jComboBox1 = new JComboBox();
	JPanel jPanel1 = new JPanel();
	JLabel jLabel3 = new JLabel();
	JButton jButton1 = new JButton(); // ��ѯ
	JPanel jPanel2 = new JPanel();
	XYLayout xYLayout2 = new XYLayout();
	JLabel jLabel4 = new JLabel();
	JTextField jTextField2 = new JTextField();
	JButton jButton2 = new JButton(); // ��ѯ
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
	JButton jButton8 = new JButton(); // ���ð�ť
	JButton jButton9 = new JButton(); // �ְఴť
	JScrollPane jScrollPane1 = new JScrollPane();
	JTable jTable1 = new JTable();
	int intRow;
	dbConn conn = new dbConn();
	String[] arrField = { "ѧ��", "����", "�Ա�", "�ֻ���", "��ͥסַ" };
	DefaultTableModel model = new DefaultTableModel();
	Object[][] arrData = {};
	static int id; // ѧ����Ϣ��ѧ����ı�ţ���id�ֶΣ�������Ҫ�õ����ֶ���ȷ��ѡ���ѧ�����ĸ�
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
		jLabel1.setFont(new java.awt.Font("������", Font.BOLD, 27));
		jLabel1.setText("ѧ����Ϣ����");
		jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jLabel2.setText("��ѡ���ѯ��ʽ��");
		this.setTitle("ѧ����Ϣ����");
		jPanel1.setBorder(BorderFactory.createEtchedBorder());
		jPanel1.setLayout(xYLayout5);
		jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jLabel3.setText("������ѧ�ţ�");
		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jButton1.setText("��ѯ");
		jButton1.addActionListener(new QueryStudentByStudentId(this));
		jPanel2.setBorder(BorderFactory.createEtchedBorder());
		jPanel2.setLayout(xYLayout2);
		jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jLabel4.setText("������ѧ��������");
		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jButton2.setText("��ѯ");
		jButton2.addActionListener(new QueryStudentByRealName(this));
		jPanel3.setBorder(BorderFactory.createEtchedBorder());
		jPanel3.setLayout(xYLayout4);
		jLabel6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jLabel6.setText("�������ֻ��ţ�");
		jButton3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jButton3.setText("��ѯ");
		jButton3.addActionListener(new QueryStudentByClass(this));
		jComboBox1.addActionListener(new ShowSelector(this));
		jComboBox1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jTextField3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jTextField2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jButton4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jButton4.setText("�鿴");
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
		jButton5.setText("�޸�");
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeStudentInfo(e);
			}
		});

		jButton7.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jButton7.setText("�˳�");
		jButton7.addActionListener(new StudentManager_Quit(this));

		jButton8.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jButton8.setText("����");
		jButton8.addActionListener(new StudentManager_Reset(this));

		jButton9.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jButton9.setText("�ְ�");
		jButton9.addActionListener(new StudentManager_Fenban(this));

		jScrollPane1.setBorder(BorderFactory.createEtchedBorder());
		jTable1.setCellSelectionEnabled(true);
		jButton6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jButton6.setText("ɾ��");
		jButton6.addActionListener(new DeleteStudent(this));
		this.getContentPane().add(jPanel2, new XYConstraints(150, 125, 510, 50));
		jPanel2.add(jButton2, new XYConstraints(381, 8, 85, 27));
		this.getContentPane().add(jPanel3, new XYConstraints(150, 125, 510, 50));
		jPanel3.add(jButton3, new XYConstraints(388, 8, 89, 26));
		ShowPanel();
		jComboBox1.addItem("��ѡ���ѯ��ʽ");
		jComboBox1.addItem("��ѧ�Ų�ѯ");
		jComboBox1.addItem("��������ѯ");
		jComboBox1.addItem("���ֻ��Ų�ѯ");
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

		// ��������ѧ��
		sql = "select id, realName, gender, phone, homeAddress from student where isDel = false order by id asc";
		// ������ʾ
		UpdateRecord();
	}

	// ������ʾ
	public void UpdateRecord() {
		Object[][] arrTmp = {}; // �趨�����ֶ�
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
			JOptionPane.showMessageDialog(this, "δ��ѯ���������������ݣ����������������ѯ��������", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
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

	// ��ѯ��ʽ
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

	// ��ȡѡ������
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

	// �˳�
	public void quit(ActionEvent e) {
		this.dispose();
	}

	// �������ɸѡ���������»�ȡ����
	public void reset(ActionEvent e) {
		sql = "select id, realName, gender, phone, homeAddress from student where isDel = false order by id asc";
		UpdateRecord();
		jPanel1.setVisible(false);
		jPanel2.setVisible(false);
		jPanel3.setVisible(false);
	}

	// �ְ�
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
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫ�鿴����Ϣ��", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// ��ѧ�Ų�ѯ
	public void queryStudentByStudentId(ActionEvent e) {
		if (!jTextField1.getText().trim().equals("")) {
			sql = "select id, realName, gender, phone, homeAddress from student where isDel = false and id = '" + jTextField1.getText().trim() + "'";
			UpdateRecord();
		} else {
			JOptionPane.showMessageDialog(this, "������Ҫ��ѯ��ѧ��ѧ�ţ�", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// ѡ�񴥷�
	public void showSelector(ActionEvent e) {
		ShowPanel();
	}

	// ��������ѯ
	public void queryStudentByRealName(ActionEvent e) {
		if (!jTextField2.getText().trim().equals("")) {
			sql = "select id, realName, gender, phone, homeAddress from student where isDel = false and realName like'" + ( "%" + jTextField2.getText().trim() + "%") + "'";
			UpdateRecord();
		} else {
			JOptionPane.showMessageDialog(this, "������Ҫ��ѯ��ѧ��������", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// ���ֻ��Ų�ѯ
	public void queryStudentByPhone(ActionEvent e) {
		String phone = jTextField3.getText().trim();
		if (phone.length() != 11) {
			JOptionPane.showMessageDialog(this, "������11λ�ֻ���", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		sql = "select id, realName, gender, phone, homeAddress from student where isDel = false and phone ='" + jTextField3.getText().trim() + "'";
		UpdateRecord();	
	}

	// �鿴ѧ������ϸ��Ϣ
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
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫ�鿴����Ϣ��", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// �޸�
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
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫ�޸ĵ���Ϣ��", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
	}

	// ����ɾ��
	public void deleteStudent(ActionEvent e) {
		getSelectedRow();
		if (intRow != -1) {
			try {
				if (0 < conn.getUpdate("update student set isDel = true where id = '" + id + "'")) {
					UpdateRecord();
					JOptionPane.showMessageDialog(this, "ɾ��ѧ����Ϣ�ɹ���", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "ɾ��ѧ����Ϣʧ�ܣ�", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (Exception error) {
				System.out.println("error is: " + error.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫɾ������Ϣ��", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
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

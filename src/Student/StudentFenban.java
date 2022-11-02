package Student;

import java.awt.*;
import javax.swing.*;
import java.util.*;

import db.dbConn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class StudentFenban extends JFrame {
	JPanel contentPane;
	JLabel jLabel1 = new JLabel(); // 学号
	JLabel jLabel2 = new JLabel(); // 姓名

	JLabel jLabel3 = new JLabel(); // 班级

	JTextField jTextField1 = new JTextField(); // 学号
	JTextField jTextField2 = new JTextField(); // 姓名
	JComboBox jComboBox1 = new JComboBox(); // 班级
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	dbConn con = new dbConn();
	Object clas[] = {};
	dbConn conn = new dbConn();
	JOptionPane loginFeedback = new JOptionPane();
	String studentId, realName, classId;
	int findId;
    ArrayList<String> idList = new ArrayList<String>();
    ArrayList<String> departmentIdList = new ArrayList<String>();
    ArrayList<String> specialtyIdList = new ArrayList<String>();

	public StudentFenban(int findId) {
		this.findId = findId;
		try {
			init();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void init() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		setSize(new Dimension(800, 600));
		setTitle("学生信息详情");

		jLabel1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel1.setText("学号:");
		jLabel1.setBounds(new Rectangle(50, 74, 90, 22));

		jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel2.setText("姓名:");
		jLabel2.setBounds(new Rectangle(50, 124, 90, 22));

		jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel3.setText("班级:");
		jLabel3.setBounds(new Rectangle(50, 174, 90, 22));

		jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField1.setText("");
		jTextField1.setBounds(new Rectangle(150, 74, 195, 23));

		jTextField2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField2.setText("");
		jTextField2.setBounds(new Rectangle(150, 124, 195, 23));

		jComboBox1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jComboBox1.setBounds(new Rectangle(150, 174, 195, 25));

        jButton1.setBounds(new Rectangle(83, 290, 90, 29));
		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton1.setText("提    交");
		jButton1.addActionListener(new StudentFenban_fenban(this));

		jButton2.setBounds(new Rectangle(247, 290, 90, 29));
		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton2.setText("退    出");
		jButton2.addActionListener(new StudentFenban_Quit(this));

		loginFeedback.setBounds(new Rectangle(28, 263, 262, 90));
		loginFeedback.setLayout(null);
		contentPane.add(jLabel1);
		contentPane.add(jLabel2);
		contentPane.add(jLabel3);
		contentPane.add(jTextField1);
		contentPane.add(jTextField2);
        contentPane.add(jComboBox1);
		contentPane.add(jButton1);
		contentPane.add(jButton2);
		contentPane.add(loginFeedback);

        jComboBox1.addItem("请选择班级");

		// 获取学生数据
		try {
			ResultSet rs = conn.query("select id, realName, classId from student where id = '" + findId + "'");
			while (rs.next()) {
				studentId = rs.getString("id"); 
				realName = rs.getString("realName"); 
				classId = rs.getString("classId");
				jTextField1.setText(studentId);
				jTextField2.setText(realName);
                jTextField1.setEnabled(false);
                jTextField2.setEnabled(false);
			}
			rs.close();
		} catch (Exception error) {
			System.out.println("error is: " + error);
		}

        // 获取班级
		try {
			ResultSet result = conn.query("select id, departmentId, specialtyId, cname from class where isDel = false order by id asc");
			while (result.next()) {
				String cname = result.getString("cname");
				idList.add(result.getString("id"));
                departmentIdList.add(result.getString("departmentId"));
                specialtyIdList.add(result.getString("specialtyId"));
				jComboBox1.addItem(cname);
			}
			result.close();
		} catch (Exception error) {
			System.out.println("error is: " + error);
		}
	}

	// 退出
	public void quitCurrentWindow(ActionEvent e) {
		this.dispose();
	}

    public void fenban(ActionEvent e) {
        try {
            int index = jComboBox1.getSelectedIndex();
            if (index == 0) {
                JOptionPane.showMessageDialog(this, "请选择班级", "提 示", JOptionPane.INFORMATION_MESSAGE);
            }
            String classId = idList.get(index - 1);
            String departmentId = departmentIdList.get(index - 1);
            String specialtyId = specialtyIdList.get(index - 1);
			int res = conn.getUpdate("update student set departmentId = '" + departmentId + "', specialtyId = '" + specialtyId + "', classId = '" + classId + "' where id = '" + studentId + "'");
			if (res > 0) {
				JOptionPane.showMessageDialog(this, "分班成功", "提 示", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "分班失败", "提 示", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception error) {
			System.out.println("error is: " + error.getMessage());
		}
    }

    public void quit(ActionEvent e) {
        this.dispose();
    }
}

class StudentFenban_fenban implements ActionListener {
	private StudentFenban adaptee;

	StudentFenban_fenban(StudentFenban adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.fenban(e);
	}
}

class StudentFenban_Quit implements ActionListener {
	private StudentFenban adaptee;

	StudentFenban_Quit(StudentFenban adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.quit(e);
	}
}
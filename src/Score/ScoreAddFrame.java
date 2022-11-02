package Score;

import java.sql.ResultSet;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import db.dbConn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Title: 成绩录入 
 * Description: 成绩录入模块
 * 
 */

public class ScoreAddFrame extends JFrame {
	JPanel contentPane;
	JLabel jLabel1 = new JLabel();
	int FS, spid, FC;
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JLabel jLabel5 = new JLabel();
	String IS;
	JTextField jTextField2 = new JTextField();
	JTextField jTextField3 = new JTextField();
	JTextField jTextField4 = new JTextField();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	JOptionPane jOptionPane1 = new JOptionPane();
	JLabel jLabel6 = new JLabel();
	JComboBox jComboBox1 = new JComboBox();
	dbConn sta = new dbConn();
	ArrayList<String> courseIdList = new ArrayList<String>();

	public ScoreAddFrame() {
		try {
			init();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void init() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		setSize(new Dimension(482, 300));
		setTitle("成绩录入");
		jLabel1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jLabel1.setText("成  绩  录  入");
		jLabel1.setBounds(new Rectangle(178, 17, 126, 25));

		jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jLabel3.setText("学生学号:");
		jLabel3.setBounds(new Rectangle(75, 70, 90, 21));

		jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jLabel4.setText("成绩(数字):");
		jLabel4.setBounds(new Rectangle(75, 170, 90, 21));
		jLabel5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jLabel5.setText("备   注:");
		jLabel5.setBounds(new Rectangle(75, 220, 90, 21));
		jTextField2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField2.setText("");
		jTextField2.setBounds(new Rectangle(190, 70, 184, 26));
		jTextField3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jTextField3.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField3.setText("");
		jTextField3.setBounds(new Rectangle(190, 170, 184, 26));
		jTextField4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jTextField4.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField4.setText("");
		jTextField4.setBounds(new Rectangle(190, 220, 184, 26));
		jButton1.setBounds(new Rectangle(83, 290, 90, 29));
		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton1.setText("提    交");
		jButton1.addActionListener(new ScoreAddFrame_jButton1_actionAdapter(this));
		jButton2.setBounds(new Rectangle(247, 290, 90, 29));
		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton2.setText("退    出");
		jButton2.addActionListener(new ScoreAddFrame_jButton2_actionAdapter(this));
		jOptionPane1.setBounds(new Rectangle(75, 261, 262, 90));
		jOptionPane1.setLayout(null);
		jLabel6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jLabel6.setText("课    程:");
		jLabel6.setBounds(new Rectangle(75, 120, 82, 21));
		jComboBox1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jComboBox1.setBounds(new Rectangle(190, 120, 160, 25));
		contentPane.add(jLabel1);
		contentPane.add(jButton1);
		contentPane.add(jButton2);
		contentPane.add(jOptionPane1);
		contentPane.add(jTextField3);
		contentPane.add(jLabel5);
		contentPane.add(jTextField4);
		contentPane.add(jLabel4);
		contentPane.add(jLabel3);
		contentPane.add(jTextField2);
		contentPane.add(jComboBox1);
		contentPane.add(jLabel6);
		jComboBox1.addItem("请选择");
		try {
			// 选择课程
			ResultSet rs = sta.query("select id, cname from course where isDel = false order by id asc");
			while (rs.next()) {
				String xibu = rs.getString("cname");
				String id = rs.getString("id");
				courseIdList.add(id);
				jComboBox1.addItem(xibu);
			}
			rs.close();
		} catch (Exception error) {
			System.out.println("error is: " + error);
		}
	}

	// 录入
	public void InC() {
		String studentId = jTextField2.getText().trim();
		int index = jComboBox1.getSelectedIndex();
		String courseId = "0";
		if (index > 0) {
			courseId = courseIdList.get(index - 1);
		}
		Float score = Float.parseFloat(jTextField3.getText().trim());
		String remark = jTextField4.getText().trim();
		// 异常判断
		if (studentId.length() == 0) {
			jOptionPane1.showMessageDialog(this, "学生学号不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		} 
		if (index == 0) {
			jOptionPane1.showMessageDialog(this, "请选择课程！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		} 
		if (jTextField3.getText().length() == 0) {
			jOptionPane1.showMessageDialog(this, "成绩不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		}
		String scoreLevel;
		if (score >= 90) {
			scoreLevel = "优秀";
		} else if (score >= 80) {
			scoreLevel = "良好";
		} else if (score >= 60) {
			scoreLevel = "及格";
		} else {
			scoreLevel = "不及格";
		}
		try {
			boolean ifExist = false;
			ResultSet rs = sta.query("select id from student where id = '" + studentId + "'");
			while (rs.next()) {
				ifExist = true;
			}
			if (!ifExist) {
				jOptionPane1.showMessageDialog(this, "经查证，无学生信息，请重新输入学号", "提示", JOptionPane.INFORMATION_MESSAGE, null);
			} else {
				sta.getUpdate("insert into score (studentId, courseId, score, scoreLevel, remark) values ('"
						+ studentId + "','"
						+ courseId + "','"
						+ score + "','"
						+ scoreLevel + "','"
						+ remark + "')");
				jOptionPane1.showMessageDialog(this, "成绩录入成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
			}
			rs.close();
		} catch (Exception error) {
			System.out.println("error is: " + error);
		}
	}

	// 录入
	public void jButton1_actionPerformed(ActionEvent e) {
		InC();
	}

	// 退出
	public void jButton2_actionPerformed(ActionEvent e) {
		this.dispose();
	}
}

class ScoreAddFrame_jButton1_actionAdapter implements ActionListener {
	private ScoreAddFrame adaptee;

	ScoreAddFrame_jButton1_actionAdapter(ScoreAddFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton1_actionPerformed(e);
	}
}

class ScoreAddFrame_jButton2_actionAdapter implements ActionListener {
	private ScoreAddFrame adaptee;

	ScoreAddFrame_jButton2_actionAdapter(ScoreAddFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton2_actionPerformed(e);
	}
}

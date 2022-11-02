package Cource;

import java.awt.Dimension;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JTextField;

import db.dbConn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Title: 课程录入 
 *
 * 
 */

public class CourceAddFrame extends JFrame {
	JPanel contentPane;
	int FS, spid, FC;
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	String IS;
	JTextField jTextField1 = new JTextField();
	JTextField jTextField2 = new JTextField();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	JOptionPane jOptionPane1 = new JOptionPane();
	JLabel jLabel6 = new JLabel();
	dbConn conn = new dbConn();

	public CourceAddFrame() {
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
		setTitle("课程录入");
		jLabel1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jLabel1.setText("课  程  录  入");
		jLabel1.setBounds(new Rectangle(178, 17, 126, 25));

		jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jLabel2.setText("课程名称：");
		jLabel2.setBounds(new Rectangle(75, 70, 81, 21));

		jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jLabel3.setText("课程介绍：");
		jLabel3.setBounds(new Rectangle(75, 120, 81, 21));

		jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField1.setText("");
		jTextField1.setBounds(new Rectangle(190, 70, 184, 26));

		jTextField2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField2.setText("");
		jTextField2.setBounds(new Rectangle(190, 120, 184, 26));

		jButton1.setBounds(new Rectangle(103, 170, 90, 29));
		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton1.setText("提    交");
		jButton1.addActionListener(new CourceAddFrame_jButton1_actionAdapter(this));

		jButton2.setBounds(new Rectangle(277, 170, 90, 29));
		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton2.setText("退    出");
		jButton2.addActionListener(new CourceAddFrame_jButton2_actionAdapter(this));

		jOptionPane1.setBounds(new Rectangle(75, 261, 262, 90));
		jOptionPane1.setLayout(null);
		contentPane.add(jLabel1);
		contentPane.add(jButton1);
		contentPane.add(jButton2);
		contentPane.add(jOptionPane1);
		contentPane.add(jTextField2);
		contentPane.add(jLabel3);
		contentPane.add(jLabel2);
		contentPane.add(jTextField1);
	}

	// 录入
	public void addCourse() {
		// 异常判断
		String cname = jTextField1.getText();
		String remark = jTextField2.getText();
		if (cname.equals("")) {
			jOptionPane1.showMessageDialog(this, "请输入课程名称", "提示", JOptionPane.INFORMATION_MESSAGE, null);
			return;
		}
		
		try {
			boolean ifCourseExist = false;
			ResultSet rs = conn.query("select id from course where cname = '" + cname + "' and isDel = false");
			while (rs.next()) {
				ifCourseExist = true;
			}
			if (ifCourseExist) {
				jOptionPane1.showMessageDialog(this, "已存在同名课程", "提示", JOptionPane.INFORMATION_MESSAGE, null);
			} else {
				conn.getUpdate("insert into course (cname, remark) values ('"
						+ cname + "', '" + remark + "')");
				jOptionPane1.showMessageDialog(this, "成功录入课程", "提示", JOptionPane.INFORMATION_MESSAGE, null);
			}

		} catch (Exception error) {
			System.out.println("error is: " + error);;
		}
	}

	public void jButton1_actionPerformed(ActionEvent e) {
		addCourse();
	}

	public void jButton2_actionPerformed(ActionEvent e) {
		this.dispose();
	}
}

class CourceAddFrame_jButton1_actionAdapter implements ActionListener {
	private CourceAddFrame adaptee;

	CourceAddFrame_jButton1_actionAdapter(CourceAddFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton1_actionPerformed(e);
	}
}

class CourceAddFrame_jButton2_actionAdapter implements ActionListener {
	private CourceAddFrame adaptee;

	CourceAddFrame_jButton2_actionAdapter(CourceAddFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton2_actionPerformed(e);
	}
}

package Cource;

import java.awt.Font;
import javax.swing.JFrame;
import com.borland.jbcl.layout.XYLayout;

import db.dbConn;

import com.borland.jbcl.layout.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

/**
 * 
 * Title: 课程修改 
 * Description: 课程修改模块
 * 
 */

public class CourceChangeFrame extends JFrame {
	XYLayout xYLayout1 = new XYLayout();
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JTextField jTextField1 = new JTextField();
	JTextField jTextField2 = new JTextField();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	dbConn conn = new dbConn();
	JOptionPane jOptionPane1 = new JOptionPane();
	int csid;
	int findId;

	public CourceChangeFrame(int findId) {
		this.findId = findId;
		try {
			init();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void init() throws Exception {
		getContentPane().setLayout(xYLayout1);
		this.setTitle("课程修改与删除");
		xYLayout1.setWidth(500);
		xYLayout1.setHeight(350);
		jLabel1.setFont(new java.awt.Font("新宋体", Font.BOLD, 20));
		jLabel1.setText("课   程   修   改 ");

		jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jLabel2.setText("课程名称：");

		jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jLabel3.setText("课程介绍：");

		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton1.setText("修    改");
		jButton1.addActionListener(new CourceManager_jButton1_actionAdapter(this));

		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton2.setText("退    出");
		jButton2.addActionListener(new CourceManager_jButton3_actionAdapter(this));

		jTextField2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		this.getContentPane().add(jLabel2, new XYConstraints(85, 80, 80, 30));
		this.getContentPane().add(jLabel3, new XYConstraints(85, 130, 80, 30));
		this.getContentPane().add(jTextField1, new XYConstraints(200, 80, 140, 30));
		this.getContentPane().add(jTextField2, new XYConstraints(200, 130, 140, 30));
		this.getContentPane().add(jLabel1, new XYConstraints(158, 21, -1, 49));
		this.getContentPane().add(jButton1, new XYConstraints(130, 180, 90, 30));
		this.getContentPane().add(jButton2, new XYConstraints(295, 180, 90, 30));

		// 获取课程信息
		try {
			ResultSet rs = conn.query("select id, cname, remark from course where isDel = false and id = '" + findId + "'");
			while (rs.next()) {
				String cname = rs.getString("cname");
				String remark = rs.getString("remark");
				jTextField1.setText(cname);
				jTextField2.setText(remark);
			}

		} catch (Exception error) {
			System.out.println("error is: " + error);
		}
	}

	// 选定行后在修改界面显示选定默认值
	// public void setF() {
	// 	if (findId < 0)
	// 		return;
	// 	else {
	// 		try {
	// 			ResultSet rs = conn.query("select * from tb_cource where courceId='" + Integer.valueOf(findId) + "'");
	// 			while (rs.next()) {
	// 				jComboBox1.setSelectedItem(String.valueOf(rs.getString("courceSpecName")));
	// 				jTextField1.setText(rs.getString("courceName").trim());
	// 				jTextField2.setText(rs.getString("courceHour").trim());
	// 			}
	// 			rs.close();
	// 		} catch (Exception error) {
	// 			System.out.println("error is: " + error);
	// 		}
	// 	}
	// }

	// 退出
	public void jButton3_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	// 修改
	public void jButton1_actionPerformed(ActionEvent e) {
		String cname = jTextField1.getText().trim();
		String remark = jTextField2.getText().trim();
		try {
			conn.getUpdate("update course set cname ='" + cname + "', remark = '" + remark
					+ "' where id = '" + findId + "'");
			jOptionPane1.showMessageDialog(this, "课程信息修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		} catch (Exception a) {
			System.out.println(a.getMessage());
		}
	}
}

class CourceManager_jButton1_actionAdapter implements ActionListener {
	private CourceChangeFrame adaptee;

	CourceManager_jButton1_actionAdapter(CourceChangeFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton1_actionPerformed(e);
	}
}

class CourceManager_jButton3_actionAdapter implements ActionListener {
	private CourceChangeFrame adaptee;

	CourceManager_jButton3_actionAdapter(CourceChangeFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton3_actionPerformed(e);
	}
}

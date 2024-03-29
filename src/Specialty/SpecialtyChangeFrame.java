package Specialty;

import java.sql.ResultSet;
import java.awt.*;
import java.util.*;

import javax.swing.*;

import db.dbConn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Title: 院系修改 
 * Description: 院系信息修改模块
 *
 */

public class SpecialtyChangeFrame extends JFrame {
	JPanel contentPane;
	JLabel jLabel1 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	JOptionPane jOptionPane1 = new JOptionPane();
	JComboBox jComboBox1 = new JComboBox();
	JTextField jTextField1 = new JTextField();
	JTextField jTextField2 = new JTextField();
	dbConn conn = new dbConn();
	String find;
	ArrayList<String> idList = new ArrayList<String>();

	public SpecialtyChangeFrame(String find) {
		this.find = find;
		try {
			init();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void init() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		setSize(new Dimension(465, 280));
		setTitle("专业修改");
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		jLabel1.setText("专  业  信  息  录  入");
		jLabel1.setBounds(new Rectangle(136, 20, 212, 25));

		jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel2.setText("专业名称:");
		jLabel2.setBounds(new Rectangle(80, 80, 90, 20));

		jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel3.setText("所属院系:");
		jLabel3.setBounds(new Rectangle(80, 130, 90, 20));

		jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel4.setText("专业介绍:");
		jLabel4.setBounds(new Rectangle(80, 180, 90, 20));

		jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField1.setText("");
		jTextField1.setBounds(new Rectangle(180, 80, 180, 25));

		jComboBox1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jComboBox1.setEditable(true);
		jComboBox1.setBounds(new Rectangle(180, 130, 180, 25));
		jComboBox1.addActionListener(new SpecialtyChangeFrame_jComboBox1_actionAdapter(null));

		jTextField2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField2.setText("");
		jTextField2.setBounds(new Rectangle(180, 180, 180, 25));

		jButton1.setBounds(new Rectangle(102, 223, 96, 29));
		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton1.setText("确    定");
		jButton1.addActionListener(new SpecialtyChangeFrame_jButton1_actionAdapter(this));

		jButton2.setBounds(new Rectangle(265, 221, 96, 31));
		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton2.setToolTipText("");
		jButton2.setText("退    出");
		jButton2.addActionListener(new SpecialtyChangeFrame_jButton2_actionAdapter(this));

		jOptionPane1.setBounds(new Rectangle(106, 258, 262, 90));
		jOptionPane1.setLayout(null);

		contentPane.add(jLabel1);
		contentPane.add(jOptionPane1);

		contentPane.add(jLabel2);
		contentPane.add(jTextField1);
		contentPane.add(jLabel3);
		contentPane.add(jComboBox1);
		contentPane.add(jLabel4);
		contentPane.add(jTextField2);

		contentPane.add(jButton2);
		contentPane.add(jButton1);

		jComboBox1.addItem("请选择院系");
		// 获取院系数据
		try {
			ResultSet rs = conn.query("select id, cname from department where isDel = false order by id asc");
			while (rs.next()) {
				String xibu = rs.getString("cname");
				String id = rs.getString("id");
				idList.add(id);
				jComboBox1.addItem(xibu);
			}
		} catch (Exception error) {
			System.out.println("error is: " + error);
		}

		// 获取选择的专业信息
		try {
			ResultSet rs = conn.query("select id, departmentId, cname, remark from specialty where isDel = false and id = '" + find + "'");
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

	// 退出
	public void jButton2_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	// 修改
	public void jButton1_actionPerformed(ActionEvent e) {
		// 异常判断
		int index = jComboBox1.getSelectedIndex();
		String cname = jTextField1.getText();
		String remark = jTextField2.getText();
		if (cname.equals("")) {
			jOptionPane1.showMessageDialog(this, "请输入专业名称", "提示", JOptionPane.INFORMATION_MESSAGE, null);
			return;
		}
		if (index == 0) {
			jOptionPane1.showMessageDialog(this, "请选择所属院系", "提示", JOptionPane.INFORMATION_MESSAGE, null);
			return;
		}
		String departmentId = idList.get(index - 1);
		try {
			// 先针对输入的院系名称进行查询，如果已存在同名院系，则提示院系已存在
			boolean specialtyExist = false;

			ResultSet rs_departExist = conn.query("select id from specialty where cname = '"
					+ cname + "' and isDel = false and departmentId = '" + departmentId + "'");
			while (rs_departExist.next()) {
				specialtyExist = true;
			}

			if (specialtyExist) {
				jOptionPane1.showMessageDialog(this, "选定的院系中已存在同名专业", "提示", JOptionPane.INFORMATION_MESSAGE, null);
			} else {
				conn.getUpdate("update specialty set departmentId = '"
				 		+ departmentId + "', cname = '"
						+ cname + "', remark = '"
						+ remark + "' where id = '" + find + "'");
				jOptionPane1.showMessageDialog(this, "录入专业成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
			}

		} catch (Exception error) {
			System.out.println("error is: " + error);;
		}
	}

	// 选择触发
	public void jComboBox1_actionPerformed(ActionEvent e) {
		
	}
}

class SpecialtyChangeFrame_jComboBox1_actionAdapter implements ActionListener {
	private SpecialtyChangeFrame adaptee;

	SpecialtyChangeFrame_jComboBox1_actionAdapter(SpecialtyChangeFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		// adaptee.jComboBox1_actionPerformed(e);
	}
}

class SpecialtyChangeFrame_jButton1_actionAdapter implements ActionListener {

	private SpecialtyChangeFrame adaptee;

	SpecialtyChangeFrame_jButton1_actionAdapter(SpecialtyChangeFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton1_actionPerformed(e);
	}
}

class SpecialtyChangeFrame_jButton2_actionAdapter implements ActionListener {

	private SpecialtyChangeFrame adaptee;

	SpecialtyChangeFrame_jButton2_actionAdapter(SpecialtyChangeFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton2_actionPerformed(e);
	}
}

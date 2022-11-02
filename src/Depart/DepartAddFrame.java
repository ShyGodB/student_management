package Depart;

import java.sql.ResultSet;
import java.awt.*;
import javax.swing.*;

import db.dbConn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Title: 院系信息录入 
 * Description: 院系信息录入模块，实现对学院、专业的录入。
 */

public class DepartAddFrame extends JFrame {
	JPanel contentPane;
	JLabel jLabel1 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	JOptionPane jOptionPane1 = new JOptionPane();
	JTextField jTextField1 = new JTextField();
	JTextField jTextField2 = new JTextField();
	String xueyuan, zhuanye;
	dbConn conn = new dbConn();
	JLabel jLabel4 = new JLabel();

	public DepartAddFrame() {
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
		setTitle("院系录入");
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		jLabel1.setText("院  系  信  息  录  入");
		jLabel1.setBounds(new Rectangle(136, 20, 212, 25));
		
		jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel3.setText("院系名称:");
		jLabel3.setBounds(new Rectangle(80, 80, 90, 20));
		
		jButton1.setBounds(new Rectangle(102, 180, 96, 29));
		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton1.setText("提    交");
		jButton1.addActionListener(new AddDepartFrame_jButton1_actionAdapter(this));
		
		jButton2.setBounds(new Rectangle(265, 180, 96, 31));
		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton2.setToolTipText("");
		jButton2.setText("退    出");
		jButton2.addActionListener(new AddDepartFrame_jButton2_actionAdapter(this));
		jOptionPane1.setBounds(new Rectangle(106, 258, 262, 90));
		jOptionPane1.setLayout(null);

		jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField1.setText("");
		jTextField1.setBounds(new Rectangle(180, 80, 180, 25));

		jTextField2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField2.setText("");
		jTextField2.setBounds(new Rectangle(180, 130, 180, 25));

		// jComboBox1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		// jComboBox1.setEditable(true);
		// jComboBox1.setBounds(new Rectangle(180, 80, 180, 25));
		// jComboBox1.addActionListener(new AddDepartFrame_jComboBox1_actionAdapter(this));
		jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel4.setText("院系介绍:");
		jLabel4.setBounds(new Rectangle(80, 130, 90, 20));
		// jComboBox2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		// jComboBox2.setEditable(true);
		// jComboBox2.setBounds(new Rectangle(180, 130, 180, 25));
		contentPane.add(jLabel1);
		contentPane.add(jOptionPane1);
		contentPane.add(jLabel3);
		contentPane.add(jTextField1);
		contentPane.add(jLabel4);
		contentPane.add(jTextField2);

		contentPane.add(jButton2);
		contentPane.add(jButton1);
		// jComboBox1.addItem("请选择学院");
		// 选择学院
		// try {
		// 	ResultSet rs = conn.query("select * from tb_depart");
		// 	while (rs.next()) {
		// 		String xibu = rs.getString("departName");
		// 		jComboBox1.addItem(xibu);
		// 	}

		// } catch (Exception error) {
		// 	System.out.println("error is: " + error);
		// }
		// jComboBox2.setEnabled(false);

	}

	// 选择专业
	// public void zhuanye() {
	// 	jComboBox2.removeAllItems();
	// 	jComboBox2.addItem("请选择专业");

	// 	try {

	// 		ResultSet rs = conn.query(
	// 				"select * from tb_spec where departName='" + String.valueOf(jComboBox1.getSelectedItem()) + "' ");
	// 		while (rs.next()) {
	// 			String zhy = rs.getString("specName");
	// 			jComboBox2.addItem(zhy);
	// 		}

	// 	} catch (Exception error) {
	// 		System.out.println("error is: " + error);
	// 	}

	// }

	// 存下选定学院到xueyuan
	// public void xueYuan() {
	// 	String sel = String.valueOf(jComboBox1.getSelectedItem());

	// 	try {

	// 		ResultSet rs = conn.query("select * from tb_depart where departName='" + sel + "'");
	// 		while (rs.next()) {
	// 			xueyuan = rs.getString("departName");

	// 		}

	// 	} catch (Exception error) {
	// 		System.out.println("error is: " + error);
	// 	}

	// }

	// 存下选定专业到zhuanye
	public void zhuanYe() {
		// String sel = String.valueOf(jComboBox2.getSelectedItem());

		// try {

		// 	ResultSet rs = conn.query("select * from tb_spec where specName='" + sel + "'");
		// 	while (rs.next()) {
		// 		zhuanye = rs.getString("specName");

		// 	}

		// } catch (Exception error) {
		// 	System.out.println("error is: " + error);
		// }

	}

	// 退出
	public void jButton2_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	// 添加院系
	public void jButton1_actionPerformed(ActionEvent e) {
		// 异常判断
		String cname = jTextField1.getText();
		String remark = jTextField2.getText();
		if (cname.equals("")) {
			jOptionPane1.showMessageDialog(this, "请输入院系名称", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		} else {
			try {
				// 先针对输入的院系名称进行查询，如果已存在同名院系，则提示院系已存在
				boolean departExist = false;

				ResultSet rs_departExist = conn.query("select id from department where cname = '"
						+ cname + "' and isDel = false");
				while (rs_departExist.next()) {
					departExist = true;
				}


				if (departExist) {
					jOptionPane1.showMessageDialog(this, "该院系已存在，请重新输入", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					conn.getUpdate("insert into department (cname, remark) values ('"
							+ cname + "','"
							+ remark + "')");
					jOptionPane1.showMessageDialog(this, "恭喜您院系录入成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				}

			} catch (Exception error) {
				System.out.println("error is: " + error);;
			}
		}
	}

	// 选择触发
	public void jComboBox1_actionPerformed(ActionEvent e) {
		// zhuanye();
		// jComboBox2.setEnabled(true);
	}
}

class AddDepartFrame_jComboBox1_actionAdapter implements ActionListener {
	private DepartAddFrame adaptee;

	AddDepartFrame_jComboBox1_actionAdapter(DepartAddFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jComboBox1_actionPerformed(e);
	}
}

class AddDepartFrame_jButton1_actionAdapter implements ActionListener {
	private DepartAddFrame adaptee;

	AddDepartFrame_jButton1_actionAdapter(DepartAddFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {

		adaptee.jButton1_actionPerformed(e);
	}
}

class AddDepartFrame_jButton2_actionAdapter implements ActionListener {
	private DepartAddFrame adaptee;

	AddDepartFrame_jButton2_actionAdapter(DepartAddFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton2_actionPerformed(e);
	}
}

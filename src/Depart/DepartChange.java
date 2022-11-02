package Depart;

import java.sql.ResultSet;
import java.awt.*;

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

public class DepartChange extends JFrame {
	JPanel contentPane;
	JLabel jLabel1 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	JOptionPane jOptionPane1 = new JOptionPane();
	JTextField jTextField1 = new JTextField();
	JTextField jTextField2 = new JTextField();
	dbConn conn = new dbConn();
	JLabel jLabel4 = new JLabel();
	String find;
	String zhuanye, xueyuan;

	public DepartChange(String find) {
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
		setTitle("院系修改");
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		jLabel1.setText("院  系  信  息  修  改");
		jLabel1.setBounds(new Rectangle(136, 20, 212, 25));

		jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel3.setText("院系名称:");
		jLabel3.setBounds(new Rectangle(80, 100, 90, 20));

		jButton1.setBounds(new Rectangle(102, 223, 96, 29));
		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton1.setText("确    定");
		jButton1.addActionListener(new DepartU_jButton1_actionAdapter(this));

		jButton2.setBounds(new Rectangle(265, 221, 96, 31));
		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton2.setToolTipText("");
		jButton2.setText("退    出");
		jButton2.addActionListener(new DepartU_jButton2_actionAdapter(this));

		jOptionPane1.setBounds(new Rectangle(106, 258, 262, 90));
		jOptionPane1.setLayout(null);

		jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel4.setText("院系介绍");
		jLabel4.setBounds(new Rectangle(80, 150, 90, 20));

		jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField1.setText("");
		jTextField1.setBounds(new Rectangle(180, 80, 180, 25));

		jTextField2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField2.setText("");
		jTextField2.setBounds(new Rectangle(180, 130, 180, 25));

		contentPane.add(jLabel1);
		contentPane.add(jOptionPane1);

		contentPane.add(jLabel3);
		contentPane.add(jTextField1);
		contentPane.add(jLabel4);
		contentPane.add(jTextField2);

		contentPane.add(jButton2);
		contentPane.add(jButton1);
		// 选择学院
		try {
			ResultSet rs = conn.query("select id, cname, remark from department where isDel = false and id = '" + find + "'");
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

	// // 存下选定学院到xueyuan
	// public void xueYuan() {
	// 	// String sel = String.valueOf(jComboBox1.getSelectedItem());
	// 	try {
	// 		ResultSet rs = conn.query("select * from tb_depart where departName='" + sel + "'");
	// 		while (rs.next()) {
	// 			xueyuan = rs.getString("departName");
	// 		}
	// 		rs.close();
	// 	} catch (Exception error) {
	// 		System.out.println("error is: " + error);
	// 	}
	// }

	// // 存下选定专业到zhuanye
	// public void zhuanYe() {
	// 	// String sel = String.valueOf(jComboBox2.getSelectedItem());
	// 	try {
	// 		ResultSet rs = conn.query("select * from tb_spec where specName='" + sel + "'");
	// 		while (rs.next()) {
	// 			zhuanye = rs.getString("specName");
	// 		}
	// 		rs.close();
	// 	} catch (Exception error) {
	// 		System.out.println("error is: " + error);
	// 	}
	// }

	// 退出
	public void jButton2_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	// 修改
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
						+ cname + "' and id != '" + find + "' and isDel = false");
				while (rs_departExist.next()) {
					departExist = true;
				}

				if (departExist) {
					jOptionPane1.showMessageDialog(this, "该院系已存在，请重新输入", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					conn.getUpdate("update department set cname = '" + cname + "', remark = '" + remark + "' where id = '" + find + "'");
					jOptionPane1.showMessageDialog(this, "院系信息修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				}

			} catch (Exception error) {
				System.out.println("error is: " + error);;
			}
		}
	}


	// 选定行后在修改界面显示选定默认值
	// public void xianshi() {
	// 	if (find == null)
	// 		return;
	// 	else {
	// 		jComboBox2.setEnabled(true);
	// 		try {
	// 			ResultSet rs = conn.query("select * from tb_spec where specId='" + Integer.valueOf(find) + "'");
	// 			while (rs.next()) {
	// 				jComboBox1.setSelectedItem(String.valueOf(rs.getString("departName")));
	// 				jComboBox2.setSelectedItem(String.valueOf(rs.getString("specName")));
	// 			}
	// 			rs.close();
	// 		} catch (Exception error) {
	// 			System.out.println("error is: " + error);
	// 		}
	// 	}
	// }
}

class DepartU_jComboBox1_actionAdapter implements ActionListener {

	private DepartChange adaptee;

	DepartU_jComboBox1_actionAdapter(DepartChange adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		// adaptee.jComboBox1_actionPerformed(e);
	}
}

class DepartU_jButton1_actionAdapter implements ActionListener {

	private DepartChange adaptee;

	DepartU_jButton1_actionAdapter(DepartChange adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton1_actionPerformed(e);
	}
}

class DepartU_jButton2_actionAdapter implements ActionListener {

	private DepartChange adaptee;

	DepartU_jButton2_actionAdapter(DepartChange adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton2_actionPerformed(e);
	}
}
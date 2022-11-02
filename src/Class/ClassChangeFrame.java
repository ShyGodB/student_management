package Class;

import java.awt.Dimension;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.util.*;

import db.dbConn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Title: 班级修改
 * 
 */

public class ClassChangeFrame extends JFrame {
	JPanel contentPane;
	JLabel jLabel1 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JLabel jLabel5 = new JLabel();
	JTextField jTextField1 = new JTextField();
	JTextField jTextField2 = new JTextField();
	JComboBox jComboBox1 = new JComboBox();
	JComboBox jComboBox2 = new JComboBox();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	JOptionPane jOptionPane1 = new JOptionPane();
	dbConn conn = new dbConn();

	ArrayList<String> departmentIdList = new ArrayList<String>();
	ArrayList<String> specialtyIdList = new ArrayList<String>();

	String find;

	public ClassChangeFrame(String find) {
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
		setTitle("班级信息修改");
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		jLabel1.setText("班  级  信  息  录  入");
		jLabel1.setBounds(new Rectangle(136, 20, 212, 25));
		jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));

		jLabel2.setText("班级名称:");
		jLabel2.setBounds(new Rectangle(80, 80, 90, 20));

		jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel3.setText("所属学院:");
		jLabel3.setBounds(new Rectangle(80, 130, 90, 20));

		jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel4.setText("所属专业：");
		jLabel4.setBounds(new Rectangle(80, 180, 90, 20));

		jLabel5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel5.setText("班级介绍");
		jLabel5.setBounds(new Rectangle(80, 230, 90, 20));

		jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField1.setBounds(new Rectangle(180, 80, 180, 25));

		jComboBox1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jComboBox1.setEditable(true);
		jComboBox1.setBounds(new Rectangle(180, 130, 180, 25));
		jComboBox1.addActionListener(new ClassChangeFrame_jComboBox1_actionAdapter(this));
		
		jComboBox2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jComboBox2.setEditable(true);
		jComboBox2.setBounds(new Rectangle(180, 180, 180, 25));

		jTextField2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField2.setBounds(new Rectangle(180, 230, 180, 25));

		jButton1.setBounds(new Rectangle(102, 270, 96, 29));
		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton1.setText("提    交");
		jButton1.addActionListener(new ClassChangeFrame_jButton1_actionAdapter(this));
		
		jButton2.setBounds(new Rectangle(265, 270, 96, 31));
		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton2.setToolTipText("");
		jButton2.setText("退    出");
		jButton2.addActionListener(new ClassChangeFrame_jButton2_actionAdapter(this));

		jOptionPane1.setBounds(new Rectangle(106, 258, 262, 90));
		jOptionPane1.setLayout(null);

		contentPane.add(jLabel1);
		contentPane.add(jOptionPane1);

		contentPane.add(jLabel2);
		contentPane.add(jTextField1);
		contentPane.add(jLabel3);
		contentPane.add(jComboBox1);
		contentPane.add(jLabel4);
		contentPane.add(jComboBox2);
		contentPane.add(jLabel5);
		contentPane.add(jTextField2);

		contentPane.add(jButton2);
		contentPane.add(jButton1);
		jComboBox1.addItem("请选择学院");
		jComboBox2.addItem("请选择专业");
		// 获取院系数据
		try {
			ResultSet rs = conn.query("select id, cname from department where isDel = false order by id asc");
			while (rs.next()) {
				String xibu = rs.getString("cname");
				String id = rs.getString("id");
				departmentIdList.add(id);
				jComboBox1.addItem(xibu);
			}
		} catch (Exception error) {
			System.out.println("error is: " + error);
		}
		jComboBox2.setEnabled(false);
		
		// 获取选择的班级信息
		try {
			ResultSet rs = conn.query("select id, departmentId, specialtyId, cname, remark from class where isDel = false and id = '" + find + "'");
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

	// 选择专业
	public void zhuanye() {
		jComboBox2.removeAllItems();
		jComboBox2.addItem("请选择专业");
		int index = jComboBox1.getSelectedIndex();
		if (index > 0) {
			String departmentId = departmentIdList.get(index - 1);
			try {
				ResultSet rs = conn.query("select id, departmentId, cname from specialty where departmentId = '" + departmentId + "' and isDel = false order by id asc");
				while (rs.next()) {
					String zhy = rs.getString("cname");
					String id = rs.getString("id");
					specialtyIdList.add(id);
					jComboBox2.addItem(zhy);
				}
			} catch (Exception error) {
				System.out.println("error is: " + error);
			}
		}
	}

	// 退出
	public void jButton2_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	// 提交
	public void jButton1_actionPerformed(ActionEvent e) {
		// 异常判断
		int index1 = jComboBox1.getSelectedIndex();
		int index2 = jComboBox2.getSelectedIndex();
		String cname = jTextField1.getText();
		String remark = jTextField2.getText();
		if (cname.equals("")) {
			jOptionPane1.showMessageDialog(this, "请输入班级名称", "提示", JOptionPane.INFORMATION_MESSAGE, null);
			return;
		}
		if (index1 == 0) {
			jOptionPane1.showMessageDialog(this, "请选择所属院系", "提示", JOptionPane.INFORMATION_MESSAGE, null);
			return;
		}
		if (index2 == 0) {
			jOptionPane1.showMessageDialog(this, "请选择所属专业", "提示", JOptionPane.INFORMATION_MESSAGE, null);
			return;
		}
		String departmentId = departmentIdList.get(index1 - 1);
		String specialtyId = specialtyIdList.get(index2 - 1);
		
		try {
			boolean ifClassExist = false;
			ResultSet rs = conn.query("select id from class where cname = '" + cname + "' and departmentId = '" + departmentId + "' and specialtyId = '" + specialtyId + "' and isDel = false");
			while (rs.next()) {
				ifClassExist = true;
			}
			if (ifClassExist) {
				jOptionPane1.showMessageDialog(this, "选定的院系、专业中已存在同名班级", "提示", JOptionPane.INFORMATION_MESSAGE, null);
			} else {
				conn.getUpdate("update class set departmentId = '" + departmentId + "', specialtyId = '" + specialtyId +  "', cname = '" + cname + "', remark = '" + remark + "' where id = '" + find + "'");
				jOptionPane1.showMessageDialog(this, "恭喜您班级信息修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
			}

		} catch (Exception error) {
			System.out.println("error is: " + error);;
		}
	}

	// 选择学院后触发专业可选
	public void jComboBox1_actionPerformed(ActionEvent e) {
		zhuanye();
		jComboBox2.setEnabled(true);
	}
}

class ClassChangeFrame_jComboBox1_actionAdapter implements ActionListener {
	private ClassChangeFrame adaptee;

	ClassChangeFrame_jComboBox1_actionAdapter(ClassChangeFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jComboBox1_actionPerformed(e);
	}
}

class ClassChangeFrame_jButton1_actionAdapter implements ActionListener {
	private ClassChangeFrame adaptee;

	ClassChangeFrame_jButton1_actionAdapter(ClassChangeFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {

		adaptee.jButton1_actionPerformed(e);
	}
}

class ClassChangeFrame_jButton2_actionAdapter implements ActionListener {
	private ClassChangeFrame adaptee;

	ClassChangeFrame_jButton2_actionAdapter(ClassChangeFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton2_actionPerformed(e);
	}
}

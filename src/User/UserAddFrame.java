package User;

import java.awt.Dimension;
import java.awt.*;

import db.dbConn;

import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Title: 用户添加 
 * Description: 用户添加模块，只对管理员显示。
 * 
 */

public class UserAddFrame extends JFrame {
	JPanel contentPane;
	String level;
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JTextField jTextField1 = new JTextField();
	JPasswordField jPasswordField1 = new JPasswordField();
	JPasswordField jPasswordField2 = new JPasswordField();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	JOptionPane jOptionPane1 = new JOptionPane();
	dbConn conn = new dbConn();

	public UserAddFrame() {
		try {
			init();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void init() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		setSize(new Dimension(469, 315));
		setTitle("添加用户");
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 23));
		jLabel1.setText("添  加   用   户");
		jLabel1.setBounds(new Rectangle(134, 12, 198, 27));
		jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel2.setText("用户名:");
		jLabel2.setBounds(new Rectangle(90, 66, 68, 22));
		jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel3.setText("密    码:");
		jLabel3.setBounds(new Rectangle(90, 118, 75, 27));
		jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel4.setText("确认密码:");
		jLabel4.setBounds(new Rectangle(84, 167, 89, 29));
		jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
		jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField1.setBounds(new Rectangle(191, 65, 155, 24));
		jPasswordField1.setBorder(BorderFactory.createLoweredBevelBorder());
		jPasswordField1.setBounds(new Rectangle(191, 119, 155, 24));
		jPasswordField2.setBorder(BorderFactory.createLoweredBevelBorder());
		jPasswordField2.setText("");
		jPasswordField2.setBounds(new Rectangle(191, 169, 155, 24));
		jButton1.setBounds(new Rectangle(99, 225, 89, 25));
		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
		jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton1.setText("提    交");
		jButton1.addActionListener(new AddAdminFrame_jButton1_actionAdapter(this));
		jButton2.setBounds(new Rectangle(267, 225, 89, 25));
		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
		jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton2.setText("退    出");
		jButton2.addActionListener(new AddAdminFrame_jButton2_actionAdapter(this));
		jOptionPane1.setBounds(new Rectangle(0, 233, 262, 90));
		jOptionPane1.setLayout(null);
		contentPane.add(jLabel2);
		contentPane.add(jLabel3);
		contentPane.add(jLabel4);
		contentPane.add(jTextField1);
		contentPane.add(jPasswordField2);
		contentPane.add(jPasswordField1);
		contentPane.add(jButton1);
		contentPane.add(jButton2);
		contentPane.add(jLabel1);
		contentPane.add(jOptionPane1);
	}

	// 退出
	public void jButton2_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	// 添加
	public void jButton1_actionPerformed(ActionEvent e) {
		// 异常判断
		boolean isTrue = false;
		String username = jTextField1.getText().trim();
		// String cpassword = jPasswordField1.getText();
		String cpassword = new String(this.jPasswordField1.getPassword());
		String cpassword2 = new String(this.jPasswordField2.getPassword());
		if (username.length() == 0 || username.length() > 20) {
			JOptionPane.showMessageDialog(this, "用户名为空且长度不可大于20", "提 示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		// 先限制密码长度小可小于6
		if (cpassword.length() < 6 || cpassword2.length() < 6) {
			JOptionPane.showMessageDialog(this, "密码长度不可小于6", "提 示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		if (cpassword2.trim().equals(cpassword.trim())) {
			isTrue = true; // 密码确认成功
		} else {
			JOptionPane.showMessageDialog(this, "密码确认错误!", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		}
		if (isTrue) {
			try {
				// 查询用户名是否存在
				boolean ifExist = false;
				ResultSet rs = conn.query("select id from adminUser where isDel = false and username = '" + username + "'");
				while (rs.next()) {
					ifExist = true;
				}
				if (ifExist) {
					JOptionPane.showMessageDialog(this, "该用户名已经存在!", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					// 不存在则添加
					conn.getUpdate("insert into adminuser (username, cpassword, clevel) values ('"
							+ username + "','" + cpassword + "', 2)");
					JOptionPane.showMessageDialog(this, "恭喜您添加用户成功!", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				}
				rs.close();
			} catch (Exception error) {
				System.out.println("error is: " + error);;
			}
		}
	}
}

class AddAdminFrame_jButton1_actionAdapter implements ActionListener {
	private UserAddFrame adaptee;

	AddAdminFrame_jButton1_actionAdapter(UserAddFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton1_actionPerformed(e);
	}
}

class AddAdminFrame_jButton2_actionAdapter implements ActionListener {
	private UserAddFrame adaptee;

	AddAdminFrame_jButton2_actionAdapter(UserAddFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton2_actionPerformed(e);
	}
}

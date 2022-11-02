package User;

import java.awt.Dimension;
import java.awt.*;

import db.dbConn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/**
 * 
 * Title: �û������޸� 
 * Description: �û������޸�ģ�飬����Ա����ͨ�û����ɼ�������Ա���޸������û����룬��ͨ�û�ֻ���޸ı��û����롣
 * 
 
 */

public class UserPasswordFrame extends JFrame {
	JPanel contentPane;
	String adminId;
	String adminLevel;
	String adminUsername;
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JLabel jLabel5 = new JLabel();
	JTextField jTextField1 = new JTextField();
	JPasswordField jPasswordField1 = new JPasswordField();
	JPasswordField jPasswordField2 = new JPasswordField();
	JPasswordField jPasswordField3 = new JPasswordField();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	JOptionPane jOptionPane1 = new JOptionPane();
	dbConn conn = new dbConn();

	public UserPasswordFrame(String adminId, String adminLevel, String adminUsername) {
		this.adminId = adminId;
		this.adminLevel = adminLevel;
		this.adminUsername = adminUsername;
		try {
			init();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void init() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		setSize(new Dimension(444, 340));
		setTitle("�û������޸�");
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		jLabel1.setText("��  ��  ��  ��  ��  ��");
		jLabel1.setBounds(new Rectangle(112, 15, 204, 24));
		jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel2.setText("�û���:");
		jLabel2.setBounds(new Rectangle(80, 72, 74, 23));
		jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel3.setText("������:");
		jLabel3.setBounds(new Rectangle(80, 115, 74, 23));
		jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel4.setText("������:");
		jLabel4.setBounds(new Rectangle(80, 159, 74, 23));
		jLabel5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel5.setText("ȷ��������:");
		jLabel5.setBounds(new Rectangle(72, 203, 101, 23));
		jTextField1.setEnabled(false);
		jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
		jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField1.setBounds(new Rectangle(175, 72, 159, 23));
		jPasswordField1.setBorder(BorderFactory.createLoweredBevelBorder());
		jPasswordField1.setBounds(new Rectangle(175, 114, 160, 25));
		jPasswordField2.setBorder(BorderFactory.createLoweredBevelBorder());
		jPasswordField2.setText("");
		jPasswordField2.setBounds(new Rectangle(175, 158, 160, 25));
		jPasswordField3.setBorder(BorderFactory.createLoweredBevelBorder());
		jPasswordField3.setText("");
		jPasswordField3.setBounds(new Rectangle(175, 202, 160, 25));
		jButton1.setBounds(new Rectangle(87, 254, 86, 26));
		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
		jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton1.setText("��    ��");
		jButton1.addActionListener(new MAPasswordFrameFrame_jButton1_actionAdapter(this));
		jButton2.setBounds(new Rectangle(253, 254, 86, 26));
		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
		jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton2.setText("��    ��");
		jButton2.addActionListener(new MAPasswordFrameFrame_jButton2_actionAdapter(this));
		jOptionPane1.setBounds(new Rectangle(-8, 84, 262, 90));
		jOptionPane1.setLayout(null);
		contentPane.add(jLabel1);
		contentPane.add(jLabel2);
		contentPane.add(jLabel3);
		contentPane.add(jLabel4);
		contentPane.add(jLabel5);
		contentPane.add(jPasswordField3);
		contentPane.add(jPasswordField1);
		contentPane.add(jPasswordField2);
		contentPane.add(jButton2);
		contentPane.add(jButton1);
		contentPane.add(jTextField1);
		contentPane.add(jOptionPane1);
		this.jTextField1.setText(adminUsername);

		if (adminLevel.equals("1")) {
			jTextField1.setEnabled(true);
		}
	}

	// �˳�
	public void jButton2_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	// �޸�����
	public void jButton1_actionPerformed(ActionEvent e) {
		boolean isOldPasswordTrue = false; // ��д�ľ������Ƿ���ȷ
		boolean isEmpty = true; // �Ƿ�δ��ѯ������
		String username = jTextField1.getText().trim();
		String cpassword = new String(this.jPasswordField1.getPassword());
		String cpassword2 = new String(this.jPasswordField2.getPassword());
		String cpassword3 = new String(this.jPasswordField3.getPassword());
		// ���������볤��С��С��6
		if (cpassword.length() < 6 || cpassword2.length() < 6) {
			JOptionPane.showMessageDialog(this, "���볤�Ȳ���С��6", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		// �쳣�ж�
		if (username == null || username.length() == 0 || username.length() > 20) {
			JOptionPane.showMessageDialog(this, "�û�������Ϊ������󳤶�Ϊ20���ַ�!", "��  ʾ", JOptionPane.INFORMATION_MESSAGE, null);
		} else if (cpassword2.trim().compareTo(cpassword3.trim()) != 0) {
			JOptionPane.showMessageDialog(this, "������ȷ�ϴ���!", "��  ʾ", JOptionPane.INFORMATION_MESSAGE, null);
		} else {
			try {
				// ��ԭ���������֤
				ResultSet rs = conn.query("select id, username, cpassword from adminuser where id = '" + adminId + "'");
				while (rs.next()) {
					isOldPasswordTrue = true;
					isEmpty = false;
				}
				if (isEmpty) {
					JOptionPane.showMessageDialog(this, "�û������������, δ��ѯ���û���Ϣ!", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
				}
				if (isOldPasswordTrue) {
					// �޸�����
					conn.getUpdate("update adminuser set cpassword = '" + cpassword2.trim()
							+ "' where id = '" + adminId + "'");
					JOptionPane.showMessageDialog(this, "��ϲ���޸ĳɹ�!", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
					jTextField1.setText("");
					jPasswordField1.setText("");
					jPasswordField2.setText("");
					jPasswordField3.setText("");
				}
				rs.close();
			} catch (java.sql.SQLException error) {
				System.out.println("error is: " + error);;
			}
		}
	}
}

class MAPasswordFrameFrame_jButton1_actionAdapter implements ActionListener {
	private UserPasswordFrame adaptee;

	MAPasswordFrameFrame_jButton1_actionAdapter(UserPasswordFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton1_actionPerformed(e);
	}
}

class MAPasswordFrameFrame_jButton2_actionAdapter implements ActionListener {
	private UserPasswordFrame adaptee;

	MAPasswordFrameFrame_jButton2_actionAdapter(UserPasswordFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton2_actionPerformed(e);
	}
}

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
 * Title: Ժϵ�޸� 
 * Description: Ժϵ��Ϣ�޸�ģ��
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
		setTitle("רҵ�޸�");
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		jLabel1.setText("ר  ҵ  ��  Ϣ  ¼  ��");
		jLabel1.setBounds(new Rectangle(136, 20, 212, 25));

		jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel2.setText("רҵ����:");
		jLabel2.setBounds(new Rectangle(80, 80, 90, 20));

		jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel3.setText("����Ժϵ:");
		jLabel3.setBounds(new Rectangle(80, 130, 90, 20));

		jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel4.setText("רҵ����:");
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
		jButton1.setText("ȷ    ��");
		jButton1.addActionListener(new SpecialtyChangeFrame_jButton1_actionAdapter(this));

		jButton2.setBounds(new Rectangle(265, 221, 96, 31));
		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton2.setToolTipText("");
		jButton2.setText("��    ��");
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

		jComboBox1.addItem("��ѡ��Ժϵ");
		// ��ȡԺϵ����
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

		// ��ȡѡ���רҵ��Ϣ
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

	// �˳�
	public void jButton2_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	// �޸�
	public void jButton1_actionPerformed(ActionEvent e) {
		// �쳣�ж�
		int index = jComboBox1.getSelectedIndex();
		String cname = jTextField1.getText();
		String remark = jTextField2.getText();
		if (cname.equals("")) {
			jOptionPane1.showMessageDialog(this, "������רҵ����", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
			return;
		}
		if (index == 0) {
			jOptionPane1.showMessageDialog(this, "��ѡ������Ժϵ", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
			return;
		}
		String departmentId = idList.get(index - 1);
		try {
			// ����������Ժϵ���ƽ��в�ѯ������Ѵ���ͬ��Ժϵ������ʾԺϵ�Ѵ���
			boolean specialtyExist = false;

			ResultSet rs_departExist = conn.query("select id from specialty where cname = '"
					+ cname + "' and isDel = false and departmentId = '" + departmentId + "'");
			while (rs_departExist.next()) {
				specialtyExist = true;
			}

			if (specialtyExist) {
				jOptionPane1.showMessageDialog(this, "ѡ����Ժϵ���Ѵ���ͬ��רҵ", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
			} else {
				conn.getUpdate("update specialty set departmentId = '"
				 		+ departmentId + "', cname = '"
						+ cname + "', remark = '"
						+ remark + "' where id = '" + find + "'");
				jOptionPane1.showMessageDialog(this, "¼��רҵ�ɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
			}

		} catch (Exception error) {
			System.out.println("error is: " + error);;
		}
	}

	// ѡ�񴥷�
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

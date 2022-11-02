package Specialty;

import java.sql.ResultSet;
import java.awt.*;
import javax.swing.*;
import java.util.*;

import db.dbConn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Title: רҵ��Ϣ¼�� 
 * Description: רҵ��Ϣ¼��ģ�顣
 */

public class SpecialtyAddFrame extends JFrame {
	JPanel contentPane;
	JLabel jLabel1 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	JOptionPane jOptionPane1 = new JOptionPane();
	JTextField jTextField1 = new JTextField(); // רҵ����
	JComboBox jComboBox1 = new JComboBox();
	JTextField jTextField2 = new JTextField(); // רҵ����
	String xueyuan, zhuanye;
	dbConn conn = new dbConn();
	ArrayList<String> idList = new ArrayList<String>();

	public SpecialtyAddFrame() {
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
		setTitle("רҵ¼��");
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
		jComboBox1.addActionListener(new AddDepartFrame_jComboBox1_actionAdapter(this));

		jTextField2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField2.setText("");
		jTextField2.setBounds(new Rectangle(180, 180, 180, 25));
		
		jButton1.setBounds(new Rectangle(102, 230, 96, 29));
		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton1.setText("��    ��");
		jButton1.addActionListener(new AddDepartFrame_jButton1_actionAdapter(this));
		
		jButton2.setBounds(new Rectangle(265, 230, 96, 31));
		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton2.setToolTipText("");
		jButton2.setText("��    ��");
		jButton2.addActionListener(new AddDepartFrame_jButton2_actionAdapter(this));

		jOptionPane1.setBounds(new Rectangle(106, 258, 262, 90));
		jOptionPane1.setLayout(null);
		
		// jComboBox2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		// jComboBox2.setEditable(true);
		// jComboBox2.setBounds(new Rectangle(180, 130, 180, 25));
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
		// ѡ��ѧԺ
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
		// jComboBox2.setEnabled(false);

	}

	// �˳�
	public void jButton2_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	// ���Ժϵ
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
				conn.getUpdate("insert into specialty (departmentId, cname, remark) values ('"
				 		+ departmentId + "','"
						+ cname + "','"
						+ remark + "')");
				jOptionPane1.showMessageDialog(this, "¼��רҵ�ɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
			}

		} catch (Exception error) {
			System.out.println("error is: " + error);;
		}
	}

	// ѡ�񴥷�
	public void jComboBox1_actionPerformed(ActionEvent e) {
		// zhuanye();
		// jComboBox2.setEnabled(true);
	}
}

class AddDepartFrame_jComboBox1_actionAdapter implements ActionListener {
	private SpecialtyAddFrame adaptee;

	AddDepartFrame_jComboBox1_actionAdapter(SpecialtyAddFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jComboBox1_actionPerformed(e);
	}
}

class AddDepartFrame_jButton1_actionAdapter implements ActionListener {
	private SpecialtyAddFrame adaptee;

	AddDepartFrame_jButton1_actionAdapter(SpecialtyAddFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {

		adaptee.jButton1_actionPerformed(e);
	}
}

class AddDepartFrame_jButton2_actionAdapter implements ActionListener {
	private SpecialtyAddFrame adaptee;

	AddDepartFrame_jButton2_actionAdapter(SpecialtyAddFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton2_actionPerformed(e);
	}
}

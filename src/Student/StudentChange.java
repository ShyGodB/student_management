package Student;

import java.awt.*;
import javax.swing.*;

import db.dbConn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class StudentChange extends JFrame {
	JPanel contentPane;
	JLabel windowTitle = new JLabel(); // 窗口标题
	// JLabel jLabel1 = new JLabel(); // 学号
	JLabel jLabel2 = new JLabel(); // 姓名
	JLabel jLabel3 = new JLabel(); // 性别
	JLabel jLabel4 = new JLabel(); // 出生日期
	JLabel jLabel5 = new JLabel(); // 国籍
	JLabel jLabel6 = new JLabel(); // 民族
	JLabel jLabel7 = new JLabel(); // 学历
	JLabel jLabel8 = new JLabel(); // 家庭住址
	JLabel jLabel9 = new JLabel(); // 手机号
	JLabel jLabel10 = new JLabel(); // 家庭电话
	JLabel jLabel11 = new JLabel(); // 校园电话

	JTextField jTextField1 = new JTextField();
	JTextField jTextField2 = new JTextField();
	JTextField jTextField3 = new JTextField();
	JTextField jTextField4 = new JTextField();
	JTextField jTextField5 = new JTextField();
	JTextField jTextField6 = new JTextField();
	JTextField jTextField7 = new JTextField();
	JTextField jTextField8 = new JTextField();
	JTextField jTextField9 = new JTextField();
	JTextField jTextField10 = new JTextField();
	JTextField jTextField11 = new JTextField();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	dbConn con = new dbConn();
	Object clas[] = {};
	dbConn conn = new dbConn();
	JOptionPane loginFeedback = new JOptionPane();
	String studentId, realName, gender, birthday, nationality, nation, education, homeAddress, phone, homeTelephone, schoolTelephone;
	int findId;

	public StudentChange(int findId) {
		this.findId = findId;
		try {
			init();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void init() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		setSize(new Dimension(800, 600));
		setTitle("学生信息录入");

		windowTitle.setFont(new java.awt.Font("Dialog", Font.BOLD, 23));
		windowTitle.setForeground(SystemColor.inactiveCaptionText);
		windowTitle.setText("学  生  信  息  录  入");
		windowTitle.setBounds(new Rectangle(196, 16, 232, 25));

		// jLabel1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		// jLabel1.setText("学号:");
		// jLabel1.setBounds(new Rectangle(50, 74, 90, 22));

		jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel2.setText("姓名:");
		jLabel2.setBounds(new Rectangle(50, 74, 90, 22));

		jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel3.setText("性别:");
		jLabel3.setBounds(new Rectangle(50, 124, 90, 22));

		jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel4.setText("出生日期:");
		jLabel4.setBounds(new Rectangle(50, 174, 90, 22));

		jLabel5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel5.setText("国籍:");
		jLabel5.setBounds(new Rectangle(50, 224, 90, 22));

		jLabel6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel6.setText("民族:");
		jLabel6.setBounds(new Rectangle(50, 274, 90, 22));

		jLabel7.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel7.setText("学历:");
		jLabel7.setBounds(new Rectangle(450, 74, 90, 22));

		jLabel8.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel8.setText("家庭住址:");
		jLabel8.setBounds(new Rectangle(450, 124, 90, 22));

		jLabel9.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel9.setText("手机号:");
		jLabel9.setBounds(new Rectangle(450, 174, 90, 22));

		jLabel10.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel10.setText("家庭电话:");
		jLabel10.setBounds(new Rectangle(450, 224, 90, 22));

		jLabel11.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel11.setText("校园电话:");
		jLabel11.setBounds(new Rectangle(450, 274, 90, 22));

		// jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		// jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
		// jTextField1.setText("");
		// jTextField1.setBounds(new Rectangle(150, 74, 195, 23));

		jTextField2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField2.setText("");
		jTextField2.setBounds(new Rectangle(150, 74, 195, 23));

		jTextField3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField3.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField3.setText("");
		jTextField3.setBounds(new Rectangle(150, 124, 195, 23));

		jTextField4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField4.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField4.setText("");
		jTextField4.setBounds(new Rectangle(150, 174, 195, 23));

		jTextField5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField5.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField5.setText("");
		jTextField5.setBounds(new Rectangle(150, 224, 195, 23));

		jTextField6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField6.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField6.setText("");
		jTextField6.setBounds(new Rectangle(150, 274, 195, 23));

		jTextField7.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField7.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField7.setText("");
		jTextField7.setBounds(new Rectangle(550, 74, 195, 23));

		jTextField8.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField8.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField8.setText("");
		jTextField8.setBounds(new Rectangle(550, 124, 195, 23));

		jTextField9.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField9.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField9.setText("");
		jTextField9.setBounds(new Rectangle(550, 174, 195, 23));

		jTextField10.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField10.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField10.setText("");
		jTextField10.setBounds(new Rectangle(550, 224, 195, 23));

		jTextField11.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField11.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField11.setText("");
		jTextField11.setBounds(new Rectangle(550, 274, 195, 23));

		jButton1.setText("提  交");
		jButton1.setBounds(new Rectangle(147, 324, 90, 27));
		jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton1.addActionListener(new ChangeStudent(this));
		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));

		jButton2.setText("退  出");
		jButton2.setBounds(new Rectangle(375, 324, 90, 27));
		jButton2.addActionListener(new QuitStudentChange(this));
		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));

		loginFeedback.setBounds(new Rectangle(28, 263, 262, 90));
		loginFeedback.setLayout(null);
		contentPane.add(windowTitle);
		// contentPane.add(jComboBox5);
		// contentPane.add(jComboBox4);
		// contentPane.add(jLabel1);
		contentPane.add(jLabel2);
		contentPane.add(jLabel3);
		contentPane.add(jLabel4);
		contentPane.add(jLabel5);
		contentPane.add(jLabel6);
		contentPane.add(jLabel7);
		contentPane.add(jLabel8);
		contentPane.add(jLabel9);
		contentPane.add(jLabel10);
		contentPane.add(jLabel11);
		// contentPane.add(jTextField1);
		contentPane.add(jTextField2);
		contentPane.add(jTextField3);
		contentPane.add(jTextField4);
		contentPane.add(jTextField5);
		contentPane.add(jTextField6);
		contentPane.add(jTextField7);
		contentPane.add(jTextField8);
		contentPane.add(jTextField9);
		contentPane.add(jTextField10);
		contentPane.add(jTextField11);
		contentPane.add(jButton1);
		contentPane.add(jButton2);
		contentPane.add(loginFeedback);

		// 获取学生数据
		try {
			ResultSet rs = conn.query("select * from student where id = '" + findId + "'");
			while (rs.next()) {
				studentId = rs.getString("id"); 
				realName = rs.getString("realName"); 
				gender = rs.getString("gender"); 
				birthday = rs.getString("birthday"); 
				nationality = rs.getString("nationality"); 
				nation = rs.getString("nation"); 
				education = rs.getString("education"); 
				homeAddress = rs.getString("homeAddress"); 
				phone = rs.getString("phone"); 
				homeTelephone = rs.getString("homeTelephone"); 
				schoolTelephone = rs.getString("schoolTelephone");
				jTextField1.setText(studentId);
				jTextField2.setText(realName);
				jTextField3.setText(gender);
				jTextField4.setText(birthday);
				jTextField5.setText(nationality);
				jTextField6.setText(nation);
				jTextField7.setText(education);
				jTextField8.setText(homeAddress);
				jTextField9.setText(phone);
				jTextField10.setText(homeTelephone);
				jTextField11.setText(schoolTelephone);
				jTextField1.setEnabled(false);
			}
			rs.close();
		} catch (Exception error) {
			System.out.println("error is: " + error);
		}
	}

	// 学生信息录入
	public void changeStudent(ActionEvent e) {
		// 参数校验
		realName = jTextField2.getText();
		gender = jTextField3.getText();
		birthday = jTextField4.getText();
		nationality = jTextField5.getText();
		nation = jTextField6.getText();
		education = jTextField7.getText();
		homeAddress = jTextField8.getText();
		phone = jTextField9.getText();
		homeTelephone = jTextField10.getText();
		schoolTelephone = jTextField11.getText();
		Boolean isError = false;
		int phoneLength = phone.length();
		if (realName.equals("")) {
			JOptionPane.showMessageDialog(this, "请输入学生姓名", "提 示", JOptionPane.INFORMATION_MESSAGE);
			isError = true;
		} else if (!gender.equals("男") && !gender.equals("女")) {
			JOptionPane.showMessageDialog(this, "学生性别应为男或女", "提 示", JOptionPane.INFORMATION_MESSAGE);
			isError = true;
		} else if (birthday.equals("")) {
			JOptionPane.showMessageDialog(this, "生日不可为空", "提 示", JOptionPane.INFORMATION_MESSAGE);
			isError = true;
		} else if (nationality.equals("")) {
			JOptionPane.showMessageDialog(this, "国籍不可为空", "提 示", JOptionPane.INFORMATION_MESSAGE);
			isError = true;
		} else if (nation.equals("")) {
			JOptionPane.showMessageDialog(this, "民族不可为空", "提 示", JOptionPane.INFORMATION_MESSAGE);
			isError = true;
		} else if (education.equals("")) {
			JOptionPane.showMessageDialog(this, "请输入学历", "提 示", JOptionPane.INFORMATION_MESSAGE);
			isError = true;
		} else if (homeAddress.equals("")) {
			JOptionPane.showMessageDialog(this, "请输入家庭住址", "提 示", JOptionPane.INFORMATION_MESSAGE);
			isError = true;
		} else if (phoneLength != 11) {
			JOptionPane.showMessageDialog(this, "请输入11位手机号", "提 示", JOptionPane.INFORMATION_MESSAGE);
			isError = true;
		}
		if (isError) {
			return;
		}
		changeStudentInfo();
	}

	// 退出
	public void quitCurrentWindow(ActionEvent e) {
		this.dispose();
	}

	// 学生信息修改
	public void changeStudentInfo() {
		try {
			int res = conn.getUpdate("update student set realName = '" + realName + "', gender = '" + gender + "', birthday = '" + birthday + "', nationality = '" + nationality + "', nation = '" + nation + "', education = '" +education + "', homeAddress = '" + homeAddress + "', phone = '" + phone + "', homeTelephone = '" + homeTelephone + "', schoolTelephone = '" + schoolTelephone + "' where id = '" + findId + "'");
			if (res > 0) {
				JOptionPane.showMessageDialog(this, "学生信息修改成功！", "提 示", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "学生信息修改失败！", "提 示", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception error) {
			System.out.println("error is: " + error.getMessage());
		}
	}
}

class QuitStudentChange implements ActionListener {
	private StudentChange adaptee;

	QuitStudentChange(StudentChange adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.quitCurrentWindow(e);
	}
}

class ChangeStudent implements ActionListener {
	private StudentChange adaptee;

	ChangeStudent(StudentChange adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.changeStudent(e);
	}
}

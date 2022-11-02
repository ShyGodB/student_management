package stuManager;

import java.awt.Dimension;
import java.awt.*;

import User.UserAddFrame;
import db.dbConn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;

/**
 * 
 * Title: ��¼ģ�� 
 * Description: ��¼ģ��Ŀ��ӻ�ʵ��
 *
 */

public class LoginFrame extends JFrame {
	JPanel contentPane; // ���ݻ���
	// ���ñ���ͼƬ
	URL url = getClass().getResource("/img/bg.jpg");
	ImageIcon loginBg = new ImageIcon(url);

	JLabel systemTitle = new JLabel(); // ϵͳ����1
	JLabel systemTitle2 = new JLabel(); // ϵͳ����2
	JLabel welcomeTitle = new JLabel(); // ��ӭ����1
	JLabel welcomeTitle2 = new JLabel(); // ��ӭ����2
	JLabel userName = new JLabel(); // �û���
	JTextField userNameValue = new JTextField(); // �û���������ȡֵ
	JLabel password = new JLabel(); // ����
	JPasswordField passwordValue = new JPasswordField(); // ����������ֵ
	JButton loginButton = new JButton(); // ��¼��ť
	JButton quitButton = new JButton(); // �˳���ť
	JOptionPane hintDialog = new JOptionPane(); // ѡ���
	JPanel bgBox = new JPanel(); // ��������
	JLabel jLabelBg = new JLabel(); 
	CardLayout cardLayout1 = new CardLayout();
	static String adminId, adminLevel, adminUsername;
	dbConn conn = new dbConn();

	public LoginFrame() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			init();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void init() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		setSize(new Dimension(504, 344));
		setTitle("��ӭ��¼ѧ������ϵͳ");
		welcomeTitle.setFont(new java.awt.Font("Dialog", Font.BOLD, 25));
		welcomeTitle.setToolTipText("");
		welcomeTitle.setText("��  ӭ  ��  ¼  ѧ  ��");
		welcomeTitle.setBounds(new Rectangle(128, 19, 246, 30));
		systemTitle.setFont(new java.awt.Font("Dialog", Font.BOLD, 25));
		systemTitle.setToolTipText("");
		systemTitle.setText("��  ��  ϵ  ͳ");
		systemTitle.setBounds(new Rectangle(173, 61, 164, 29));
		userName.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		userName.setText("�û���:");
		userName.setBounds(new Rectangle(95, 123, 77, 25));
		password.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		password.setText("��    ��:");
		password.setBounds(new Rectangle(95, 172, 74, 21));
		userNameValue.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
		userNameValue.setBorder(BorderFactory.createLoweredBevelBorder());
		userNameValue.setBounds(new Rectangle(188, 122, 212, 27));
		loginButton.setBounds(new Rectangle(124, 243, 93, 30));
		loginButton.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
		loginButton.setBorder(BorderFactory.createRaisedBevelBorder());
		loginButton.setText("��  ¼");
		loginButton.addActionListener(new Login(this));
		quitButton.setBounds(new Rectangle(286, 243, 93, 30));
		quitButton.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
		quitButton.setBorder(BorderFactory.createRaisedBevelBorder());
		quitButton.setText("ע  ��");
		quitButton.addActionListener(new SignUp(this));
		systemTitle2.setFont(new java.awt.Font("Dialog", Font.BOLD, 25));
		systemTitle2.setForeground(Color.blue);
		systemTitle2.setToolTipText("");
		systemTitle2.setText("��  ��  ϵ  ͳ");
		systemTitle2.setBounds(new Rectangle(173, 62, 164, 29));
		welcomeTitle2.setFont(new java.awt.Font("Dialog", Font.BOLD, 25));
		welcomeTitle2.setForeground(Color.blue);
		welcomeTitle2.setToolTipText("");
		welcomeTitle2.setText("��  ӭ  ��  ¼  ѧ  ��");
		welcomeTitle2.setBounds(new Rectangle(128, 20, 246, 30));
		hintDialog.setBounds(new Rectangle(28, 263, 262, 90));
		hintDialog.setLayout(null);
		passwordValue.setBorder(BorderFactory.createLoweredBevelBorder());
		passwordValue.setBounds(new Rectangle(187, 168, 213, 26));
		bgBox.setBounds(new Rectangle(-15, 0, 529, 375));
		bgBox.setLayout(cardLayout1);
		jLabelBg.setIcon(loginBg);
		contentPane.add(userNameValue);
		contentPane.add(userName);
		contentPane.add(password);
		contentPane.add(loginButton);
		contentPane.add(quitButton);
		contentPane.add(welcomeTitle);
		contentPane.add(systemTitle);
		contentPane.add(welcomeTitle2);
		contentPane.add(systemTitle2);
		contentPane.add(passwordValue);
		contentPane.add(bgBox);
		bgBox.add(jLabelBg, "jLabel8");
		contentPane.add(hintDialog);
	}
	
	// ����û�ģ��
	public void signUp(ActionEvent e) {
		UserAddFrame userAddFrame = new UserAddFrame();
		userAddFrame.setLocation(400, 200);
		userAddFrame.setSize(469, 315);
		userAddFrame.setVisible(true);
		userAddFrame.setResizable(false);
		userAddFrame.validate();
	}

	// ��¼
	public void login(ActionEvent e) {
		boolean isLoginFailed = true; // �Ƿ��¼ʧ��
		// �ж��û�������û����Ƿ�Ϊ�գ����򵯳�����
		if (userNameValue.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "�û�������Ϊ�գ�", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
		} else {
			try {
				// �ɰ�getText�����ѷ�����������ò���this.passwordValue.getText()
				String pwd = new String(this.passwordValue.getPassword());
				if (pwd.length() < 6) {
					JOptionPane.showMessageDialog(this, "���볤�Ȳ���С��6", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				// ��ѯ�û���Ϣ
				ResultSet rs = conn.query("select id, username, cpassword, clevel from adminuser where username='" + userNameValue.getText() + "' and cpassword = '" + pwd + "'" );
				while (rs.next()) {
					adminId = rs.getString("id");
					adminLevel = rs.getString("clevel");
					adminUsername = userNameValue.getText().trim();
					MainFrame mainFrame = new MainFrame(adminId, adminLevel, adminUsername); // ��������
					mainFrame.setLocation(200, 40);
					mainFrame.setSize(900, 650);
					mainFrame.setVisible(true);
					mainFrame.setResizable(false);
					mainFrame.validate();
					this.dispose();
					isLoginFailed = false; // ��¼�ɹ�
				}
				if (isLoginFailed) {
					JOptionPane.showMessageDialog(this, "�û������������", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
				}
				rs.close();
			} catch (Exception error) {
				System.out.println("error is: " + error);;
			}
		}
	}
	
}

class Login implements ActionListener {
	private LoginFrame loginFrame;

	Login(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}

	public void actionPerformed(ActionEvent e) {
		loginFrame.login(e);
	}
}

class SignUp implements ActionListener {
	private LoginFrame loginFrame;

	SignUp(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}

	public void actionPerformed(ActionEvent e) {
		loginFrame.signUp(e);
	}
}

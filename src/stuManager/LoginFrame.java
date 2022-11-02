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
 * Title: 登录模块 
 * Description: 登录模块的可视化实现
 *
 */

public class LoginFrame extends JFrame {
	JPanel contentPane; // 内容画布
	// 设置背景图片
	URL url = getClass().getResource("/img/bg.jpg");
	ImageIcon loginBg = new ImageIcon(url);

	JLabel systemTitle = new JLabel(); // 系统标题1
	JLabel systemTitle2 = new JLabel(); // 系统标题2
	JLabel welcomeTitle = new JLabel(); // 欢迎标题1
	JLabel welcomeTitle2 = new JLabel(); // 欢迎标题2
	JLabel userName = new JLabel(); // 用户命
	JTextField userNameValue = new JTextField(); // 用户名输入框的取值
	JLabel password = new JLabel(); // 密码
	JPasswordField passwordValue = new JPasswordField(); // 密码输入框的值
	JButton loginButton = new JButton(); // 登录按钮
	JButton quitButton = new JButton(); // 退出按钮
	JOptionPane hintDialog = new JOptionPane(); // 选择框
	JPanel bgBox = new JPanel(); // 背景盒子
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
		setTitle("欢迎登录学生管理系统");
		welcomeTitle.setFont(new java.awt.Font("Dialog", Font.BOLD, 25));
		welcomeTitle.setToolTipText("");
		welcomeTitle.setText("欢  迎  登  录  学  生");
		welcomeTitle.setBounds(new Rectangle(128, 19, 246, 30));
		systemTitle.setFont(new java.awt.Font("Dialog", Font.BOLD, 25));
		systemTitle.setToolTipText("");
		systemTitle.setText("管  理  系  统");
		systemTitle.setBounds(new Rectangle(173, 61, 164, 29));
		userName.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		userName.setText("用户名:");
		userName.setBounds(new Rectangle(95, 123, 77, 25));
		password.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		password.setText("密    码:");
		password.setBounds(new Rectangle(95, 172, 74, 21));
		userNameValue.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
		userNameValue.setBorder(BorderFactory.createLoweredBevelBorder());
		userNameValue.setBounds(new Rectangle(188, 122, 212, 27));
		loginButton.setBounds(new Rectangle(124, 243, 93, 30));
		loginButton.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
		loginButton.setBorder(BorderFactory.createRaisedBevelBorder());
		loginButton.setText("登  录");
		loginButton.addActionListener(new Login(this));
		quitButton.setBounds(new Rectangle(286, 243, 93, 30));
		quitButton.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
		quitButton.setBorder(BorderFactory.createRaisedBevelBorder());
		quitButton.setText("注  册");
		quitButton.addActionListener(new SignUp(this));
		systemTitle2.setFont(new java.awt.Font("Dialog", Font.BOLD, 25));
		systemTitle2.setForeground(Color.blue);
		systemTitle2.setToolTipText("");
		systemTitle2.setText("管  理  系  统");
		systemTitle2.setBounds(new Rectangle(173, 62, 164, 29));
		welcomeTitle2.setFont(new java.awt.Font("Dialog", Font.BOLD, 25));
		welcomeTitle2.setForeground(Color.blue);
		welcomeTitle2.setToolTipText("");
		welcomeTitle2.setText("欢  迎  登  录  学  生");
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
	
	// 添加用户模块
	public void signUp(ActionEvent e) {
		UserAddFrame userAddFrame = new UserAddFrame();
		userAddFrame.setLocation(400, 200);
		userAddFrame.setSize(469, 315);
		userAddFrame.setVisible(true);
		userAddFrame.setResizable(false);
		userAddFrame.validate();
	}

	// 登录
	public void login(ActionEvent e) {
		boolean isLoginFailed = true; // 是否登录失败
		// 判断用户输入的用户命是否为空，是则弹出警告
		if (userNameValue.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "用户名不能为空！", "提 示", JOptionPane.INFORMATION_MESSAGE);
		} else {
			try {
				// 旧版getText方法已废弃，所以最好不用this.passwordValue.getText()
				String pwd = new String(this.passwordValue.getPassword());
				if (pwd.length() < 6) {
					JOptionPane.showMessageDialog(this, "密码长度不可小于6", "提 示", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				// 查询用户信息
				ResultSet rs = conn.query("select id, username, cpassword, clevel from adminuser where username='" + userNameValue.getText() + "' and cpassword = '" + pwd + "'" );
				while (rs.next()) {
					adminId = rs.getString("id");
					adminLevel = rs.getString("clevel");
					adminUsername = userNameValue.getText().trim();
					MainFrame mainFrame = new MainFrame(adminId, adminLevel, adminUsername); // 打开主界面
					mainFrame.setLocation(200, 40);
					mainFrame.setSize(900, 650);
					mainFrame.setVisible(true);
					mainFrame.setResizable(false);
					mainFrame.validate();
					this.dispose();
					isLoginFailed = false; // 登录成功
				}
				if (isLoginFailed) {
					JOptionPane.showMessageDialog(this, "用户名或密码错误！", "提 示", JOptionPane.INFORMATION_MESSAGE);
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

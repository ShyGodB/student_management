package db;

import java.sql.*;

/**
 * 
 * Title: 数据库连接 
 * Description: 数据库连接模块
 */

public class dbConn {
	public dbConn() {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Statement conn() {
		try {
			// 加载驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 数据库名称，管理员账号、密码
			String url = "jdbc:mysql://localhost:3306/studentinfo?useUnicode=true&characterEncoding=UTF-8";
			String user = "root";
			String pwd = "1234qwer";

			// 连接
			Connection con = null;
			con = DriverManager.getConnection(url, user, pwd);
			Statement stat = con.createStatement();
			return stat;
		} catch (ClassNotFoundException ex) {
			return null;
		} catch (SQLException ex1) {
			return null;
		}
	}

	// 查询数据库
	public ResultSet query(String sql) {
		try {
			Statement stat = conn();
			ResultSet rs = stat.executeQuery(sql);
			return rs;
		} catch (SQLException ex) {
			System.err.println("------------" + ex.getMessage());
			return null;
		}
	}

	// 更新数据库
	public int getUpdate(String sql) {
		try {
			Statement stat = conn();
			int i = stat.executeUpdate(sql);
			return i;
		} catch (Exception ex) {
			System.out.println(">>>>>>>>" + ex.getMessage());
			return -1;
		}
	}

	private void init() throws Exception {
		conn();
	}

}

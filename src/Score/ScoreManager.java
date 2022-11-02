package Score;

import com.borland.jbcl.layout.*;

import db.dbConn;

import javax.swing.*;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.awt.*;

/**
 * 
 * Title: 成绩管理 
 * Description: 成绩管理模块，可以查询成绩，是成绩修改和删除的入口。
 * 
 */

public class ScoreManager extends JFrame {
	XYLayout xYLayout1 = new XYLayout();
	JLabel jLabel1 = new JLabel();
	JPanel jPanel1 = new JPanel();
	JScrollPane jScrollPane1 = new JScrollPane();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	XYLayout xYLayout2 = new XYLayout();
	JTable jTable1 = new JTable();
	ButtonGroup buttonGroup1 = new ButtonGroup();
	JButton jButton_average = new JButton();
	JButton jButton_all_sort = new JButton();
	dbConn conn = new dbConn();
	String sql;
	Object[][] arrData = {};
	int tableType = 1;
	String[] arrField1 = { "成绩编号", "学生学号", "姓名", "课程", "分数" };
	String[] arrField2 = { "学生学号", "姓名", "班级", "总成绩", "班级排名" };
	String[] arrField3 = { "课程编号", "课程名称", "平均成绩", "及格率" };

	DefaultTableModel model = new DefaultTableModel();
	int intRow;
	static int find;
	int i_sortDesc = 0, i_print = 0;
	JOptionPane jOptionPane1 = new JOptionPane();
	JButton jButton6 = new JButton();

	public ScoreManager() {
		try {
			init();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void init() throws Exception {
		getContentPane().setLayout(xYLayout1);
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		jLabel1.setText("成  绩  管  理");
		jScrollPane1.setBorder(BorderFactory.createEtchedBorder());
		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jButton1.setText("修   改");
		jButton1.addActionListener(new ScoreF_jButton1_actionAdapter(this));
		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jButton2.setText("返   回");
		jButton2.addActionListener(new ScoreF_jButton2_actionAdapter(this));
		jPanel1.setLayout(xYLayout2);
		jPanel1.setBorder(BorderFactory.createEtchedBorder());
		xYLayout1.setWidth(550);
		xYLayout1.setHeight(560);
		
		jButton_all_sort.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton_all_sort.setText("总成绩及排名");
		jButton_all_sort.addActionListener(new ScoreManager_jButton_failure_actionAdapter(this));
		jButton_average.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton_average.setText("平成绩与及格率");
		jButton_average.addActionListener(new ScoreManager_jButton_average_actionAdapter(this));
		this.setTitle("成绩管理");
		jButton6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jButton6.setText("删   除");
		jButton6.addActionListener(new ScoreF_jButton6_actionAdapter(this));
		jScrollPane1.getViewport().add(jTable1);
		this.getContentPane().add(jLabel1, new XYConstraints(208, 13, 135, 43));
		// this.getContentPane().add(jButton1, new XYConstraints(74, 470, 100, -1));
		this.getContentPane().add(jButton2, new XYConstraints(374, 470, 100, -1));
		this.getContentPane().add(jPanel1, new XYConstraints(17, 68, 515, 63));
		this.getContentPane().add(jScrollPane1, new XYConstraints(18, 150, 515, 300));
		this.getContentPane().add(jButton6, new XYConstraints(224, 470, 100, -1));
		jPanel1.add(jButton_average, new XYConstraints(300, 15, 180, 25));
		jPanel1.add(jButton_all_sort, new XYConstraints(30, 15, 180, 25));

		// 设置默认查找所有成绩
		sql = "SELECT s.id, s.studentId, stu.realName, c.cname as courseName, s.score FROM score AS s LEFT JOIN student AS stu ON s.studentId = stu.id LEFT JOIN course AS c ON s.courseId = c.id WHERE s.isDel = false; ";
		// 更新
		UpdateRecord();
	}

	// 查询并更新表格
	public void UpdateRecord() {
		Object[][] arrTmp = {}; // 设定表格的字段
		Vector vec = new Vector(1, 1);
		if (tableType == 1) {
			model = new DefaultTableModel(arrTmp, arrField1);
			jTable1 = new JTable(model);
			jScrollPane1.getViewport().add(jTable1, null);
			try {
				ResultSet rs = conn.query(sql);
				while (rs.next()) {
					vec = new Vector();
					vec.add(String.valueOf(rs.getString("id")));
					vec.add(String.valueOf(rs.getString("studentId")));
					vec.add(String.valueOf(rs.getString("realName")));
					vec.add(String.valueOf(rs.getString("courseName")));
					vec.add(String.valueOf(rs.getString("score")));
					model.addRow(vec);
				}
				rs.close();
			} catch (Exception error) {
				System.out.println("error is: " + error);;
			}
		} else if (tableType == 2) {
			String sql1 = "select count(id) as count from student where isDel = false";
			String sql2 = "select id, realName, classId from student where isDel = false";
			String sql3 = "select count(id) as count from class where isDel = false";
			String sql4 = "select id, cname from class where isDel = false";
			String sql5 = "select count(id) as count from score where isDel = false";
			String sql6 = "select id, studentId, courseId, score from score where isDel = false";
			model = new DefaultTableModel(arrTmp, arrField2);
			jTable1 = new JTable(model);
			jScrollPane1.getViewport().add(jTable1, null);
			try {
				ResultSet rs1 = conn.query(sql1);
				ResultSet rs2 = conn.query(sql2);
				ResultSet rs3 = conn.query(sql3);
				ResultSet rs4 = conn.query(sql4);
				ResultSet rs5 = conn.query(sql5);
				ResultSet rs6 = conn.query(sql6);
				int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0;

				while (rs1.next()) {
					count1 = Integer.parseInt(rs1.getString("count"));
				}
				if (count1 > 0) {
					Student[] studentArray = new Student[count1];
					while (rs2.next()) {
						studentArray[count2] = new Student(rs2.getString("id"), rs2.getString("classId"), rs2.getString("realName"), "0");
						count2++;
					}

					while (rs3.next()) {
						count3 = Integer.parseInt(rs3.getString("count"));
					}
					BanjiClass[] banjiclassArray = new BanjiClass[count3];
					while (rs4.next()) {
						banjiclassArray[count4] = new BanjiClass(rs4.getString("id"), rs4.getString("cname"), count1);
						count4++;
					}

					while (rs5.next()) {
						count5 = Integer.parseInt(rs5.getString("count"));
					}
					Score[] scoreArray = new Score[count5];
					while (rs6.next()) {
						scoreArray[count6] = new Score(rs6.getString("id"), rs6.getString("studentId"), Double.parseDouble(rs6.getString("score")), rs6.getString("courseId"));
						count6++;
					}
					// 求出班级名和总成绩
					for (int i = 0; i < count1; i++) {
						Student stu = studentArray[i];
						String studentId = stu.getStudentId();
						String classId = stu.getClassId();
						String className = "";

						for (int j = 0; j < count3; j++) {
							BanjiClass classInfo = banjiclassArray[j];
							String classInfoId = classInfo.getId();
							if (classId.equals(classInfoId)) {
								className = classInfo.getName();
							}
						}
						stu.setClassName(className); // 班级名称

						Double totalScore = 0.0; // 总成绩
						for (int k = 0; k < count5; k++) {
							Score score = scoreArray[k];
							String scoreStudentId = score.getStudentId();
							if (studentId.equals(scoreStudentId)) {
								totalScore += score.getScore();
							}
						}
						stu.setTotalScore(String.valueOf(totalScore)); // 总成绩有了

						vec = new Vector();
						vec.add(stu.getStudentId());
						vec.add(stu.getRealName());
						vec.add(stu.getClassName());
						vec.add(stu.getTotalScore());
						vec.add(i + 1);
						model.addRow(vec);
					}
					// 求班级排名，需求出各个学生在对应班级的排名，所以先得根据班级分组
				}

				rs1.close();
				rs2.close();
				rs3.close();
				rs4.close();
				rs5.close();
				rs6.close();
			} catch (Exception error) {
				System.out.println("error is: " + error);;
			}
		} else if (tableType == 3) {
			model = new DefaultTableModel(arrTmp, arrField3);
			jTable1 = new JTable(model);
			jScrollPane1.getViewport().add(jTable1, null);
			try {
				String sql41 = "select count(id) as count from course where isDel = false";
				String sql51 = "select id, cname from course where isDel = false";
				String sql61 = "select count(id) as count from score where isDel = false";
				String sql71 = "select id, studentId, score, courseId from score where isDel = false";
				ResultSet rs41 = conn.query(sql41);
				ResultSet rs51 = conn.query(sql51);
				ResultSet rs61 = conn.query(sql61);
				ResultSet rs71 = conn.query(sql71);
				int count4 = 0, count5 = 0, count6 = 0, count7 = 0;//#region = 0;
				while (rs41.next()) {
					count4 = Integer.parseInt(rs41.getString("count"));
				}
				if (count4 > 0) {
					Course[] courseArray = new Course[count4];
					while (rs51.next()) {
						courseArray[count5] = new Course(rs51.getString("id"), rs51.getString("cname"));
						count5++;
					}
					while (rs61.next()) {
						count6 = Integer.parseInt(rs61.getString("count"));
					}
					Score[] scoreArray = new Score[count6];
					while (rs71.next()) {
						scoreArray[count7] = new Score(rs71.getString("id"), rs71.getString("studentId"), Double.parseDouble(rs71.getString("score")), rs71.getString("courseId"));
						count7++;
					}

					for (int i = 0; i < count4; i++) {
						Course course = courseArray[i];
						String id = course.getId(); // 课程的课程编号
						Double totalScore = 0.0; // 总成绩
						int scoreCount = 0; // 成绩数量
						int paseCount = 0; // 及格的数量
						for (int j = 0; j < count6; j++) {
							Score score = scoreArray[j];
							String courseId = score.getCourseId(); // 成绩的课程编号
							// 如果两者相等
							if (courseId.equals(id)) {
								Double sscore = score.getScore();
								totalScore = totalScore + sscore;
								scoreCount = scoreCount + 1;
								if (sscore > 60) {
									paseCount = paseCount + 1;
								}
							}
						}
						course.setAverageScore(String.valueOf(scoreCount == 0 ? 0 : totalScore / scoreCount));
						course.setPassRate(scoreCount == 0 ? "0" : getPercent(paseCount * 1.0, scoreCount * 1.0));
						vec = new Vector();
						vec.add(course.getId());
						vec.add(course.getName());
						vec.add(course.getAverageScore());
						vec.add(course.getPassRate());
						model.addRow(vec);
					}
				}
				rs41.close();
				rs51.close();
				rs61.close();
				rs71.close();
			} catch (Exception error) {
				System.out.println("error is: " + error);;
			}
		}
		jScrollPane1.getHorizontalScrollBar();
			jTable1.setGridColor(Color.blue);
			jTable1.setDragEnabled(true);
			jTable1.setSelectionForeground(Color.red);
			jTable1.setSelectionBackground(Color.green);
			jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTable1.setRowSelectionAllowed(true);
			jTable1.setShowVerticalLines(true);
	}

	public static String getPercent(Double x, Double y) {
        double d1 = x * 1.0;
        double d2 = y * 1.0;
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        // 设置保留几位小数，这里设置的是保留两位小数
        percentInstance.setMinimumFractionDigits(2);
        return percentInstance.format(d1 / d2);
    }

	// 选定所选的行
	public void getM() {
		intRow = jTable1.getSelectedRow();
		if (intRow == -1) {
			JOptionPane.showMessageDialog(this, "请选择要修改的课程!", "提示", JOptionPane.INFORMATION_MESSAGE, null);
			return;
		}
		try {
			find = Integer.parseInt(model.getValueAt(intRow, 0).toString());
			System.out.println(find);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 退出
	public void jButton2_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	// 平均分
	public void jButton_average_actionPerformed(ActionEvent e) {
		tableType = 3;
		UpdateRecord();
	}

	// 计算总成绩及排名
	public void jButton_failure_actionPerformed(ActionEvent e) {
		tableType = 2;
		UpdateRecord();
	}

	// 修改课程
	public void jButton1_actionPerformed(ActionEvent e) {
		getM();
		if (find > 0) {
			ScoreChange siadd = new ScoreChange(find);
			siadd.setLocation(400, 200);
			siadd.setSize(465, 310);
			siadd.setVisible(true);
			siadd.setResizable(false);
			siadd.validate();
			this.dispose();
		}
	}

	// 删除课程
	public void jButton6_actionPerformed(ActionEvent e) {
		getM();
		if (intRow == -1) {
			JOptionPane.showMessageDialog(this, "请选择要删除的课程!", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		}
		try {
			conn.getUpdate("delete from tb_score where scoreId='" + Integer.valueOf(find) + "'");
			JOptionPane.showMessageDialog(this, "删除成功!", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		} catch (Exception error) {
			System.out.println("error is: " + error.getMessage());
		}
		UpdateRecord();
	}
}

class ScoreF_jButton1_actionAdapter implements ActionListener {
	private ScoreManager adaptee;

	ScoreF_jButton1_actionAdapter(ScoreManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton1_actionPerformed(e);
	}
}

class ScoreF_jButton6_actionAdapter implements ActionListener {
	private ScoreManager adaptee;

	ScoreF_jButton6_actionAdapter(ScoreManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton6_actionPerformed(e);
	}
}

class ScoreF_jButton2_actionAdapter implements ActionListener {
	private ScoreManager adaptee;

	ScoreF_jButton2_actionAdapter(ScoreManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton2_actionPerformed(e);
	}
}

class ScoreManager_jButton_average_actionAdapter implements ActionListener {
	private ScoreManager adaptee;

	ScoreManager_jButton_average_actionAdapter(ScoreManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_average_actionPerformed(e);
	}
}

class ScoreManager_jButton_failure_actionAdapter implements ActionListener {
	private ScoreManager adaptee;

	ScoreManager_jButton_failure_actionAdapter(ScoreManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_failure_actionPerformed(e);
	}
}

class Student {
	private String studentId;
	private String realName;
	private String classId;
	private String className;
	private String totalScore;
	private String classSort;

	public Student(String studentId, String classId, String realName, String totalScore) {
		this.studentId = studentId;
		this.classId = classId;
		this.realName = realName;
		this.totalScore = totalScore;
	}

	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getClassId() {
		return this.classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTotalScore() {
		return this.totalScore;
	}

	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}

	public String getClassSort() {
		return this.classSort;
	}

	public void setClassSort(String classSort) {
		this.classSort = classSort;
	}
	
}

// 班级类
class BanjiClass {
	private String id;
	private String name;
	private Student[] students;

	public BanjiClass(String id, String name, int count) {
		this.id = id;
		this.name = name;
		this.students = new Student[count];
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStudents(Student[] students) {
		this.students = students;
	}

	public void initStudents(int count) {
		this.students = new Student[count];
	}

	public Student[] addStudent(String studentId, String classId, String realName, String totalScore) {
		Student[] newArray = new Student[this.students.length + 1];
		for (int i = 0; i < this.students.length; i++) {
			newArray[i] = this.students[i];
		}
		newArray[this.students.length] = new Student(studentId, classId, realName, totalScore);
		return newArray;
	}

	// 快速排序
	public void quickSort(Student[] students, int start, int end) {
	    if (start < end) {  
			Student stu1 = students[start];
	        Double base = Double.valueOf(stu1.getTotalScore()); // 选定的基准值（第一个学生的总成绩数值作为基准值）   
	        Student temp; // 记录临时中间值   
	        int i = start, j = end;
	        do {   
	            while ((Double.valueOf(students[i].getTotalScore()) < base) && (i < end)) {
					i++;
				}
	            while ((Double.valueOf(students[j].getTotalScore()) > base) && (j > start)) {
					j--;
				}
	            if (i <= j) {   
	                temp = students[i];   
	                students[i] = students[j];   
	                students[j] = temp;   
	                i++;   
	                j--;   
	            }   
	        } while (i <= j);   
	        if (start < j) {
				quickSort(students, start, j); 
			}  
	        if (end > i) {
				quickSort(students, i, end);
			}   
	    }   
	}

	public void sortStudent() {
		int count = this.students.length;
		Student[] newArray = this.students;
		if (count > 0) {
			quickSort(newArray, 0, count - 1);
			for (int i = 0; i < count; i++) {
				Student stu = newArray[i];
				stu.setClassSort(String.valueOf(i + 1));
			}
		}
	}
}


class Course {
	private String id;
	private String name;
	private String averageScore;
	private String paseRate;

	public Course(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAverageScore() {
		return this.averageScore;
	}

	public void setAverageScore(String averageScore) {
		this.averageScore = averageScore;
	}

	public String getPassRate() {
		return this.paseRate;
	}

	public void setPassRate(String paseRate) {
		this.paseRate = paseRate;
	}
}

// select id, score, courseId from score where isDel = false

class Score {
	private String id;
	private String studentId;
	private Double score;
	private String courseId;

	public Score(String id, String studentId, Double score, String courseId) {
		this.id = id;
		this.studentId = studentId;
		this.score = score;
		this.courseId = courseId;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Double getScore() {
		return this.score;
	}

	public void setName(Double score) {
		this.score = score;
	}

	public String getCourseId() {
		return this.courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
}
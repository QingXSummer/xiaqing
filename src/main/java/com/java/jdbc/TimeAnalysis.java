package com.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class TimeAnalysis {
	
	public static String DRIVER ="com.mysql.jdbc.Driver";
	
	public static String URL ="jdbc:mysql://192.168.0.14:3306/dataasset?useUnicode=true&characterEncoding=UTF8";
	
	public static String userName="root";
	
	public static String pwd="mysql";
	
	public static String sql = "INSERT INTO bdam_asset_timelanalysis ( OID, ORIGINAL_ID, START_TIME, END_TIME, TASK_ID, SCORE, ASSESS_TIME ) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
	
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static Connection connection;
	
	public static Random random = new Random();
	
	public static List<String> taskIds = new ArrayList<>();
	
	public static List<String> tableIds = new ArrayList<>();
	private static void getTaskid(Connection connection){
		try {
			ResultSet set = connection.createStatement().executeQuery("SELECT ORIGINAL_OID FROM bdam_task ");
			while (set.next()) {
				String id = set.getString("ORIGINAL_OID");
				taskIds.add(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getTaskIdByRandom() {
		int size = taskIds.size();
		int index = random.nextInt(size);
		return taskIds.get(index);
	}
	
	
	private static void getTableIds(Connection connection){
		try {
			ResultSet set = connection.createStatement().executeQuery("SELECT ORIGINAL_OID FROM bdam_entity_table");
			while (set.next()) {
				String id = set.getString("ORIGINAL_OID");
				tableIds.add(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  static TimeAnalysisObj produce(String originaloid,String assessTime){
		TimeAnalysisObj obj = new TimeAnalysisObj();
		obj.setOid(UUID.randomUUID().toString());
		obj.setOriginal_oid(originaloid);
		obj.setStartTime(format.format(new Date()));
		long delay = random.nextInt(5000);
		obj.setEndTime(format.format(new Date(System.currentTimeMillis()+delay*1000)));
		obj.setTaskId(getTaskIdByRandom());
		obj.setAssessTime(assessTime);
		if (delay <3600) {
			obj.setScore("100");						
		}else {
			obj.setScore(random.nextInt(100)+"");			
		}
		return obj;
	}
	
	
	
	public static void main(String[] args) {

		String assessTime = "2018-05-24";

		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, userName, pwd);
			PreparedStatement statement = connection.prepareStatement(sql);
			PreparedStatement statement2 = connection.prepareStatement("insert into bdam_asset_dim_score values(?,?,'m6','1',?,?)");
			
			getTaskid(connection);
			getTableIds(connection);
			
//			List<String> ceshi = new ArrayList<>();
//			ceshi.add("748450");
			List<String>tableID = tableIds;
			
			for (String tableId : tableID) {
				int time = random.nextInt(1);
				if (time == 2 || time == 3) {					
					for (int i = 0; i<time;i++ ) {
						TimeAnalysisObj obj = produce(tableId, assessTime);
						statement.setString(0, obj.getOid());
						statement.setString(1, obj.getOriginal_oid());
						statement.setString(2, obj.getStartTime());
						statement.setString(3, obj.getEndTime());
						statement.setString(4, obj.getTaskId());
						statement.setString(5, obj.getScore());
						statement.executeUpdate();
						
						statement2.setString(0, obj.getOid());
						statement2.setString(1, obj.getOriginal_oid());
						statement2.setString(2, obj.getScore());
						statement2.setString(3, obj.getAssessTime());
						statement2.executeUpdate();
					}
				}else {
					TimeAnalysisObj obj = produce(tableId, assessTime);
					statement.setString(1, obj.getOid());
					statement.setString(2, obj.getOriginal_oid());
					statement.setString(3, obj.getStartTime());
					statement.setString(4, obj.getEndTime());
					statement.setString(5, obj.getTaskId());
					statement.setString(6, obj.getScore());
					statement.setString(7, obj.getAssessTime());
					statement.executeUpdate();
					
					statement2.setString(1, obj.getOid());
					statement2.setString(2, obj.getOriginal_oid());
					statement2.setString(3, obj.getScore());
					statement2.setString(4, obj.getAssessTime());
					statement2.executeUpdate();
				}
				
			
				
				

			}
			
			
				
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}

class TimeAnalysisObj {
	private String oid;
	private String original_oid;
	private String startTime;
	private String endTime;
	private String taskId;
	private String score;
	private String AssessTime;
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOriginal_oid() {
		return original_oid;
	}
	public void setOriginal_oid(String original_oid) {
		this.original_oid = original_oid;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getAssessTime() {
		return AssessTime;
	}
	public void setAssessTime(String assessTime) {
		AssessTime = assessTime;
	}
	
	
}


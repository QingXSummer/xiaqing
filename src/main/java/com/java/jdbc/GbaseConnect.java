package com.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GbaseConnect {
	
	public static String DRIVER ="com.gbase.jdbc.Driver";
	
	public static String URL ="jdbc:gbase://192.168.0.17:5258/dataman?useUnicode=true&characterEncoding=UTF8";
	
	public static String userName="model";
	
	public static String pwd="model";
	
	public static String insertSql = "select TABLE_NAME,TABLE_COMMENT  from information_schema.tables "
			+ "where TABLE_SCHEMA='modeldb3' AND TABLE_TYPE='BASE TABLE';";
	
	public static String sql ="insert into bdam_storage_data_info VALUES"
			+ "(?,?,?,'modeldb3','GBMN','30','1000000','500','1','10','10000','',"
			+ "'2018-05-29','2017-03-01','2017-03-04')";
	
	public static Connection connection;
	
	
	public static Map<String, String> produce(String tableName,String comment){
		Map<String, String> map = new HashMap<>();
		map.put("code", tableName);
		map.put("oid", UUID.randomUUID().toString());
		map.put("tableName", comment );
		map.put("db", "modeldb3");
		map.put("cluster", "GBMN");
		map.put("cycle", "10");
		map.put("total", "1000000");
		map.put("size", "1000");
		map.put("over", "0");
		map.put("overS", "10");
		map.put("overT", "10000");
		map.put("asses_time", "10000");
		return map;
		
	}
	
	public static void main(String[] args) {
	
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, userName, pwd);
			
			ResultSet resultSet = connection.createStatement().executeQuery(insertSql);			
			PreparedStatement pStatement = connection.prepareStatement(sql);
			
			
			
			while (resultSet.next()) {
				String tableName = resultSet.getString(1);
				String comment = resultSet.getString(2);
				Map<String, String> map = produce(tableName,comment);
				pStatement.setString(1, map.get("oid"));
				pStatement.setString(2, map.get("code"));
				pStatement.setString(3, map.get("tableName"));
				pStatement.executeUpdate();
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

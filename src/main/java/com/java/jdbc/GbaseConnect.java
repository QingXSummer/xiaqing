package com.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class GbaseConnect {
	
	public static final  String db="provag";
	public static final  String CLUSTER="GBMN";
	
	public static Random random = new Random();
	
	public static DecimalFormat df = new DecimalFormat("0.00");
	
	public static String DRIVER ="com.gbase.jdbc.Driver";
	
	public static String URL ="jdbc:gbase://172.16.15.4:5258/dataman?useUnicode=true&characterEncoding=UTF8";
	
	public static String userName="gbase";
	
	public static String pwd="gbase";
	
	public static String insertSql = "select TABLE_NAME,TABLE_COMMENT  from information_schema.tables "
			+ "where TABLE_SCHEMA='"+db+"' AND TABLE_TYPE='BASE TABLE';";
	
	public static String sql ="insert into bdam_storage_data_info VALUES"
			+ "(?,?,?,'"+db+"','"+CLUSTER+"','30',?,?,'','','','',"
			+ "'2018-05-30','2017-03-01','2017-03-04')";
	
	public static Connection connection;
	
	
	public static Map<String, String> produce(String tableName,String comment){
		Map<String, String> map = new HashMap<>();
		map.put("code", tableName);
		map.put("oid", UUID.randomUUID().toString());
		map.put("tableName", comment );
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
				pStatement.setString(4, Math.abs(random.nextInt())+"");
				pStatement.setString(5, df.format(random.nextFloat()));
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

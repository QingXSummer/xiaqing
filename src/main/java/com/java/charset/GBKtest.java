package com.java.charset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;


public class GBKtest {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		File file1 = new File("C:\\tmp\\new1.txt");
		InputStream inputStream = new FileInputStream(file1);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String txt = reader.readLine();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://172.16.15.4:3306/test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&allowMultiQueries=true",
				"root", "bonc!QAZ");
		String sql = "insert into `sql` values(?)";
		QueryRunner runner = new QueryRunner();
//		runner.execute(connection, sql, rsh, params)
		runner.update(connection, sql, txt);
		
	}
	
}

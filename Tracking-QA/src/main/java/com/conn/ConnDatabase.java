package com.conn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;

public class ConnDatabase {
	public void getConnectionByProc(String procName) {
		String url="";
		String classname="";
		String username="";
		String password="";
		Connection conn = null;
		
		try {
			//
			Properties pro = new Properties();
			try {
				pro.load(ConnDatabase.class.getResourceAsStream("jdbc.properties"));
			} catch (IOException e) {
				System.out.println("");
			}
			url = pro.getProperty("url");	
			classname = pro.getProperty("driverClassName");	
			Class.forName(classname);
	        conn = (Connection) DriverManager.getConnection(url);
	        CallableStatement c=(CallableStatement) conn.prepareCall("{call "+procName+"}");
	        c.execute();
	        conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public  static void main(String[] args){
		ConnDatabase con = new ConnDatabase();
		con.getConnectionByProc("proc_testCount('aaaa')");
	}*/
	
}



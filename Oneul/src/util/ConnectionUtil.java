package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dao.GuestBookDAO;

public class ConnectionUtil {
	
	private static ConnectionUtil dbconn = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String url = "jdbc:mysql://localhost/Oneul";
	
	
	private ConnectionUtil() throws SQLException{
		//conn = ConnectionUtil.getConnection();
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url,"root","root");
		}catch(Exception ex){}
		
	}
	
	public static ConnectionUtil getInstance(){
		if(dbconn == null){
			try {
				dbconn = new ConnectionUtil();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return dbconn;
	}
	
	public Connection getConnection(){
		return conn;
	}
	
	
}

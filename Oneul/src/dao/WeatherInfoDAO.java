package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.ConnectionUtil;
import vo.WeatherInfoVO;


public class WeatherInfoDAO {
	
	PreparedStatement pstmt;
	ResultSet rs;
	
	public WeatherInfoDAO() {
		pstmt = null;
		rs = null;
		
	}
	
	public WeatherInfoVO selectWeatherIcon(String weather_code){
		
		WeatherInfoVO voW = new WeatherInfoVO();
		
		StringBuffer sql = new StringBuffer();
		
		//System.out.println(weather_code);
		
		try{
			sql.append("SELECT weather_icon ");
			sql.append("FROM weather_info ");
			sql.append("WHERE weather_code = ? ");
						
			pstmt = ConnectionUtil.getInstance().getConnection().prepareStatement(sql.toString());	
			pstmt.setString(1, weather_code);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				voW. setImgData(rs.getBlob("weather_icon"));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			dbclose();
		}
		return voW;
	}
	

	public int insert(WeatherInfoVO vo){
		StringBuffer sql = new StringBuffer();
		int result = 0;
		try{
			sql.append("UPDATE weather_info ");
			sql.append("SET weather_icon = "+ "?");
			sql.append("WHERE weather_code = 'SKY_O14' ");
			File icon=new File("C:\\image\\28.PNG");
			FileInputStream fis;
			fis=new FileInputStream(icon);
			
			//임시로 이미지 보내기
			vo.setWeather_icon(icon);
			
			pstmt = ConnectionUtil.getInstance().getConnection().prepareStatement(sql.toString());	
			//pstmt.setString(1, "SKY_O00");
			pstmt.setBinaryStream(1,(InputStream)fis, (int)(icon.length()));
		
			result = pstmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception ex){}
		finally{
			dbclose();
		}
		
		return result;
	}
	
	public void dbclose(){
		try {
			if(rs != null){	rs.close(); 
			}
			if(pstmt != null){ pstmt.close(); 
			}
			//if(conn != null){ conn.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

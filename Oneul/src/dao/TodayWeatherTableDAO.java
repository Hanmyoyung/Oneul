package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.ConnectionUtil;
import vo.PostTableVO;
//ÇØ½Ã Å×ÀÌºí·Î ¹Ù²Ù±â!!

public class TodayWeatherTableDAO {

	PreparedStatement pstmt;
	ResultSet rs;
	double total;
	double first,second,third,fourth,fifth;
	int weather_result;
	double rate1,rate2,rate3,rate4,rate5;
	String weather_type;
	

	
	public TodayWeatherTableDAO() {
		pstmt = null;
		rs = null;
		
	}
	
	public double getTotalRate(String weather_type){
		
		getTotalValue();
		
		if(weather_type.equals("º½ÀÌ¾ß ¿©¸§ÀÌ¾ß")){
			rate1=Double.parseDouble(String.format("%.2f", (first/total)*100.0));
			System.out.println(first);
			System.out.println(total);
			return(rate1);
		}else if(weather_type.equals("º½º½º½º½ º½ÀÌ ¿Ô¾î¿ä")){
			rate2=Double.parseDouble(String.format("%.2f", (second/total)*100.0));
			return(rate2);
		}else if(weather_type.equals("º½ÀÎ°¡ º½")){
			rate3=Double.parseDouble(String.format("%.2f", (third/total)*100.0));
			return(rate3);
		}else if(weather_type.equals("¾ÆÁ÷µµ º½Àº ¾Æ³Ä")){
			rate4=Double.parseDouble(String.format("%.2f", (fourth/total)*100.0));
			return(rate4);
		}else{
			rate5=Double.parseDouble(String.format("%.2f", (fifth/total)*100.0));
			return(rate5);
		}
		
	}
	
	public void getTotalValue(){
		StringBuffer sql = new StringBuffer();
		int temp;
		try{
			
			sql.append("SELECT COUNT(*) ");
			sql.append("FROM post ");
			
			pstmt = ConnectionUtil.getInstance().getConnection().prepareStatement(sql.toString());	
			rs = pstmt.executeQuery();
						
			
			while(rs.next()){
				temp=rs.getInt("COUNT(*)");
				total=temp;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception ex){}
		finally{
			dbclose();
		}
	}
	
	
	

	public void getWeatherValue(String weather_type){
		
		this.weather_type=weather_type;
		StringBuffer sql = new StringBuffer();
		int temp=0;
		
		try{
			
			sql.append("SELECT weather_freq ");
			sql.append("FROM weather ");
			sql.append("WHERE weather_type = ? ");
			
			pstmt = ConnectionUtil.getInstance().getConnection().prepareStatement(sql.toString());	
			pstmt.setString(1, weather_type);
			rs = pstmt.executeQuery();
						
			while(rs.next()){
				temp=rs.getInt("weather_freq");
			}
			
			if(weather_type.equals("º½ÀÌ¾ß ¿©¸§ÀÌ¾ß")){
				first=temp;
			}else if(weather_type.equals("º½º½º½º½ º½ÀÌ ¿Ô¾î¿ä")){
				second=temp;
			}else if(weather_type.equals("¾ÆÁùµµ º½Àº ¾Æ³Ä")){
				third=temp;
			}else if(weather_type.equals("ÀÌ°Ô º½ÀÌ¾ß °Ü¿ïÀÌ¾ß")){
				fourth=temp;
			}else{
				fifth=temp;
			}		
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception ex){}
		finally{
			dbclose();
		}
		
	}
	
	public void setWeatherValue(String weather_type){
		System.out.println("¿©±â±îÁö ¿À³ª¿ä");
		this.weather_type=weather_type;
		StringBuffer sql = new StringBuffer();
		int temp;
		
		try{
			
			sql.append("SELECT weather_freq ");
			sql.append("FROM weather ");
			sql.append("WHERE weather_type = ? ");
			
			pstmt = ConnectionUtil.getInstance().getConnection().prepareStatement(sql.toString());	
			pstmt.setString(1, weather_type);
			rs = pstmt.executeQuery();
						
			
			while(rs.next()){
				temp=rs.getInt("weather_freq");
				weather_result=temp+1;
				System.out.println(weather_result);
			}
			
			System.out.println(weather_result);
			
			if(weather_type.equals("º½ÀÌ¾ß ¿©¸§ÀÌ¾ß")){
				first=weather_result;
			}else if(weather_type.equals("º½º½º½º½ º½ÀÌ ¿Ô¾î¿ä")){
				second=weather_result;
			}else if(weather_type.equals("¾ÆÁùµµ º½Àº ¾Æ³Ä")){
				third=weather_result;
			}else if(weather_type.equals("ÀÌ°Ô º½ÀÌ¾ß °Ü¿ïÀÌ¾ß")){
				fourth=weather_result;
			}else{
				fifth=weather_result;
			}		
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception ex){}
		finally{
			dbclose();
		}
		
	}


	public void update(){
		
		StringBuffer sql = new StringBuffer();
		try{
			

			sql.append("UPDATE weather ");
			sql.append("SET weather_freq = ? ");
			sql.append("WHERE weather_type = ? ");
			
			pstmt = ConnectionUtil.getInstance().getConnection().prepareStatement(sql.toString());	
			pstmt.setInt(1, weather_result);
			pstmt.setString(2, weather_type);
			pstmt.executeUpdate();
						
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception ex){}
		finally{
			dbclose();
		}
		
	}
	
	

	
	public int delete(PostTableVO vo) throws SQLException{
		StringBuffer sql = new StringBuffer();
		int result = 0;
		
		try{
			sql.append("DELETE FROM post WHERE id = ?");		
			pstmt = ConnectionUtil.getInstance().getConnection().prepareStatement(sql.toString());					
			pstmt.setInt(1, vo.getPostno());			
			result = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
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
	
	/*
	public ArrayList<PostTableVO> select(){
		
		ArrayList<PostTableVO> list = new ArrayList<PostTableVO>();		
		StringBuffer sql = new StringBuffer();
		
		try{
			sql.append("SELECT post_no,user_no, like_freq, weather_type, coordi_no, content, area, writetime, modifytime ");
			sql.append("FROM post ORDER BY post_no DESC");
		
			
			pstmt = ConnectionUtil.getInstance().getConnection().prepareStatement(sql.toString());			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
					PostTableVO vo = new PostTableVO();
					vo.setPostno(rs.getInt("post_no"));
					vo.setUserno(rs.getInt("user_no"));
					vo.setLike(rs.getInt("like_freq"));
					vo.setWeather_type(rs.getString("weather_type"));
					vo.setCoordino(rs.getInt("coordi_no"));
					vo.setContent(rs.getString("content"));
					vo.setArea(rs.getString("area"));
					vo.setWritetime(rs.getTimestamp("writetime"));
					vo.setModifytime(rs.getTimestamp("modifytime"));
					
					list.add(vo);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			dbclose();
		}
		return list;
	}
	
	

	
	 */
	
}

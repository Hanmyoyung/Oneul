package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.ConnectionUtil;
import vo.PostTableVO;


public class TodayWeatherTableDAO {

	PreparedStatement pstmt;
	ResultSet rs;
	int temp;
	int result;
	String weather_type;

	
	public TodayWeatherTableDAO() {
		pstmt = null;
		rs = null;
		
	}
	
	
	public void getWeatherValue(String weather_type){
		
		this.weather_type=weather_type;
		StringBuffer sql = new StringBuffer();
		
		try{
			
			sql.append("SELECT weather_freq ");
			sql.append("FROM weather ");
			sql.append("WHERE weather_type = ? ");
			
			pstmt = ConnectionUtil.getInstance().getConnection().prepareStatement(sql.toString());	
			pstmt.setString(1, weather_type);
			rs = pstmt.executeQuery();
						
			
			while(rs.next()){
				temp=rs.getInt("weather_freq");
				result=temp+1;
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
			pstmt.setInt(1, result);
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

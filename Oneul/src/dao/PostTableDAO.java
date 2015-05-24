package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.ConnectionUtil;
import vo.PostTableVO;
import vo.TodayWeatherTableVO;

	

public class PostTableDAO {
	

	PreparedStatement pstmt;
	ResultSet rs;

	
	public PostTableDAO() {
		pstmt = null;
		rs = null;
		
	}
	

	
	public ArrayList<PostTableVO> select(){
		
		ArrayList<PostTableVO> list = new ArrayList<PostTableVO>();		
		StringBuffer sql = new StringBuffer();
		
		try{
			sql.append("SELECT post_no,user_no, like_freq, weather_type, coordi_item, content, area, writetime, modifytime ");
			sql.append("FROM post ORDER BY post_no DESC");
		
			
			pstmt = ConnectionUtil.getInstance().getConnection().prepareStatement(sql.toString());			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
					PostTableVO vo = new PostTableVO();
					vo.setPostno(rs.getInt("post_no"));
					vo.setUserno(rs.getInt("user_no"));
					vo.setLike(rs.getInt("like_freq"));
					vo.setWeather_type(rs.getString("weather_type"));
					vo.setCoordi_item(rs.getString("coordi_item"));
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
	
	
	public PostTableVO selectImage(int listnum){
		
		PostTableVO vo = new PostTableVO();
		
		StringBuffer sql = new StringBuffer();
		
		try{
			sql.append("SELECT image ");
			sql.append("FROM post ");
			sql.append("WHERE post_no = "+listnum);
						
			pstmt = ConnectionUtil.getInstance().getConnection().prepareStatement(sql.toString());					
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				vo. setImgData(rs.getBlob("image"));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			dbclose();
		}
		return vo;
	}
	

	public int insert(PostTableVO vo){
		StringBuffer sql = new StringBuffer();
		TodayWeatherTableDAO daoT = new TodayWeatherTableDAO();
		CoordiTableDAO daoC = new CoordiTableDAO();
		String weather_string="봄이야 여름이야";
		String coordi_item="청바지";
		
		int result = 0;
		try{

			sql.append("INSERT INTO post(post_no, user_no, image, weather_type, content, coordi_item) ");
			sql.append("VALUES(?, ?, ?, ?, ?, ?) ");
			
			File image=new File("C:\\image\\1.jpg");
			FileInputStream fis;
			fis=new FileInputStream(image);
			
			//임시로 이미지 보내기
			vo.setImage(image);
			
			pstmt = ConnectionUtil.getInstance().getConnection().prepareStatement(sql.toString());			
			
			pstmt.setInt(1, vo.getPostno());
			pstmt.setInt(2, 1);
			pstmt.setBinaryStream(3,(InputStream)fis, (int)(image.length()));
			pstmt.setString(4, weather_string);
			pstmt.setString(5, "헿헤헤에에엥헤헤헤헤헤헤헿ㅎㅎ헤헤헤헤헤");
			pstmt.setString(6, coordi_item);
			daoC.getCoordiItem(coordi_item);
			daoC.update();
			
			daoT.setWeatherValue(weather_string);
			daoT.update();
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception ex){}
		finally{
			dbclose();
		}
		
		return result;
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
}

package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.PostTableVO;

	

public class PostTableDAO {
	
	private static PostTableDAO dao = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String url = "jdbc:mysql://localhost/Oneul";
	

	
	private PostTableDAO() throws SQLException{
		//conn = ConnectionUtil.getConnection();
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url,"root","root");
		}catch(Exception ex){}
		
	}
	

	public static PostTableDAO getInstance(){
		if(dao == null){
			try {
				dao = new PostTableDAO();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dao;
	}
	

	public ArrayList<PostTableVO> select(){
		
		ArrayList<PostTableVO> list = new ArrayList<PostTableVO>();		
		StringBuffer sql = new StringBuffer();
		
		try{
			sql.append("SELECT post_no,user_no, like_freq, weather_no, coordi_no, content, area, writetime, modifytime ");
			sql.append("FROM post ORDER BY post_no DESC");
		
			
			pstmt = conn.prepareStatement(sql.toString());			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
					PostTableVO vo = new PostTableVO();
					vo.setPostno(rs.getInt("post_no"));
					vo.setUserno(rs.getInt("user_no"));
					vo.setLike(rs.getInt("like_freq"));
					vo.setWeather(rs.getInt("weather_no"));
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
	
	public PostTableVO selectImage(int listnum){
		
		StringBuffer sql = new StringBuffer();
		PostTableVO vo = new PostTableVO();
		
		try{
			sql.append("SELECT image WHERE post_no=listnum");
			sql.append("FROM post ORDER BY post_no DESC");
		
			
			pstmt = conn.prepareStatement(sql.toString());			
			rs = pstmt.executeQuery();
			
			vo. setImgData(rs.getBlob("image"));
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			dbclose();
		}
		return vo;
	}
	

	public int insert(PostTableVO vo){
		StringBuffer sql = new StringBuffer();
		int result = 0;
		try{
			sql.append("INSERT INTO post(post_no, user_no, image, weather_no, content) ");
			//sql.append("VALUES(GUESTBOOK_NO_SEQ.NEXTVAL,?,?,?) ");
			sql.append("VALUES(?,?,?,?,?) ");
			File image=new File("C:\\1.jpg");
			FileInputStream fis;
			fis=new FileInputStream(image);
			
			//임시로 이미지 보내기
			vo.setImage(image);
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, vo.getPostno());
			pstmt.setInt(2, vo.getUserno());
			//pstmt.setTimestamp(3, vo.getWritetime());
			//pstmt.setTimestamp(4, vo.getModifytime());
			//pstmt.setBinaryStream(3, vo.getPicture());
			//pstmt.setBinaryStream(3, (InputStream)fis, (int)(image.length()));
			pstmt.setBinaryStream(3,(InputStream)fis, (int)(image.length()));
			//pstmt.setInt(6, vo.getLike());
			pstmt.setInt(4, vo.getWeather());
			pstmt.setString(5, vo.getContent());
			//pstmt.setString(9, vo.getArea());
			//pstmt.setInt(10, vo.getCoordino());
		
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
			
			pstmt = conn.prepareStatement(sql.toString());
			
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
			if(rs != null){	rs.close(); }
			if(pstmt != null){ pstmt.close(); }
			//if(conn != null){ conn.close(); }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
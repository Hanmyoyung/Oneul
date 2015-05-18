package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vo.GuestBookVO;

public class GuestBookDAO {
	
	private static GuestBookDAO dao = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String url = "jdbc:mysql://localhost/Oneul";
	
	
	private GuestBookDAO() throws SQLException{
		//conn = ConnectionUtil.getConnection();
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url,"root","root");
		}catch(Exception ex){}
		
	}
	
	public static GuestBookDAO getInstance(){
		if(dao == null){
			try {
				dao = new GuestBookDAO();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return dao;
	}
	
	
	public ArrayList<GuestBookVO> select(){
		
		ArrayList<GuestBookVO> list = new ArrayList<GuestBookVO>();		
		StringBuffer sql = new StringBuffer();
		
		try{
			sql.append("SELECT id, name, content, password ");
			sql.append("FROM guestbook ORDER BY id DESC");
		
			
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
					GuestBookVO vo = new GuestBookVO();
					vo.setGuestbookno(rs.getInt("id"));
					vo.setGuestbookname(rs.getString("name"));
					vo.setGuestbookcontent(rs.getString("content"));
					vo.setGuestbookpassword(rs.getString("password"));
					list.add(vo);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			dbclose();
		}
		return list;
	}
	
	public int insert(GuestBookVO vo){
		StringBuffer sql = new StringBuffer();
		int result = 0;
		try{
			sql.append("INSERT INTO guestbook(id, name, content, password) ");
			//sql.append("VALUES(GUESTBOOK_NO_SEQ.NEXTVAL,?,?,?) ");
			sql.append("VALUES(?,?,?,?) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, vo.getGuestbookno());
			pstmt.setString(2, vo.getGuestbookname());
			pstmt.setString(3, vo.getGuestbookcontent());
			pstmt.setString(4, vo.getGuestbookpassword());
			
			result = pstmt.executeUpdate();	
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			dbclose();
		}
		
		return result;
	}
	
	public int delete(GuestBookVO vo) throws SQLException{
		StringBuffer sql = new StringBuffer();
		int result = 0;
		
		try{
			sql.append("DELETE FROM guestbook WHERE id = ? AND password = ? ");

			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, vo.getGuestbookno());
			pstmt.setString(2, vo.getGuestbookpassword());
			
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

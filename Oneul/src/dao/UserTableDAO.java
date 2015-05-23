package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.ConnectionUtil;
import vo.UserTableVO;

public class UserTableDAO {
	
	PreparedStatement pstmt;
	ResultSet rs;

	
	public UserTableDAO() {
		pstmt = null;
		rs = null;
		
	}
				
	public ArrayList<UserTableVO> select(){
			
		ArrayList<UserTableVO> list= new ArrayList<UserTableVO>();
		StringBuffer sql = new StringBuffer();
		
		try{
			sql.append("SELECT u.nickname "); 
			sql.append("FROM post p, user u  ");
			sql.append("WHERE p.user_no= u.user_no ");
			sql.append("ORDER BY post_no DESC");
			
			pstmt = ConnectionUtil.getInstance().getConnection().prepareStatement(sql.toString());				
			rs = pstmt.executeQuery();
			
			while(rs.next()){
					UserTableVO vo = new UserTableVO();
					vo.setNickname(rs.getString("nickname"));
					
					list.add(vo);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			dbclose();
		}
		return list;
	}
	
	public int insert(UserTableVO vo){
		StringBuffer sql = new StringBuffer();
		int result = 0;
		try{
			//sql.append("INSERT INTO user(nickname) ");
			//sql.append("VALUES(?) ");
			
			sql.append("INSERT INTO user(id, password, user_area, nickname) ");
			sql.append("VALUES(?, ?, ?, ?) ");
			
			/*
			pstmt = ConnectionUtil.getInstance().getConnection().prepareStatement(sql.toString());			
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getUser_area());
			pstmt.setString(1, vo.getNickname());
			result = pstmt.executeUpdate();
			*/
			
			pstmt = ConnectionUtil.getInstance().getConnection().prepareStatement(sql.toString());			
			pstmt.setString(1,"ccunyday");
			pstmt.setString(2,"aaaa");
			pstmt.setString(3,"seoul");
			pstmt.setString(4,"boysbeanxious");
			
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

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.ConnectionUtil;
import vo.CoordiTableVO;
import vo.PostTableVO;


public class CoordiTableDAO {

	PreparedStatement pstmt;
	ResultSet rs;
	int temp;
	int result;
	String coordi_item;
	String maxCoordi;

	
	public CoordiTableDAO() {
		pstmt = null;
		rs = null;
		
	}
	
	
	public CoordiTableVO selectTodayCoordi(){
		
		CoordiTableVO voC = new CoordiTableVO();
		//PostTableVO voP = new PostTableVO();
		
		StringBuffer sql = new StringBuffer();
		
		try{
			sql.append("SELECT image, coordi_item ");
			sql.append("FROM post ");
			sql.append("WHERE coordi_item = (SELECT coordi_item FROM coordi WHERE coordi_freq = (SELECT MAX(coordi_freq) FROM coordi ))");
						
			pstmt = ConnectionUtil.getInstance().getConnection().prepareStatement(sql.toString());	
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				voC. setImgData(rs.getBlob("image"));
				maxCoordi=rs.getString("coordi_item"); 
			}			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			dbclose();
		}
		return voC;
	}
	
	public String getMaxCoordi(){
		return maxCoordi;
	}

	
	public void getCoordiItem(String coordi_item){
		
		this.coordi_item=coordi_item;
		StringBuffer sql = new StringBuffer();
		
		try{
			
			sql.append("SELECT coordi_freq ");
			sql.append("FROM coordi ");
			sql.append("WHERE coordi_item = ? ");
			
			pstmt = ConnectionUtil.getInstance().getConnection().prepareStatement(sql.toString());	
			pstmt.setString(1, coordi_item);
			rs = pstmt.executeQuery();
						
			
			while(rs.next()){
				temp=rs.getInt("coordi_freq");
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
			

			sql.append("UPDATE coordi ");
			sql.append("SET coordi_freq = ? ");
			sql.append("WHERE coordi_item = ? ");
			
			pstmt = ConnectionUtil.getInstance().getConnection().prepareStatement(sql.toString());	
			pstmt.setInt(1, result);
			pstmt.setString(2, coordi_item);
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
}


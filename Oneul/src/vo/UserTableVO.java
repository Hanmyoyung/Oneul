package vo;

import java.io.File;
import java.sql.Blob;
import java.sql.SQLException;

public class UserTableVO {
	
	int user_no;
	String id;
	String password;
	String user_area;
	String nickname;
	byte[ ] imgData = null ;	
	File propic;

	
	
	public byte[] getImgData() {
		return imgData;
	}

	public void setImgData(Blob bImage) {
		try {
			 imgData = bImage.getBytes(1,(int)bImage.length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public File getPropic() {
		return propic;
	}
	public void setgetPropic(File image) {
		this.propic = image;
	}
		
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_area() {
		return user_area;
	}
	public void setUser_area(String user_area) {
		this.user_area = user_area;
	}

	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


}

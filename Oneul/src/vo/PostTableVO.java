package vo;

import java.io.File;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;



public class PostTableVO { // 이걸 useBean으로 해놓으면 객체 생성할 필요 없이 쓸 수 있다. 모든 페이지에서 쓴다면 그냥 이렇게 만드는게 좋다. 유즈 빈으로 
	//<jsp:useBean..... 이렇게>
	
	private int postno, userno, like, coordino;
	private String content, area, weather_type;
	private Timestamp writetime, modifytime;
	private Blob picture;
	byte[ ] imgData = null ;	
	File image;

	
	
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
		
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	
	
	public Blob getPicture() {
		return picture;
	}
	
	public void setPicture(Blob blob) {
		this.picture = blob; 
		
	}			
	

	public int getPostno() {
		return postno;
	}
	public void setPostno(int postno) {
		this.postno = postno;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public Timestamp getWritetime() {
		return writetime;
	}
	public void setWritetime(Timestamp writetime) {
		this.writetime = writetime;
	}
	public Timestamp getModifytime() {
		return modifytime;
	}
	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public String getWeather_type() {
		return weather_type;
	}
	public void setWeather_type(String weather) {
		this.weather_type = weather;
	}
	public int getCoordino() {
		return coordino;
	}
	public void setCoordino(int coordino) {
		this.coordino = coordino;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}


}

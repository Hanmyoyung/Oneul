package vo;

import java.sql.Blob;
import java.sql.Timestamp;



public class PostTableVO {
	
	private int postno, userno, like, weather, coordino;
	private String content, area;
	private Timestamp writetime, modifytime;
	private Blob picture;
	byte[ ] imgData = null ;
	
	
	public Blob getPicture() {
		return picture;
	}
	
	public void setPicture(Blob blob) {
		this.picture = blob; 
		
	}			
	
	public byte[] getImgData() {
		return imgData;
	}
	public void setImgData(byte[] imgData) {
		this.imgData = imgData;
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
	public int getWeather() {
		return weather;
	}
	public void setWeather(int weather) {
		this.weather = weather;
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

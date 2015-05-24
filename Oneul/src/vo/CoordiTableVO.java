package vo;

import java.sql.Blob;
import java.sql.SQLException;

public class CoordiTableVO {

	int coordi_no;
	String coordi_item; 
	int coordi_freq;
	String maxCoordi;
	
	public String getMaxCoordi() {
		return maxCoordi;
	}

	public void setMaxCoordi(String maxCoordi) {
		this.maxCoordi = maxCoordi;
	}

	public void setImgData(byte[] imgData) {
		this.imgData = imgData;
	}
	byte[ ] imgData = null ;
	
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
	public int getCoordi_no() {
		return coordi_no;
	}
	public void setCoordi_no(int coordi_no) {
		this.coordi_no = coordi_no;
	}
	public String getCoordi_item() {
		return coordi_item;
	}
	public void setCoordi_item(String coordi_item) {
		this.coordi_item = coordi_item;
	}
	public int getCoordi_freq() {
		return coordi_freq;
	}
	public void setCoordi_freq(int coordi_freq) {
		this.coordi_freq = coordi_freq;
	}
}

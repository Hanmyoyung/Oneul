package vo;

import java.io.File;
import java.sql.Blob;
import java.sql.SQLException;



public class WeatherInfoVO { // �̰� useBean���� �س����� ��ü ������ �ʿ� ���� �� �� �ִ�. ��� ���������� ���ٸ� �׳� �̷��� ����°� ����. ���� ������ 
	//<jsp:useBean..... �̷���>
	
	private String weather_code;
	//private Blob weather_icon;
	byte[ ] imgData = null ;	
	File weather_icon;

	
	
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
		
	public File getWeather_icon() {
		return weather_icon;
	}
	public void setWeather_icon(File image) {
		this.weather_icon = image;
	}

	public String getWeather_code() {
		return weather_code;
	}

	public void setWeather_code(String weather_code) {
		this.weather_code = weather_code;
	}
/*
	public Blob getWeather_icon() {
		return weather_icon;
	}

	public void setWeather_icon(Blob blob) {
		this.weather_icon = blob;
	}	
*/
}
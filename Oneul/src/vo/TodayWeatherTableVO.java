package vo;

public class TodayWeatherTableVO {
	
	int weather_no;
	String weather; 
	int weather_freq;
	
	public int getWeather_no() {
		return weather_no;
	}
	public void setWeather_no(int weather_no) {
		this.weather_no = weather_no;
	}
	public String getWeather_type() {
		return weather;
	}
	public void setWeather_type(String weather_type) {
		this.weather = weather_type;
	}
	public int getWeather_freq() {
		return weather_freq;
	}
	public void setWeather_freq(int weather_freq) {
		this.weather_freq = weather_freq;
	}

}

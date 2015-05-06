package dao;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URLDecoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


public class SkWeather {
	
	String weatherResult;
	String city;
	String county;
	String village;
	String currentTemp;
	String maxTemp;
	String minTemp;
	String timeRelease;
	
	public SkWeather(){
		
		weatherResult = new String();
		city = new String();
		county = new String();
		village = new String();
		currentTemp = new String();
		maxTemp = new String();
		minTemp = new String();
		timeRelease = new String();
	}
	
	public String getCurruntWeather(){
		return currentTemp;
	}
	
	public void jsonParsing(){

		try{
			
			ObjectMapper mapper = new ObjectMapper();				
			JsonNode rootNode = mapper.readTree(new StringReader(weatherResult));
			JsonNode tempNode = rootNode.get("weather").get("hourly").get(0).get("temperature");	
			JsonNode pingNode = rootNode.get("weather").get("hourly").get(0).get("grid");
			
			city = pingNode.get("city").getTextValue();
			county = pingNode.get("county").getTextValue();
			village = pingNode.get("village").getTextValue();
			currentTemp = tempNode.get("tc").getTextValue();
			maxTemp = tempNode.get("tmax").getTextValue();
			minTemp = tempNode.get("tmin").getTextValue();
			timeRelease = rootNode.get("weather").get("hourly").get(0).get("timeRelease").getTextValue();

			System.out.println("도시는요? "+city);
			System.out.println("구는요 ? "+county);
			System.out.println("동은요 ? "+village);
			System.out.println("현재 날씨는요? "+currentTemp);
			System.out.println("최고 기온은요? "+maxTemp);
			System.out.println("최저기온은요? "+minTemp);
			System.out.println("릴리즈된시간은요? "+timeRelease);
			
			}catch(JsonParseException e){
			}catch(JsonMappingException e){
			}catch(IOException e){
			}
					
	}
	
	public void getWeatherData(){
		// HttpClient 생성
		HttpClient httpclient = new DefaultHttpClient(); 
		StringBuffer weatherResultBuf = new StringBuffer();
		
		try {

			HttpGet httpget = new HttpGet("http://apis.skplanetx.com/weather/current/hourly?lon=&village=을지로2가&county=중구&lat=&city=서울&version=1&appKey=4e153581-381a-3b7a-9dbe-c665ea699bb3&format=json");
			System.out.println("executing request " + httpget.getURI());
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			System.out.println("----------------------------------------");
			// 응답 결과
			System.out.println(response.getStatusLine());
			
			if (entity != null) {
				System.out.println("Response content length: "+ entity.getContentLength());
				BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8")); 
				
				String line = new String();
				while ((line = br.readLine()) != null) {
					weatherResultBuf.append(URLDecoder.decode(line,"EUC-KR"));
				}
			}
			
			weatherResult = weatherResultBuf.toString();
			System.out.println(weatherResult);			
			httpget.abort();
			
			System.out.println("----------------------------------------");
			httpclient.getConnectionManager().shutdown();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}
	
				
}

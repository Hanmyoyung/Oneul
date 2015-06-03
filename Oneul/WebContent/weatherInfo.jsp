<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="vo.WeatherInfoVO"%>
<%@page import="dao.WeatherInfoDAO"%>
<%@page import="util.SkWeather"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%--오른쪽 패널: 날씨 정보 보여주는 jsp --%>
<%
   WeatherInfoDAO dao = new WeatherInfoDAO();
   WeatherInfoVO vo = new WeatherInfoVO();
   //여기서 이미지 처리 해도 무방
   //화면상에서 걸리는 것들이 자바 스크립트!를 쓰자  이렇게 위에서 <!% 
   // 이렇게 됩니다.
   
%>

	스크에서 받아온 날씨 정보를 보여 드립니다.
<%   
	SkWeather sk  = new SkWeather();
   	sk.getWeatherData();
   	sk.jsonParsing();
   	sk.imageParsing();
   	sk.getSkyCode();
%>
 
	<img id="weatherIcon" src="weatherIconProcessing.jsp?parameter=<%=sk.getSkyCode() %>" width="100"  />
	
	<br>
   	<%=sk.getCity()%> <br>
	<%=sk.getCounty()%><br>
	<%=sk.getVillage()%><br>
	<%=sk.getCurruntTemp()%><br>
	<%=sk.getSkyString() %>

</body>
</html>
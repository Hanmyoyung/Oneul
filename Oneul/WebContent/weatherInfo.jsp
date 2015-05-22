<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="util.SkWeather"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	스크에서 받아온 날씨 정보를 보여 드립니다.
<%   
	SkWeather sk  = new SkWeather();
   	sk.getWeatherData();
   	sk.jsonParsing();
%>
	<br>
   	<%=sk.getCity()%> <br>
	<%=sk.getCounty()%><br>
	<%=sk.getVillage()%><br>
	<%=sk.getCurruntTemp()%>

</body>
</html>
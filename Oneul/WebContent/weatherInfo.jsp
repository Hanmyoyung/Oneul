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

<%--������ �г�: ���� ���� �����ִ� jsp --%>
<%
   WeatherInfoDAO dao = new WeatherInfoDAO();
   WeatherInfoVO vo = new WeatherInfoVO();
   //���⼭ �̹��� ó�� �ص� ����
   //ȭ��󿡼� �ɸ��� �͵��� �ڹ� ��ũ��Ʈ!�� ����  �̷��� ������ <!% 
   // �̷��� �˴ϴ�.
   
%>

	��ũ���� �޾ƿ� ���� ������ ���� �帳�ϴ�.
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
<%@ page import="java.sql.*"%>
<%@page import="vo.TodayWeatherTableVO"%>
<%@page import="dao.TodayWeatherTableDAO"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="javascript">

   function goPage(url){
	     parent.post.location.href=url;
	     alert(url);
	     // alert
	   }
</script>

<%--왼쪽 패널: 오늘의 날씨 통계정보 보여주는 jsp --%>
<%
	TodayWeatherTableDAO dao = new TodayWeatherTableDAO();
	//TodayWeatherTableVO vo = new TodayWeatherTableVO();	
	dao.getTotalValue();
	dao.getWeatherValue("봄이야 여름이야");
	dao.getWeatherValue("봄봄봄봄 봄이 왔어요");
	dao.getWeatherValue("봄인가 봄");
	dao.getWeatherValue("이직도 봄은 아냐");
	dao.getWeatherValue("이게 봄이야 겨울이야");
	
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	비율입니다. <br>
	봄이야 여름이야 : <%=dao.getTotalRate("봄이야 여름이야")%>%  <img id="goebutton" src="image/gobutton.png" width="25" onclick="goPage('timelineByWeather.jsp?parameter=<%= dao.getWeatherPara("봄이야 여름이야") %>')"  /><br>
	봄봄봄봄 봄이 왔어요 : <%=dao.getTotalRate("봄봄봄봄 봄이 왔어요")%>% <img id="gobutton" src="image/gobutton.png" width="25" onclick="goPage('timelineByWeather.jsp?parameter=<%= dao.getWeatherPara("봄봄봄봄 봄이 왔어요") %>')" /><br>
	봄인가 봄 : <%=dao.getTotalRate("봄인가 봄")%>% <img id="gobutton" src="image/gobutton.png" width="25" onclick="goPage('timelineByWeather.jsp?parameter=<%= dao.getWeatherPara("봄인가 봄") %>')" /><br>
	아직도 봄은 아냐 : <%=dao.getTotalRate("이직도 봄은 아냐")%>% <img id="gobutton" src="image/gobutton.png" width="25" onclick="goPage('timelineByWeather.jsp?parameter=<%= dao.getWeatherPara("이직도 봄은 아냐") %>')"  /><br>
	이게 봄이야 겨울이야 : <%=dao.getTotalRate("이게 봄이야 겨울이야")%>% <img id="gobutton" src="image/gobutton.png" width="25" onclick="goPage('timelineByWeather.jsp?parameter=<%= dao.getWeatherPara("이게 봄이야 겨울이야") %>')" /><br>
	
</body>
</html>



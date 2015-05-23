<%@ page import="java.sql.*"%>
<%@page import="vo.WeatherInfoVO"%>
<%@page import="dao.WeatherInfoDAO"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% 
	WeatherInfoDAO daoW = WeatherInfoDAO.getInstance();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String weather_code = request.getParameter("parameter");
	WeatherInfoVO voW = daoW.selectWeatherIcon(weather_code);
	
	//System.out.println(weather_code);

	out.clear();
	out=pageContext.pushBody();
	
	response.setContentType("icon/png");
	OutputStream o = response.getOutputStream();
	o.write(voW.getImgData());
	o.flush();
	o.close();
	
%>

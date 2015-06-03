<%@ page import="java.sql.*"%>
<%@page import="vo.CoordiTableVO"%>
<%@page import="dao.CoordiTableDAO"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<% 
	CoordiTableDAO daoC = new CoordiTableDAO();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	//String weather_code = request.getParameter("parameter");
	CoordiTableVO voC = daoC.selectTodayCoordi();
	//System.out.println(weather_code);
	

	out.clear();
	out=pageContext.pushBody();
	
	response.setContentType("image/jpeg");
	OutputStream o = response.getOutputStream();
	o.write(voC.getImgData());
	o.flush();
	o.close();
	
%>

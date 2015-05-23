<%@ page import="java.sql.*"%>
<%@page import="vo.PostTableVO"%>
<%@page import="dao.PostTableDAO"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% 
	PostTableDAO dao = new PostTableDAO();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int listnum = Integer.parseInt(request.getParameter("parameter"))+1;
	PostTableVO vo = dao.selectImage(listnum);
	
	out.clear();
	out=pageContext.pushBody();
	
	response.setContentType("image/jpeg");
	OutputStream o = response.getOutputStream();
	o.write(vo.getImgData());
	o.flush();
	o.close();
	
%>


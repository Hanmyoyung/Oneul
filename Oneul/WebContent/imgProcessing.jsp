<%@ page import="java.sql.*"%>
<%@page import="vo.PostTableVO"%>
<%@page import="dao.PostTableDAO"%>
<%@ page import="java.io.*"%>
 <%@page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% 
	PostTableDAO dao = PostTableDAO.getInstance();
	//PostTableVO vo = new PostTableVO();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int listnum = Integer.valueOf(request.getParameter("i"))+1;
	
	PostTableVO vo = dao.selectImage(listnum);
	
	
	out.clear();
	out=pageContext.pushBody();
	response.setContentType("image/jpeg");
	OutputStream o = response.getOutputStream();
	o.write(vo.getImgData());
	o.flush();
	//o.close();

 
	out.println("Display Blob Example");
	out.println("image not found for given id>");
			
			


%>


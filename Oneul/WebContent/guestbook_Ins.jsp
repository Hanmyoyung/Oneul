<%@page import="javax.swing.JOptionPane"%>
<%@page import="javax.swing.JPanel"%>
<%@page import="dao.GuestBookDAO"%>
<%@page import="vo.GuestBookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
	
		GuestBookDAO dao = GuestBookDAO.getInstance();
		GuestBookVO vo = new GuestBookVO();
		
		vo.setGuestbookcontent(request.getParameter("content"));
		vo.setGuestbookname(request.getParameter("name"));
		vo.setGuestbookpassword(request.getParameter("password"));
		
		int result = dao.insert(vo);
	
		response.sendRedirect("guestbook.jsp"); 
	%>
</body>
</html>
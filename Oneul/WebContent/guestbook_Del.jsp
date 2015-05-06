<%@page import="vo.GuestBookVO"%>
<%@page import="dao.GuestBookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>삭제</title>
</head>
<body>
	<%
		GuestBookDAO dao = new GuestBookDAO();
		GuestBookVO vo = new GuestBookVO();
		
		vo.setGuestbookno(Integer.parseInt(request.getParameter("id")));
		
		System.out.println(request.getParameter("password"));
		if(request.getParameter("password") != null){
			vo.setGuestbookpassword(request.getParameter("password"));
			
			dao.delete(vo);
		}
	%>
	<form method="post" action="guestbook_Del.jsp">
		<input type="hidden" name="id" value="<%=vo.getGuestbookno()%>">
		비밀번호 입력 :
		<input type="text" name="password"><input type="submit" value="입력" onclick="self.close(); opener.location.reload();"> 		
	</form> 
</body>
</html>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.PostTableVO"%>
<%@page import="dao.PostTableDAO"%>
<%@page import="vo.CoordiTableVO"%>
<%@page import="dao.CoordiTableDAO"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.Blob"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%--맨 위 상단 패널: 가장 많이 선택된 코디 보여주는 jsp --%>
<%
   CoordiTableDAO daoC = new CoordiTableDAO();
	//CoordiTableVO vo = new CoordiTableVO();
   //여기서 이미지 처리 해도 무방
   //화면상에서 걸리는 것들이 자바 스크립트!를 쓰자  이렇게 위에서 <!% 
   // 이렇게 됩니다.
  daoC.selectTodayCoordi();
   
%>

	<%-- 
	
	 <img id="TodayCoordi" src="TodayCoordiImgProcessing.jsp?parameter=<%=daoC.getMaxCoordi()%>" width="100"  />--%>
	
	<img id="TodayCoordi" src="TodayCoordiImgProcessing.jsp" width="100"  />

 <%=daoC.getMaxCoordi()%>
	

	


</body>
</html>

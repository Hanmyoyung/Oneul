<%@page import="java.util.ArrayList"%>
<%@page import="vo.GuestBookVO"%>
<%@page import="dao.GuestBookDAO"%>
<%@page import="dao.SkWeather"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.OutputStream"  %>>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	GuestBookDAO dao = GuestBookDAO.getInstance();
	GuestBookVO vo = new GuestBookVO();
%>
<script language="javascript">
	function goDel(id){
		window.open("guestbook_Del.jsp?id=" + id, "", "width=400, height=200");
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
<%
	//SkWeather sk  = new SkWeather();
	//sk.getWeatherData();
	//sk.jsonParsing();
	

	//out.println("현재 날씨는요? "+sk.getCurruntWeather());

%>
<br>

<b>방명록</b><hr>
	<!-- 글 등록 부분 시작-->
	<form name="inputform" method="post" action="guestbook_Ins.jsp">
		<table border="1" width="500">
			<input type="hidden" name ="pro" value="ins">
			<tr>
				<th width="100">이름</th>
				<td><input type="text" name="name"></td>
				<th width="100">비밀번호</th>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="4">
					<textarea rows="3" cols="55" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center"><input type="submit" value="등록"></td>
			</tr>
		</table>
	</form>
	<hr>
	<!-- 글 등록 부분 끝 -->
	
	<!-- 글 목록 부분 시작 -->
	<form method="post" name="listform">
		<input type="hidden" name ="pro">
<%
	ArrayList<GuestBookVO> list = dao.select();
	if(list.size() > 0 && list != null){
		for(int i=0; i<list.size(); i++){
			vo = list.get(i);
%>
			<table border="1" width="500">
					<tr>
						<th width="100">이름</th>
						<td width="300"><%=vo.getGuestbookname() %></td>
						<td width="100"><input type="button" onclick="goDel(<%=vo.getGuestbookno()%>)" value="삭제"></td>
					</tr>
					<tr>
						<td colspan="4"><%= vo.getGuestbookcontent() %></td>
					</tr>
					<tr>
						<td colspan="4">
						<%

							out.clear();
							out=pageContext.pushBody();
							response.setContentType("image/jpg");
							OutputStream o = response.getOutputStream();
							o.write(vo.getImgData());
							o.flush();
							o.close();
						%></td>
					</tr>
			</table>
<%
		}
	}
%>
 	</form>
 	<!-- 글 목록 부분 끝 -->
</body>
</html>
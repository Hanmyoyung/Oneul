<%@page import="java.util.ArrayList"%>
<%@page import="vo.PostTableVO"%>
<%@page import="dao.PostTableDAO"%>
<%@page import="vo.UserTableVO"%>
<%@page import="dao.UserTableDAO"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.Blob"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
   	PostTableVO voP = new PostTableVO();
	PostTableDAO daoP = new PostTableDAO();
	UserTableVO voU = new UserTableVO();
	UserTableDAO daoU = new UserTableDAO();
	
	String weather_type = request.getParameter("parameter");
	System.out.println("왜 되다 안될까요"+weather_type);
	
   	ArrayList<PostTableVO> listP = daoP.selectByWeather(weather_type);
   	ArrayList<UserTableVO> listU = daoU.select();
   //여기서 이미지 처리 해도 무방
   //화면상에서 걸리는 것들이 자바 스크립트!를 쓰자  이렇게 위에서 <!% 
   // 이렇게 됩니다.
%>
<script language="javascript">

function goDel(id){
 	  alert("신고하시겠습니까?");
    //window.open("guestbook_Del.jsp?post_no=" + post_no, "", "width=400, height=200");
    
   // alert
}

function goLike(id){
 	  alert("공감하시겠습니까?");
    //window.open("guestbook_Del.jsp?post_no=" + post_no, "", "width=400, height=200");
    
   // alert
}
function goRep(id){
 	  alert("댓글을 확인하시겠습니까?");
    //window.open("guestbook_Del.jsp?post_no=" + post_no, "", "width=400, height=200");
    
   // alert
}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>날씨 별 타임라인</title>
</head>
<body>

<br>

<p align="center"><b><%=daoP.getSelectedWeather() %></b><img id="writebutton" src="image/writebutton.png" width="25" onclick="location.href='writeTable.jsp'" align="right" />  </p>

<hr>
   <!-- 글 목록 부분 시작 -->
   <form method="post" name="listform">
      <input type="hidden" name ="pro">

<%

   if(listP.size() > 0 && listP != null){
      for(int i=0; i<listP.size(); i++){
         voP = listP.get(i);
         voU = listU.get(i);
%>
			<table border="1" width=100%>
					<tr>
						<td width=50%>유저사진 </td>
						<td width=50%>닉네임 : <%=voU.getNickname()%></td>
					</tr>
					
					<tr>
						<td>옷 : <%=voP.getCoordi_item() %></td>
						<td>날씨 : <%=voP.getWeather_type() %></td>
		
					</tr>
	
					<tr>
						<td colspan="2">
							  <img id="photoImage" src="imgProcessing.jsp?parameter=<%= i%>" width="100"  />
						</td>
					</tr>
					
					<tr>
						<td colspan="2">
							  글입니다. <%=voP.getContent() %>
						</td>
					</tr>
					
					<tr>
						<td colspan="2">
						<div style="float:left;">
						<input  type="button" onclick="goDel(<%=voP.getPostno()%>)"   value="신고">
						</div>
						<div style="float:right;">
						<input type="button" onclick="goLike(<%=voP.getPostno()%>)"   value="공감">
						<input type="button" onclick="goRep(<%=voP.getPostno()%>)"    value="댓글">
						</div>
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

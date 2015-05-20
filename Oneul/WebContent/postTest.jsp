<%@page import="java.util.ArrayList"%>
<%@page import="vo.PostTableVO"%>
<%@page import="dao.PostTableDAO"%>
<%@page import="dao.SkWeather"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.Blob"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
   PostTableDAO dao = PostTableDAO.getInstance();
   PostTableVO vo = new PostTableVO();
   //여기서 이미지 처리 해도 무방
   //화면상에서 걸리는 것들이 자바 스크립트!를 쓰자  이렇게 위에서 <!% 
   // 이렇게 됩니다.
%>
<script language="javascript">
   function goDel(id){
      window.open("guestbook_Del.jsp?post_no=" + post_no, "", "width=400, height=200");
     // alert
   }
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
<%


   
   SkWeather sk  = new SkWeather();
   sk.getWeatherData();
   sk.jsonParsing();
   

  out.println("현재 날씨는요? "+sk.getCurruntWeather());
   

%>
<br>

<b>방명록</b><hr>   


   <!-- 글 목록 부분 시작 -->
   <form method="post" name="listform">
      <input type="hidden" name ="pro">

<%
   //dao.insert(vo);
   ArrayList<PostTableVO> list = dao.select();
   if(list.size() > 0 && list != null){
      for(int i=0; i<list.size(); i++){
         vo = list.get(i);
%>
			<table border="1" width="500">
					<tr>
						<th width="100">이름</th>
						<td width="300"><%=vo.getUserno() %></td>
						<td width="100"><input type="button" onclick="goDel(<%=vo.getPostno()%>)" value="삭제"></td>
					</tr>
					<tr>
						<td colspan="4"><%= vo.getContent() %></td>
					</tr>
					<tr>
						<td colspan="4">
							  <img id="photoImage" src="imgProcessing.jsp?parameter=<%= i%>" width="100"  />
						</td>
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

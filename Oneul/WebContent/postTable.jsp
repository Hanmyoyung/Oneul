<%@page import="java.util.ArrayList"%>
<%@page import="vo.PostTableVO"%>
<%@page import="dao.PostTableDAO"%>
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

<br>

<b>SEOUL</b><hr>   


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
			<table border="1" width=100%>
					<tr>
						<td width=50%>유저사진</td>
						<td width=50%>닉네임</td>
					</tr>
					
					<tr>
						<td>옷</td>
						<td>날씨</td>
						
					</tr>
					
					<tr>
						<td colspan="2">
							  <img id="photoImage" src="imgProcessing.jsp?parameter=<%= i%>" width="100"  />
						</td>
					</tr>
					
					<tr>
						<td colspan="2">
							  글입니다.
						</td>
					</tr>
					
					<tr>
						<td colspan="2">
						<div style="float:left;">
						<input  type="button" onclick="goDel(<%=vo.getPostno()%>)"   value="신고">
						</div>
						<div style="float:right;">
						<input type="button" onclick="goDel(<%=vo.getPostno()%>)"   value="공감">
						<input type="button" onclick="goDel(<%=vo.getPostno()%>)"    value="댓글">
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

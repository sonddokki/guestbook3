<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.javaex.dao.GuestDao"%>
<%@ page import="com.javaex.vo.GuestVo"%>
<%@ page import="java.util.List"%>

<%
	//request의 어트리뷰트 영역에 있는 data를 꺼내온다.
	List<GuestVo> guestList = (List<GuestVo>)request.getAttribute("gList");

	System.out.println("addList");	
	System.out.println(guestList);	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<form action="GBC" method="get">
		<table border="1" width="540px">
		<input type="hidden" name="action" value="add">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value=""></td>
				<td>비밀번호</td>
				<td><input type="password" name="password" value=""></td>
			</tr>
			<tr>
				<td colspan="4"><textarea cols="72" rows="5" type="text"
						name="content" value=""></textarea></td>
			</tr>
			<tr>
				<td colspan="4"><button type="submit">등록</button></td>
			</tr>
		</table>
	</form>
	
	<br>

	<%for (int i = 0; i < guestList.size(); i++) {%>	
	<table border="1" width="540px">
		<tr>
			<td><%=guestList.get(i).getNo()%></td>
			<td><%=guestList.get(i).getName()%></td>
			<td><%=guestList.get(i).getRegDate()%></td>
			<td><a href="./GBC?action=dFrom&no=<%=guestList.get(i).getNo()%>">삭제</a></td>
		</tr>
		<tr>
			<td colspan="4"><%=guestList.get(i).getContent()%></td>
		</tr>
	</table>
	<br>
	<%}	%>


</body>
</html>
<%@ page isErrorPage="true"%>
<%@page import="in.co.rays.bean.UserBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		List list = (List) request.getAttribute("list");
		List list1 = (List) request.getAttribute("list1");
		String msg = (String) request.getAttribute("msg");
		Iterator it = list.iterator();
	%>
	<%-- <%@ include file="Header.jsp"%> --%>
	<form action="UserListCtl" method="post">

		<table>
			<a href="UserCtl">ADD USER</a> |
			<a href="UserListCtl">USER LIST</a>
			<hr>
			<td><input type="text" name="input"></td>
			<td><input type="submit" name="operation" value="delete">&nbsp;&nbsp;</td>
			<!-- <td><input type="submit" name="operation" value="add"></td> -->
			<td><select name="preload">
			<option>----------select----------</option>
					<%
						Iterator it1 = list1.iterator();

						while (it1.hasNext()) {
							UserBean bean1 = (UserBean) it1.next();
					%>
					<option value="<%=bean1.getLastName()%>"><%=bean1.getLastName()%></option>
					<%
						}
					%>
			</select>&nbsp;&nbsp;&nbsp;</td>
			<th><label>DOB</label></th>
			<td><input type="date" name="dob"></td>
			
			<td><input type="submit" name="operation" value="search"></td>
		</table>
		<h3><%=(msg!=null)?msg:"" %></h3>
		<table border="1">

			<tr>
				<th>Select</th>
				<th>Id</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>LoginId</th>
				<th>Password</th>
				<th>DOB</th>
				<th>Address</th>
				<!-- <th>Edit</th> -->
			</tr>
			<%
				while (it.hasNext()) {
					UserBean bean = (UserBean) it.next();
			%>
			<tr>
				<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
				<th><%=bean.getId()%></th>
				<th><%=bean.getFirstName()%></th>
				<th><%=bean.getLastName()%></th>
				<th><%=bean.getLoginId()%></th>
				<th><%=bean.getPassword()%></th>
				<th><%=bean.getDob()%></th>
				<th><%=bean.getAddress()%></th>
				<th><a href="UserCtl?id=<%=bean.getId()%>"> &nbsp;Edit </a></th>
			</tr>
			<%
				}
			%>
		</table>
		<table>

		</table>

	</form>
</body>
</html>
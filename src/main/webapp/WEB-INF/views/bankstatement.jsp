<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
body {

	background-size: cover;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	margin: 0;
	padding: 0;
}

h1 {
	font-size: 30px;
	color: blue;
	position: center;
}




</style>
<body>

<img src="/resources/images/LoginPage.jpg"/>
	<div align="right">
		<a href="logout">logout</a>
	</div>
	<div align="center">
		<h1>Bank Statement</h1>
		<h2>Account Type : ${accountDetails.accountType}</h2>
		<h2>Account Number :${accountDetails.accountNumber}</h2>
		<table>
			<tr>
				<th>Date</th>
				<th>Amount</th>
			</tr>


			<c:forEach items="${accountDetails.statementList}" var="statement">

				<tr>
					<td>${statement.statementDate}</td>
					<td>${statement.statementAmount}</td>
				</tr>

			</c:forEach>
		</table>
	</div>


	<h1></h1>
</body>
</html>
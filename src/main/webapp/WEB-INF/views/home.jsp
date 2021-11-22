<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customer Save Page</title>
<style>
body {
		center fixed;
	background-size: cover;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	margin: 0;
	padding: 0;
}

.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
</style>

</head>
<body>
	<div align="right">
		<a href="logout">logout</a>
	</div>
	<springForm:form method="POST" commandName="accountDetails"
		action="home.do">
<img src="/resources/images/LoginPage.jpg"/>
		<table>
			<tr>
				<td>Account ID :</td>
				<td><springForm:input path="accountID" /></td>
				<td><springForm:errors path="accountID" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Start Date(Optional):</td>
				<td><springForm:input path="startDate" /></td>
				<td><springForm:errors path="startDate" cssClass="error" /></td>

				<td>End Date (Optional):</td>
				<td><springForm:input path="endDate" /></td>
				<td><springForm:errors path="endDate" cssClass="error" /></td>
			</tr>

			<tr>
				<td>Amount (Min):</td>
				<td><springForm:input path="startAmount" /></td>
				<td><springForm:errors path="startAmount" cssClass="error" /></td>

				<td>Amount (Max):</td>
				<td><springForm:input path="endAmount" /></td>
				<td><springForm:errors path="endAmount" cssClass="error" /></td>
			</tr>


			<tr>
				<td colspan="3"><input type="submit" value="Submit"></td>
			</tr>





		</table>
		</table>

	</springForm:form>

</body>
</html>
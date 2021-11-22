<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nagarro Banking Services</title>

<style>
    body
        {
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
form1 {
   /* Size and position */
    width: 350px;
    margin: 70px auto 30px;
    padding: 10px;
    position: centre;

    /* Font styles */
    font-family: 'Raleway', 'Lato', Arial, sans-serif;
    color: black;
    text-shadow: 0 2px 1px rgba(0,0,0,0.3);
}




    </style>
    <div>
    
    <h1 align='center'>Nagarro Banking Services</h1>
    </div>
</head>
<body>

	<springForm:form method="POST" commandName="login"
		action="login.do" class="form1">
		<springForm:errors path="authFailure" cssClass="error" />
		<img src="/resources/images/loginPageBackground.png"/>
		<table >
			<tr>
				<td>Username:</td>
				<td><springForm:input path="name" /></td>
				<td><springForm:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><springForm:password path="password" /></td>
				<td><springForm:errors path="password" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Submit"></td>
			</tr>
		</table>

	</springForm:form>

</body>
</html>
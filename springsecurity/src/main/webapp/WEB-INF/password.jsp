<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="common/header.jsp">
	<jsp:param name="pageTitle" value="update password" />
</jsp:include>
</head>
<body>
	<h1>Change Password</h1>
	<form action="updatePwd.htm" method="post">
		<label for="oldpassword">Old Password</label>: <input id="oldpassword"
			name="oldpassword" size="20" maxlength="50" type="password" /> <br />
		<label for="password">New Password</label>: <input id="password"
			name="password" size="20" maxlength="50" type="password" /> <br />
		<input
			type="submit" value="提交" />
	</form>
</body>
</html>

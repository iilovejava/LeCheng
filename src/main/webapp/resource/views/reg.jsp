<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 显示错误信息 -->
	${msg}
	<form action="http://localhost:8080/user/reg" method="post">
		手机:<input type="text" name="userphone"><br>
		密码:<input type="text" name="userpassword"><br>
		   <input type="submit" value="提交">
	</form>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: lanou
  Date: 2018/7/29
  Time: 下午7:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
${msg }
<form action="http://localhost:8080/user/login" method="post">
    手机号:<input type="text" name="userphone"><br/>
    密码:<input type="text" name="userpassword"><br/>
    自动登录:<input type="checkbox" name="autologin"><br/>
    <input type="submit" value="提交">
</form>
</body>
</html>
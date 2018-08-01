<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>

</head>
<body>

<form action="http://localhost:8080/user/reg" method="post">
    姓名:<input type="text" name="username"><br>
    地区:<select>
            <option value="volvo">北京</option>
            <option value="saab">上海</option>
            <option value="opel">广州</option>
            <option value="audi">青岛</option>
        </select>
        <select>
            <option value="volvo">北京</option>
            <option value="saab">上海</option>
            <option value="opel">广州</option>
            <option value="audi">青岛</option>
        </select>
        <select>
            <option value="volvo">北京</option>
            <option value="saab">上海</option>
            <option value="opel">广州</option>
            <option value="audi">青岛</option>
        </select>
    <br/>
    联系地址:<input type="text" name="useraddress"><br/>
    性别:<input type="radio" name="sex" value="男"><span>男</span>
    <input type="radio" name="sex" value="女"><span>女</span><br/>
    手机:<input type="text" name="userphone"><br>
    出生日期:<input type="text" name="userbirthday"><br>
    QQ:<input type="text" name="userqq"><br>
    <input type="submit" value="保存">
</form>
</body>
</html>
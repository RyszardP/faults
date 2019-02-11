<%@ page language="java" contentType="text/html; charset=utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html">
    <meta charset="UTF-8">
    <title>Autorization</title>
</head>

<body>
<div align="center">
    <div>
        <form action="FrontController" id="form-page-registration">
            <input type="hidden" name="command" value="autorization">
            <br>
            <input type="text" id="login" name="login" size="20" maxlength="25" placeholder="Login">
            <br>
            <input type="password" id="password" name="password" size="20" maxlength="25" placeholder="Password">
            <br>
            <input type="submit" id="loginBtn" value="Login">
            <br>
            <input type="submit" id="registerBtn" value="Register">

        </form>
    </div>
</div>
</body>
</html>

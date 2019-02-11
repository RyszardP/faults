<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Admin page</title>
</head>
<body>
<div align="center">
   Workshop page
</div>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<div align="center">
    <title>Workshop page</title>
    <body>

    hi!!!
    <form action="FrontController" method="post">
        <input type="hidden" name="command" value="logout">
        <input type="submit" value="Log out">
    </form>

    <form action="FrontController">
        <input type="hidden" name="command" value="edit_password">
        <input type="submit" value="Edit password">
        <table class="table table-bordered">
            <tr>
                <td>
                    <input type="text" name="password" size="20" maxlength="25" placeholder="Пароль">
                </td>
            </tr>
        </table>
    </form>

    </body>
</div>
</body>

</html>
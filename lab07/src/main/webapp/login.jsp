
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Files Manager | Login</title>
</head>
<body>
    <h1>Login</h1>
    <form method="post">
        <label>Login:
            <input type="text" name="login"><br/>
        </label>

        <label>Password:
            <input type="password" name="password"><br/>
        </label>

        <button type="submit">Login</button>
    </form>
    <a href="register">
        <button>Registration</button>
</body>
</html>
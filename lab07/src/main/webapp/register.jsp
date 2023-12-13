<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Files Manager | Register</title>
</head>
<body>
    <h1>Register</h1>
    <form method="post">
        <label>Login:
            <input type="text" name="login"><br/>
        </label>

        <label>Email:
            <input type="text" name="email"><br/>
        </label>

        <label>Password:
            <input type="password" name="password"><br/>
        </label>

        <button type="submit">Register</button>
    </form>

</body>
</html>
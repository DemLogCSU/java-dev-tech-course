
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Files Manager</title>
</head>
<body>
    <h1>Files Manager</h1>
    <h1>${currentTime}</h1>
    <form method="post">
        <input type="submit" name="exit" value="Exit"/>
    </form>
    <h2>${currentPath}</h2>
    <c:forEach var="directory" items="${directories}">
        <a href="?path=${currentPath}/${directory}/" >${directory}/</a><br>
    </c:forEach>
    <c:forEach var="file" items="${files}">
        <a href="?path=${currentPath}/${file}/" >${file}</a><br>
    </c:forEach>
    <a href="?path=${currentPath.substring(0, currentPath.lastIndexOf("\\") + (currentPath.lastIndexOf("\\") != currentPath.indexOf("\\") ? 0 : 1))}">Up</a><br>
</body>
</html>
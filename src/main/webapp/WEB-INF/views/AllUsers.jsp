<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:useBean id="users" scope="request" type="java.util.List<com.akukhtin.ishop.model.User>"/>
<jsp:useBean id="greeting" scope="request" type="java.lang.String"/>
<%--
  Created by IntelliJ IDEA.
  User: akukhtin
  Date: 9/24/19
  Time: 4:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
Hello, ${greeting}, welcome to the all user page!
<br>
Users:

<table border="2">
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                <c:out value="${user.id}" />
            </td>
            <td>
                <c:out value="${user.login}" />
            </td>
            <td>
                <c:out value="${user.name}" />
            </td>
            <td>
                <c:out value="${user.surname}" />
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/servlet/deleteUser?user_id=${user.id}">DELETE</a>
                <a href="/ishop_war_exploded/servlet/deleteUser?user_id=${user.id}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="orders" scope="request" type="java.util.List<com.akukhtin.ishop.model.Order>"/>
<%--
  Created by IntelliJ IDEA.
  User: akukhtin
  Date: 9/24/19
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Orders</title>
</head>
<body>
<p>Orders:</p>

<table border="2">
    <tr>
        <th>ID</th>
        <th>UserID</th>
        <th>Delete</th>
        <th>Show Items</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.id}" />
            </td>
            <td>
                <c:out value="${order.userId}" />
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/servlet/deleteOrder?order_id=${order.id}">DEL</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/servlet/getItemsFromOrder?order_id=${order.id}">SHOW</a>
            </td>
        </tr>
    </c:forEach>
</table>

<br>
<a href="${pageContext.request.contextPath}/servlet/allItems"><button type="submit" class="registerbtn">All Items</button></a>

</body>
</html>

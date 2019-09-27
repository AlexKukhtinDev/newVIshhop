<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:useBean id="itemsInBucket" scope="request" type="java.util.List<com.akukhtin.ishop.model.Item>"/>
<%--
  Created by IntelliJ IDEA.
  User: akukhtin
  Date: 9/24/19
  Time: 5:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket</title>
</head>
<body>
Items in bucket:

<table border="2">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="item" items="${itemsInBucket}">
        <tr>
            <td>
                <c:out value="${item.id}" />
            </td>
            <td>
                <c:out value="${item.name}" />
            </td>
            <td>
                <c:out value="${item.price}" />
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/servlet/deleteFromBucket?item_id=${item.id}">DEL</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="${pageContext.request.contextPath}/servlet/completeOrder"><button type="submit" class="registerbtn">Complete Order</button></a>
<br>
<a href="${pageContext.request.contextPath}/servlet/allItems"><button type="submit" class="registerbtn">All Items</button></a>
</body>
</html>

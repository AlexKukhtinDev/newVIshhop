<%--
  Created by IntelliJ IDEA.
  User: akukhtin
  Date: 9/24/19
  Time: 4:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Item</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style><%@include file="css/style.css"%></style>
</head>
<body>
Let's create a new user!
<form action="${pageContext.request.contextPath}/servlet/addItem" method="post">
<form action="/ishop_war_exploded/servlet/addItem" method="post">
    <div class="container">
        <%--@declare id="user_surname"--%><%--@declare id="user_name"--%><%--@declare id="psw-repeat"--%><%--@declare id="psw"--%><%--@declare id="login"--%><%--@declare id="price"--%><%--@declare id="model"--%><%--@declare id="item_name"--%>
        <h2>Create item</h2>
        <p>Please fill in this form to create an item.</p>
        <hr>
        <label for="item_name"><b>Name</b></label>
        <br>
        <input type="text" placeholder="Enter name" name="item_name" required>
        <br>
        <label for="model"><b>Model</b></label>
        <br>
        <input type="text" placeholder="Enter model" name="model" required>
        <br>
        <label for="price"><b>Price</b></label>
        <br>
        <input type="text" placeholder="Enter price" name="price" required>
        <hr>
        <button type="submit" class="registerbtn">Add Item</button>
    </div>
</form>
</body>
</html>

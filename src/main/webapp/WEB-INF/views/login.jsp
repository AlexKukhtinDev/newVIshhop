<%--
  Created by IntelliJ IDEA.
  User: akukhtin
  Date: 9/24/19
  Time: 5:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
Login page
<form action="/ishop_war_exploded/login" method="post">
    <div class="container">
        <%--@declare id="user_surname"--%><%--@declare id="user_name"--%><%--@declare id="psw-repeat"--%><%--@declare id="psw"--%><%--@declare id="login"--%>
        <h2>Login</h2>
        <p>Please fill in this form to sign into account.</p>
        <hr>
        <p>${errorMessage}</p>
        <label for="login"><b>Login</b></label>
        <br>
        <input type="text" placeholder="Enter Login" name="login" required>
        <br>
        <label for="psw"><b>Password</b></label>
        <br>
        <input type="password" placeholder="Enter Password" name="psw" required>

        <button type="submit" class="registerbtn">Login</button>
    </div>
</form>
<br>
If you don't have account:
<a href="${pageContext.request.contextPath}/registration">Registration</a>
<a href="/ishop_war_exploded/registration">Registration</a>
</body>
</html>

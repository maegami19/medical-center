<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: miegami
  Date: 6/29/2019
  Time: 6:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
    <%@include file="jspf/navbar.jspf"%>

    <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

    <form method="POST">
        <div align="center" class="row">
            <div class="col">
            </div>
            <div class="col">
            </div>
            <div class="col">
                <form method="post">
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" placeholder="Username" name="username" id="username">
                    <br/>
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" placeholder="Password" name="password" id="password">
                    <c:if test="${requestScope.reply != null}">
                        ${requestScope.reply}
                        <br/>
                    </c:if>
                    <br/>
                    <button type="submit" class="btn btn-primary" id="submit-form">Submit</button>
                </form>
            </div>
            <div class="col">
            </div>
            <div class="col">
            </div>
        </div>
    </form>
    <script src="/js/login.js"></script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: miegami
  Date: 7/19/2019
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="${language}">
<head>
    <title>Users</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
</head>
<body>
<%@include file="jspf/navbar.jspf" %>
<br/>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th><fmt:message key="users.userid"/></th>
        <th><fmt:message key="users.username"/></th>
        <th><fmt:message key="users.role"/></th>
        <th><fmt:message key="users.email"/></th>
        <th><fmt:message key="users.delete"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="users" items="${list_users}">
        <tr>
            <th>${users.id}</th>
            <td>${users.username}</td>
            <td>${users.role}</td>
            <td>${users.email}</td>
            <td>
                <form method="post">
                    <button name="delete" class="btn btn-primary" id="delete-user-button" value="${users.username}">
                        <fmt:message key="users.delete"/>
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
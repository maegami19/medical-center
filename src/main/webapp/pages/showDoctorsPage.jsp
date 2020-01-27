<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: miegami
  Date: 7/4/2019
  Time: 6:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="${language}">
<head>
    <title>Doctors</title>
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
        <th><fmt:message key="showdoc.docid"/></th>
        <th><fmt:message key="showdoc.firstname"/></th>
        <th><fmt:message key="showdoc.lastname"/></th>
        <th><fmt:message key="showdoc.patients"/></th>
        <th><fmt:message key="showdoc.category"/></th>
        <th><fmt:message key="showdoc.username"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="doctors" items="${list_doc}">
        <tr>
            <th scope="row">${doctors.id}</th>
            <td>${doctors.firstname}</td>
            <td>${doctors.lastname}</td>
            <td>${doctors.countPatient}</td>
            <td>${doctors.category}</td>
            <td>${doctors.username}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

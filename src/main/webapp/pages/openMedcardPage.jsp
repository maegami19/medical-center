<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: miegami
  Date: 7/6/2019
  Time: 3:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="${language}">
<head>
    <title>Open medcard</title>
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
<br/><br/><br/>

<div align="center" class="row">
    <div class="col">
    </div>
    <div class="col">
        <form method="post">
            <label for="patient"><fmt:message key="open.medcard.topatient"/>:</label>
            <select name="to_patient" id="patient" class="form-control">
                <c:choose>
                    <c:when test="${sessionScope.user.role == 'doctor'}">
                        <c:forEach var="patients" items="${patients}">
                            <option value="${patients.id}">${patients.firstname} ${patients.lastname}(Birthday: ${patients.birthday})
                                [ID: ${patients.id}]
                            </option>
                        </c:forEach>
                    </c:when>
                    <c:when test="${sessionScope.user.role == 'nurse'}">
                        <c:forEach var="allpatients" items="${allpatients}">
                            <option value="${allpatients.id}">${allpatients.firstname} ${allpatients.lastname}(Birthday: ${allpatients.birthday})
                                [ID: ${allpatients.id}]
                            </option>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </select><br/>
            <label for="type"><fmt:message key="close.medcard.type"/>:</label>
            <select name="type" class="form-control" id="type">
                <option value="procedure"><fmt:message key="open.medcard.procedure"/></option>
                <c:choose>
                    <c:when test="${sessionScope.user.role == 'doctor'}">
                        <option value="operation"><fmt:message key="open.medcard.operation"/></option>
                    </c:when>
                </c:choose>
                <option value="preparations"><fmt:message key="open.medcard.preparations"/></option>
            </select><br/>
            <label for="description"><fmt:message key="open.medcard.description"/>:</label>
            <input class="form-control" type="text" id="description" name="description"><br/>
            <button class="btn btn-primary"><fmt:message key="password.submit"/></button>
        </form>
    </div>
    <div class="col">
    </div>
</div>
<script src="/js/openMedcard.js"></script>
</body>
</html>

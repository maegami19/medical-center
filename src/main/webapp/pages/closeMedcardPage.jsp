<%--
  Created by IntelliJ IDEA.
  User: miegami
  Date: 7/6/2019
  Time: 5:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="${language}">
<head>
    <title>Close medcard</title>
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
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<div align="center" class="row">
    <div class="col">
    </div>
    <div class="col">
        <form method="post">
            <label for="medcard"><fmt:message key="close.medcard.select"/>:</label>
            <select name="medcard" id="medcard" class="form-control">
                <c:forEach var="medcards" items="${medcards}">
                    <option value="${medcards.id}"><fmt:message key="close.medcard.patient.id"/>: ${medcards.toPatient}
                        (<fmt:message key="close.medcard.type"/>: ${medcards.type}, <fmt:message
                                key="close.medcard.description"/>: ${medcards.description})
                    </option>
                </c:forEach>
            </select><br/>
            <label for="diagnosis"><fmt:message key="close.medcard.diagnosis"/>:</label>
            <input class="form-control" type="text" id="diagnosis" name="diagnosis"><br/>
            <button class="btn btn-primary"><fmt:message key="close.medcard.close"/></button>
        </form>
    </div>
    <div class="col">
    </div>
</div>
<script src="/js/closeMedcard.js"></script>
</body>
</html>

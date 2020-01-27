<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: miegami
  Date: 6/30/2019
  Time: 10:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="${language}">
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<%@include file="jspf/navbar.jspf" %>

<br/><br/><br/><br/>
<div class="user-registration-form">
    <div align="center" class="row">
        <div class="col">
        </div>
        <div class="col">
            <label for="user-role"><fmt:message key="register.role"/></label>
            <select name="role" id="user-role" class="form-control">
                <option value="admin"><fmt:message key="register.admin"/></option>
                <option value="doctor"><fmt:message key="register.doctor"/></option>
                <option value="nurse"><fmt:message key="register.nurse"/></option>
                <option value="patient"><fmt:message key="register.patient"/></option>
            </select><br/>
        </div>
        <div class="col">
        </div>
    </div>

    <div align="center" class="row">
        <div class="col">
        </div>
        <div class="col">
            <label for="login"><fmt:message key="register.username"/></label>
            <input type="text" id="login" class="form-control"><br/>
            <label for="firstname"><fmt:message key="register.firstname"/></label>
            <input type="text" id="firstname" class="form-control"><br/>
            <label for="lastname"><fmt:message key="register.lastname"/></label>
            <input type="text" id="lastname" class="form-control"><br/>
            <label for="email"><fmt:message key="register.email"/></label>
            <input type="text" id="email" class="form-control"><br/>
            <div class="doctor-inputs" style="display: none;">
                <label for="doctor-category"><fmt:message key="register.category"/></label>
                <select name="category" id="doctor-category" class="form-control">
                    <c:forEach var="categories" items="${list}">
                        <option value="${categories}">${categories}</option>
                    </c:forEach>
                </select><br/>
            </div>
            <div class="patient-inputs" style="display: none;">
                <label for="birthday"><fmt:message key="register.birthday"/></label>
                <input type="date" id="birthday" class="form-control"><br/>
                <label for="patient-doctor"><fmt:message key="register.doctor.patient"/></label>
                <select name="doctor_id" id="patient-doctor" class="form-control">
                    <c:forEach var="doctors" items="${list_doctor}">
                        <option value="${doctors.id}">${doctors.firstname} ${doctors.lastname}(${doctors.category})</option>
                    </c:forEach>
                </select><br/>
            </div>
            <c:if test="${requestScope.message != null}">
                ${requestScope.message}
            </c:if>
            <button id="submit-registration" class="btn btn-primary"><fmt:message key="password.submit"/></button>
        </div>
        <div class="col">
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="/js/registration.js"></script>
</body>
</html>

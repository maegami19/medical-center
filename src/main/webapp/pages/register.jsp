<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: miegami
  Date: 6/30/2019
  Time: 10:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <%@include file="jspf/navbar.jspf"%>

    <br/><br/><br/><br/>
    <div class="user-registration-form">
        <div align="center" class="row">
            <div class="col">
            </div>
            <div class="col">
                <label for="user-role">Role</label>
                <select name="role" id="user-role" class="form-control">
                    <option value="admin">Admin</option>
                    <option value="doctor">Doctor</option>
                    <option value="nurse">Nurse</option>
                    <option value="patient">Patient</option>
                </select><br/>
            </div>
            <div class="col">
            </div>
        </div>

        <div align="center" class="row">
            <div class="col">
            </div>
            <div class="col">
                <label for="login">Login</label>
                <input type="text" id="login" class="form-control"><br/>
                <label for="firstname">First name</label>
                <input type="text" id="firstname" class="form-control"><br/>
                <label for="lastname">Last name</label>
                <input type="text" id="lastname" class="form-control"><br/>
                <label for="email">Email</label>
                <input type="text" id="email" class="form-control"><br/>
                <div class="doctor-inputs" style="display: none;">
                    <label for="doctor-category">Doctor category</label>
                    <select name="category" id="doctor-category" class="form-control">
                        <c:forEach var="categories" items="${list}">
                            <option value="${categories}">${categories}</option>
                        </c:forEach>
                    </select><br/>
                </div>
                <div class="patient-inputs" style="display: none;">
                    <label for="birthday">Birthday</label>
                    <input type="date" id="birthday" class="form-control"><br/>
                    <label for="patient-doctor">Doctor for patient</label>
                    <select name="doctor_id" id="patient-doctor" class="form-control">
                        <c:forEach var="doctors" items="${list_doctor}">
                            <option value="${doctors.id}">${doctors.firstname} ${doctors.lastname}(${doctors.category})</option>
                        </c:forEach>
                    </select><br/>
                </div>
                    <button id="submit-registration" class="btn btn-primary">Submit</button>
            </div>
            <div class="col">
            </div>
        </div>
    </div>



    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="/js/signin.js"></script>
</body>
</html>

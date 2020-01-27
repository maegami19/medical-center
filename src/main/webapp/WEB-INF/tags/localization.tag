<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="dropdown-menu" aria-labelledby="navbarDropdown">
    <a class="dropdown-item"
       href="${requestScope['javax.servlet.forward.request_uri']}?language=ru">Russian</a>
    <a class="dropdown-item"
       href="${requestScope['javax.servlet.forward.request_uri']}?language=en">English</a>
</div>
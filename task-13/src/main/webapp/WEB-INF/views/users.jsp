<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>

    <jsp:include page="fragments/head.jsp">
        <jsp:param name="title" value="User profile"/>
    </jsp:include>

<body>
<%@ include file="/WEB-INF/views/fragments/header.jsp" %>

<section class="container">
    <div class="row">
        <div class="col-md-5">
            <h1>User profile:</h1>
            <h4>UID # - ${user.id}</h4>
            <h4>User name - ${user.name}</h4>
            <h4>Surname - ${user.surname}</h4>
            <h4>Login - <sec:authentication property="principal.username"/></h4>
            <h4>Email - ${user.email}</h4>
            <h4>Roles - <sec:authentication property="principal.authorities"/></h4>
            <h4>Date of birthday - ${user.birthday}</h4>
        </div>
    </div>
</section>

<%@ include file="/WEB-INF/views/fragments/footer.jsp" %>
</body>
</html>

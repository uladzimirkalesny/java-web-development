<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<sec:authorize access="isAuthenticated()">
    <c:redirect url="/books"/>
</sec:authorize>

<html lang="en">

<jsp:include page="fragments/head.jsp">
    <jsp:param name="title" value="Welcome Page"/>
</jsp:include>

<body>

<%@ include file="/WEB-INF/views/fragments/header.jsp" %>
<%@ include file="/WEB-INF/views/fragments/content.jsp" %>
<%@ include file="/WEB-INF/views/fragments/footer.jsp" %>

</body>
</html>

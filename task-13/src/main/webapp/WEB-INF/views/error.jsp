<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<jsp:include page="fragments/head.jsp">
    <jsp:param name="title" value="Error Page"/>
</jsp:include>
<body>
<%@ include file="/WEB-INF/views/fragments/header.jsp" %>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1 class="alert alert-danger">${message}</h1>
        </div>
    </div>
</section>
<section>
    <div class="container">
        <p>
            <a href="<c:url value="/books" />" class="btn btn-primary">Get Back</a>
        </p>
    </div>
</section>

<%@ include file="/WEB-INF/views/fragments/footer.jsp" %>
</body>
</html>

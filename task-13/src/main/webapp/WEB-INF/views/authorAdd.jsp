<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">

<jsp:include page="fragments/head.jsp">
    <jsp:param name="title" value="Add Author Page"/>
</jsp:include>

<body>

<%@ include file="/WEB-INF/views/fragments/header.jsp" %>

<section class="container">
    <form:form action="/authors/add" method="POST" modelAttribute="author" class="form-horizontal">

        <fieldset>
            <input type="hidden" name="bookId" value="${bookId}"/>
            <legend style="text-align: start">Add author</legend>
            <div class="form-group">
                <label class="control-label col-lg-2" for="name">Name</label>
                <div class="col-lg-10">
                    <form:input id="name" path="name"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="surname">Surname</label>
                <div class="col-lg-10">
                    <form:input id="surname" path="surname"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" id="btnAdd" class="btn btn-primary" value="Add"/>
                </div>
            </div>
        </fieldset>
    </form:form>
</section>

<%@ include file="/WEB-INF/views/fragments/footer.jsp" %>
</body>
</html>

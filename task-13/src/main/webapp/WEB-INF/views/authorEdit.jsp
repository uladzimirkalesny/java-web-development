<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<jsp:include page="fragments/head.jsp">
    <jsp:param name="title" value="Author Edit Page"/>
</jsp:include>

<body>

<%@ include file="/WEB-INF/views/fragments/header.jsp" %>

<section class="container" >
    <form:form action="/authors/${author.id}/edit" method="POST" modelAttribute="author" class="form-horizontal">
        <fieldset>
            <legend style="text-align: start">Edit author</legend>
            <input type="hidden" name="id" value="${author.id}"/>
            <div class="form-group">
                <label class="control-label col-lg-2" for="name">Name</label>
                <div class="col-lg-10">
                    <form:input id="name" path="name" value="${author.name}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="surname">Surname</label>
                <div class="col-lg-10">
                    <form:input id="surname" path="surname" value="${author.surname}"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" id="btnAdd" class="btn btn-primary" value="Save Changes"/>
                </div>
            </div>
        </fieldset>
    </form:form>
    <form action="<spring:url value="/authors/${author.id}/delete?bookId=${bookId}"/>" name="Remove Author" method="POST">
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <input type="hidden" name="id" value="${author.id}"/>
                <input type="submit" id="btnDelete" class="btn btn-danger" value="Remove Author"/>
            </div>
        </div>
    </form>
</section>

<%@ include file="/WEB-INF/views/fragments/footer.jsp" %>
</body>
</html>

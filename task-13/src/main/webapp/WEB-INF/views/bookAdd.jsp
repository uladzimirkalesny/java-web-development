<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

<jsp:include page="fragments/head.jsp">
    <jsp:param name="title" value="Add Book Page"/>
</jsp:include>

<body>

<%@ include file="/WEB-INF/views/fragments/header.jsp" %>

<section class="container">
    <form:form action="/books/add" method="POST" modelAttribute="book" class="form-horizontal">
        <fieldset>
            <legend style="text-align: start">Add new book</legend>
            <div class="form-group">
                <label class="control-label col-lg-2" for="title">Title</label>
                <div class="col-lg-10">
                    <form:input id="title" path="title"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="description">Description</label>
                <div class="col-lg-10">
                    <form:input id="description" path="description"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="unitsInStock">Units In Stock</label>
                <div class="col-lg-10">
                    <form:input id="unitsInStock" path="unitsInStock"/>
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

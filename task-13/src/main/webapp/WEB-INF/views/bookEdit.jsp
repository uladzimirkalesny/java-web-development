<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">

<jsp:include page="fragments/head.jsp">
    <jsp:param name="title" value="Edit Book Page"/>
</jsp:include>

<body>

<%@ include file="/WEB-INF/views/fragments/header.jsp" %>

<section class="container" >
    <form:form action="/books/${book.id}/edit" method="POST" modelAttribute="book" class="form-horizontal">
        <fieldset>
            <legend style="text-align: start">Edit book</legend>
            <input type="hidden" name="id" value="${book.id}"/>
            <div class="form-group">
                <label class="control-label col-lg-2" for="title">Title</label>
                <div class="col-lg-10">
                    <form:input id="title" path="title" value="${book.title}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="description">Description</label>
                <div class="col-lg-10">
                    <form:input id="description" path="description" value="${book.description}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="unitsInStock">Units In Stock</label>
                <div class="col-lg-10">
                    <form:input id="unitsInStock" path="unitsInStock" value="${book.unitsInStock}"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-10">
                    <c:forEach items="${book.authors}" var="author">
                        <p>Author ${author.name} ${author.surname}
                            <a href="<spring:url value="/authors/${author.id}/edit?bookId=${book.id}"/>" class="btn btn-warning btn-large">Edit Author</a>
                        </p>
                    </c:forEach>
                </div>
            </div>
            <div class="form-group">
                <p><a href="<spring:url value="/books/${book.id}/authors/add"/>" class="btn btn-info btn-large">Add Author</a></p>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" id="btnAdd" class="btn btn-primary" value="Save Changes"/>
                </div>
            </div>
        </fieldset>
    </form:form>
</section>

<%@ include file="/WEB-INF/views/fragments/footer.jsp" %>
</body>
</html>

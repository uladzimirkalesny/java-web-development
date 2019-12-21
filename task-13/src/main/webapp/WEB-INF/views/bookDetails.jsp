<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<jsp:include page="fragments/head.jsp">
    <jsp:param name="title" value="Book Details Page"/>
</jsp:include>

<body>

<%@ include file="/WEB-INF/views/fragments/header.jsp" %>

<div class="container">
    <h1>Book</h1>
</div>
<section class="container">
    <div class="row">
        <div class="col-md-5">
            <c:if test="${book.id > 2}">
                <img src="<c:url value="/resources/book-images/random.jpg" />" alt="image"
                     style="width:90%"/>
            </c:if>
            <c:if test="${book.id <= 2}">
                <img src="<c:url value="/resources/book-images/${book.id}.jpg" />" alt="image"
                     style="width:90%"/>
            </c:if>
        </div>
        <div class="col-md-5">
            <h3>${book.title}</h3>
            <p>${book.description}</p>
            <p>Available <strong>${book.unitsInStock}</strong> units in stock</p>
            <c:forEach items="${book.authors}" var="author">
                <p>${author.name} ${author.surname}</p>
            </c:forEach>
            <c:if test="${book.id <= 2}">
                <p><a href="<spring:url value="/resources/pdf/${book.id}.pdf"/>" class="btn btn-success btn-large"
                      target="_blank">Read Book Online</a></p>
            </c:if>
            <p><a href="<spring:url value="/bucket/add/${book.id}"/>" class="btn btn-warning btn-large">Put book into
                the bucket</a></p>
            <security:authorize access="hasRole('ROLE_ADMIN') ">
                <a href="<spring:url value="/books/${book.id}/edit"/>" class="btn btn-info btn-large">Edit Book</a>
                <a href="<spring:url value="/books/${book.id}/delete"/>" class="btn btn-danger btn-large">Remove
                    Book</a>
            </security:authorize>
        </div>
    </div>
</section>

<%@ include file="/WEB-INF/views/fragments/footer.jsp" %>
</body>
</html>

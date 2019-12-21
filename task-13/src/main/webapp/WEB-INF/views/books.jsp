<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>

<jsp:include page="fragments/head.jsp">
    <jsp:param name="title" value="Books page"/>
</jsp:include>

<body>
<%@ include file="/WEB-INF/views/fragments/header.jsp" %>

<h1 style="text-align: center">The available books in our library</h1>

<section class="container">
    <div class="row">
        <c:forEach items="${books}" var="book">
            <div class="col-sm-6 col-md-3">
                <div class="thumbnail">
                    <div class="caption">
                        <c:if test="${book.id > 2}">
                            <img src="<c:url value="/resources/book-images/random.jpg" />" alt="image"
                                 style="width:90%"/>
                        </c:if>
                        <c:if test="${book.id <= 2}">
                            <img src="<c:url value="/resources/book-images/${book.id}.jpg" />" alt="image"
                                 style="width:90%"/>
                        </c:if>
                        <p>
                        <h3>${book.title}</h3></p>
                        <p>${book.description}</p>
                        <c:forEach items="${book.authors}" var="author">
                            <p>${author.name} ${author.surname}</p>
                        </c:forEach>
                        <p>
                            <a href="<spring:url value="/books/${book.id}"/>" class="btn btn-primary">Details</a>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>
        <security:authorize access="hasRole('ROLE_ADMIN') ">
            <p>
                <a href="<spring:url value="/books/add"/>" class="btn btn-success">Add Book</a>
            </p>
        </security:authorize>
    </div>
</section>

<%@ include file="/WEB-INF/views/fragments/footer.jsp" %>
</body>
</html>

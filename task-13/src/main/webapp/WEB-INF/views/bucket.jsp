<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/head.jsp">
    <jsp:param name="title" value="Bucket Page"/>
</jsp:include>
<body>
<%@ include file="/WEB-INF/views/fragments/header.jsp" %>
<section class="container">
    <div class="row">
        <h3>Bucket</h3>
    </div>
</section>
<c:if test="${not empty sessionScope.bucket}">
    <section class="container">
        <div class="row">
            <c:forEach items="${sessionScope.bucket}" var="book">
                <div class="col-sm-6 col-md-3">
                    <div class="thumbnail">
                        <div class="caption">
                            <img src="<c:url value="/resources/book-images/${book.id}.jpg" />" alt="image"
                                 style="width:20%"/>
                            <p>${book.title}</p>
                            <p>${book.description}</p>
                            <c:forEach items="${book.authors}" var="author">
                                <p>${author.name} ${author.surname}</p>
                            </c:forEach>
                            <p>
                                <a href="<spring:url value="/bucket/remove/${book.id}"/>" class="btn btn-warning">Remove
                                    book</a>
                            </p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>
</c:if>
<section class="container">
    <div class="row">
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
                <div class="caption">
                    <p>
                        <a href="<spring:url value="/books"/>" class="btn btn-primary">Get Back</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="/WEB-INF/views/fragments/footer.jsp" %>

</body>
</html>

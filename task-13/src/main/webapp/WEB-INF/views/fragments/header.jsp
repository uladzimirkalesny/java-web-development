<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header>
    <div class="container">
        <div class="row">
            <div class="col-md-4 logotype">
                <a href="<c:url value="/books"/>">
                    <img src="<c:url value="/resources/img/logo.png"/>" alt="Logotype" class="img-responsive">
                </a>
            </div>
            <div class="col-md-4 text-center descript">
                <h4>Your individual Web Library</h4>
                <h5>Choose your books</h5>
            </div>
            <div class="col-md-4 buttons">
                <security:authorize access="isAuthenticated() ">
                    <a class="btn btn-outline-secondary float-right" role="button" href="<c:url value="/logout"/>">Logout</a>
                    <a class="btn btn-outline-success float-right mr-4" role="button" href="<c:url value="/bucket/show"/>">Show Bucket</a>
                    <a class="btn btn-outline-info float-right mr-4" role="button" href="<c:url value="/users/uid"/>">User Profile</a>
                </security:authorize>
                <security:authorize access="isAnonymous()">
                    <button type="button" class="btn btn-primary float-right" data-toggle="modal"
                            data-target="#signInModal">Sign in
                    </button>
                </security:authorize>
            </div>
        </div>
    </div>
</header>

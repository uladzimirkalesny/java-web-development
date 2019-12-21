<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section id="start">
    <div class="container">
        <div class="row">
            <div class="logotype-color mx-auto d-block">
                <img src="<c:url value="/resources/img/logo.png"/>" alt="Logotype">
            </div>
        </div>
        <div class="row text-center about-app">
            <h1 style="text-align: center">
                <span>${greeting}</span>
            </h1>
        </div>
        <div class="row">
            <div class="signup-again mx-auto d-block">
                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#signUpModal">
                    First
                    time? Create account
                </button>
            </div>
        </div>
        <div class="row">
            <div class="signin-again mx-auto d-block">
                <a href="#" data-toggle="modal" data-target="#signInModal">Do
                    you
                    have one? Please login.</a>
            </div>
        </div>
    </div>
</section>
<%@ include file="signInModal.jsp" %>
<%@ include file="signUpModal.jsp" %>


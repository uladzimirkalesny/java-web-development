<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="signUpModal" tabindex="-1" role="dialog" aria-labelledby="signUpModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-md" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="signUpModalLabel">Sign up</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="<c:url value="/signup"/>" name="reg-form" method="post">
                    <div class="form-group">
                        <label for="inputRegLogin">Login</label>
                        <input type="text" name="login" class="form-control" id="inputRegLogin" placeholder="Enter login">
                    </div>
                    <div class="form-group">
                        <label for="inputRegPassword">Password</label>
                        <input type="password" name="password" class="form-control" id="inputRegPassword" placeholder="Enter password">
                    </div>
                    <div class="form-group">
                        <label for="inputRegRepeatPassword">Repeat Password</label>
                        <input type="password" name="repeatPassword" class="form-control" id="inputRegRepeatPassword" placeholder="Enter password">
                    </div>
                    <div class="form-group">
                        <label for="inputRegEmail">Email address</label>
                        <input type="email" name="email" class="form-control" id="inputRegEmail" aria-describedby="emailHelp" placeholder="Enter email">
                    </div>
                    <div class="form-group">
                        <label for="inputUserName">Name</label>
                        <input type="text" name="username" class="form-control" id="inputUserName" placeholder="Enter username">
                    </div>
                    <div class="form-group">
                        <label for="inputUserSurname">Surname</label>
                        <input type="text" name="surname" class="form-control" id="inputUserSurname" placeholder="Enter surname">
                    </div>
                    <div class="form-group">
                        <label for="inputDateBirthday">Birthday</label>
                        <input type="date" name="date" class="form-control" id="inputDateBirthday" placeholder="Enter birthday date">
                    </div>

                    <button type="submit" class="btn btn-success float-right mr-1">Sign up</button>
                    <button type="button" class="btn btn-danger float-right mr-1" data-dismiss="modal">Close</button>
                </form>
            </div>
        </div>
    </div>
</div>

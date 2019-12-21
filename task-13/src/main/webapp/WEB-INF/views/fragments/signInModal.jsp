<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="signInModal" tabindex="-1" role="dialog" aria-labelledby="signInModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-md" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="signInModalLabel">Sign in</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="<c:url value="/login"/>" method="post">
                    <div class="form-group">
                        <label for="login">Login</label>
                        <input type="text" name="login" class="form-control" id="login" placeholder="Enter login">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" name="password" class="form-control" id="password" placeholder="Enter password">
                    </div>
                    <div class="form-group">
                        <input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
                        <label class="label-checkbox100" for="ckb1">
                            Remember-me
                        </label>
                    </div>
                    <button type="submit" class="btn btn-primary mr-1 float-right">Login</button>
                    <button type="button" class="btn btn-danger mr-1 float-right" data-dismiss="modal">Close</button>
                </form>
            </div>
        </div>
    </div>
</div>

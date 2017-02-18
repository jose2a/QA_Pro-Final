<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"></jsp:include>

<div class="container">
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<form id="loginForm" action="CheckLogin" method="POST">
				<div class="form-group">
					<label for="email" class="control-label">Email</label> <input
						type="text" class="form-control" name="email" value="" required=""
						title="Please enter your email" placeholder="email"> <span
						class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="password" class="control-label">Password</label> <input
						type="password" class="form-control" name="password"
						placeholder="password" value="" required=""
						title="Please enter your password"> <span
						class="help-block"></span>
				</div>

				<c:if test="${not empty message }">
					<p class="text-danger">${message}</p>
				</c:if>

				<button type="submit" value="login" name="submit"
					class="btn btn-success">Login</button>
					 or <a href="./register.jsp">Register</a>
			</form>
		</div>
	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
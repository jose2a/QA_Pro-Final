<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"></jsp:include>

<div class="container">
	<div class="row">
		<div class="col-md-4 col-xs-4">

			<h2>Register</h2>

			<br />


			<c:if test="${not empty message }">
				<span style="color: #ff1100"> ${message} </span>
			</c:if>

			<form action="SaveUser" method="POST">

				<div class="form-group">
					<label for="username">Username</label> <input type="text"
						id="username" name="username" class="form-control"></input>
				</div>

				<div class="form-group">
					<label for="email">Email</label> <input type="text" id="email"
						name="email" class="form-control"></input>
				</div>

				<div class="form-group">
					<label for="name">Name</label> <input type="text" id="name"
						name="name" class="form-control"></input>
				</div>

				<div class="form-group">
					<label for="surname">Surname</label> <input type="text"
						id="surname" name="surname" class="form-control"></input>
				</div>

				<div class="form-group">
					<label for="password">Password</label> <input type="password"
						id="password" name="password" class="form-control"></input>
				</div>

				<input type="submit" value="Register" class="btn btn-primary">
			</form>

		</div>
	</div>

</div>
<jsp:include page="footer.jsp"></jsp:include>
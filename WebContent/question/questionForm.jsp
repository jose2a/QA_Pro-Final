<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../header.jsp"></jsp:include>

<div class="container">
	<div class="row">
		<div class="col-lg-6 col-md-6 col-xs-6">

			<c:choose>
				<c:when test="${question.id eq 0}">
					<h2>Add Question</h2>
				</c:when>
				<c:otherwise>
					<h2>Edit Question</h2>
				</c:otherwise>
			</c:choose>

			<br />

			<c:if test="${not empty message }">
				<span style="color: #ff1100"> ${message} </span>
			</c:if>

			<form id="questionForm" action="SaveQuestion" method="POST">

				<div class="form-group">
					<label for="qText">Question</label>
					<textarea id="qText" name="qText" class="form-control">${question.text}</textarea>
				</div>

				<div class="checkbox">
					<c:choose>
						<c:when test="${question.active}">
							<label> <input type="checkbox" name="active" id="active"
								value="true" checked="checked" /> Active?
							</label>
						</c:when>
						<c:otherwise>
							<label> <input type="checkbox" name="active" id="active"
								value="true" /> Active?
							</label>
						</c:otherwise>
					</c:choose>
				</div>

				<input type="hidden" name="qId" id="qId" value="${question.id}">

				<input type="submit" value="Save" class="btn btn-primary">
			</form>

		</div>
	</div>

</div>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.js"></script>
<script>
	$(document).ready(function() {

		$("#questionForm").validate({
			rules : {
				qText : {
					required : true,
					minlength : 4,
					maxlength : 100,
				}
			},
			messages : {
				qText : {
					required : "Please enter a name",
					minlength :"Minimum {0} characters required!",
					maxlength : "Maximum {0} characters allowed!"
				}
			},
			submitHandler : function() {
				//e.preventDefault();
				return true;
			}
		});

	});
</script>

<jsp:include page="../footer.jsp"></jsp:include>
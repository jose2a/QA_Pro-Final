<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:include page="../header.jsp"></jsp:include>

<div class="container">

	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12">

			<h1>Your Questions</h1>

			<a class="btn btn-primary" href="NewQuestion"><i
				class="glyphicon glyphicon-plus"></i> Add Question</a> <br /> <br />

			<c:choose>
				<c:when test="${questionList.size() eq 0}">
					<span>You have not published any question yet.</span>
				</c:when>
				<c:otherwise>
					<c:if test="${not empty message }">
						<div class="alert alert-dismissible alert-success">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							${message}
						</div>
					</c:if>

					<table id="questions"
						class="table table-bordered table-striped table-hover">
						<thead>
							<tr>
								<th>ID</th>
								<th>Question</th>
								<th>Date</th>
								<th>Active</th>
								<th>Votes up</th>
								<th>Votes down</th>
								<th>Display</th>
								<th>Edit</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="question" items="${questionList }">
								<tr>
									<td>${question.id}</td>
									<td>${question.text}</td>
									<td>${question.insertDate}</td>
									<td><c:if test="${question.active }">Yes</c:if> <c:if
											test="${question.active eq false}">No</c:if></td>
									<td>${question.upVote}</td>
									<td>${question.downVote}</td>
									<td><a class="btn btn-success"
										href="DisplayQuestion?qId=${question.id}"><i
											class="glyphicon glyphicon-list"></i> Display</a></td>
									<td><a class="btn btn-default"
										href="EditQuestion?qId=${question.id}"><i
											class="glyphicon glyphicon-edit"></i> Edit</a></td>
									<td><button class="btn btn-danger"
											data-question-id="${question.id}">
											<i class="glyphicon glyphicon-remove"></i> Delete
										</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>

		</div>
	</div>

</div>

<script src="./assets/js/bootbox.min.js"></script>
<script>
	$(document).ready(function() {

		$("#questions").on("click", ".btn-danger", function() {

			var self = $(this);

			bootbox.confirm({
				//size: "small",
				backdrop : false,
				message : "Are you sure you want to delete this question?",
				callback : function(result) { /* result is a boolean; true = OK, false = Cancel*/
					if (result) {
						var endPoint = "DeleteQuestion";

						var data = {
							qId : self.attr("data-question-id")
						};

						console.log(data);

						$.post(endPoint, data, function(result) {
							if (result.status === 'QUESTION_DELETED') {
								self.parents("tr").remove();
							}
						});

					}
				}
			});

		});
	});
</script>

<jsp:include page="../footer.jsp"></jsp:include>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="question" class="com.qapro.entity.Question"
	scope="request"></jsp:useBean>

<jsp:include page="../header.jsp"></jsp:include>

<div class="container">

	<div class="row">
		<div class="col-md-12">

			<h1>Question</h1>

			<c:choose>
				<c:when test="${question.id eq null}">
					<p>There is no question with this id.</p>
				</c:when>
				<c:otherwise>
					<h2>
						<c:out value="${question.text}"></c:out>
					</h2>

					<p class="voteParagraph">
						VOTES: UP <span id="upVote" class="badge">${question.upVote}</span> | <span id="downVote" class="badge">${question.downVote}</span>
						DOWN
					</p>

					<div>
						<a class="btn btn-primary" onclick="loadXMLDoc('UP')">Vote Up
							<i class="glyphicon glyphicon-thumbs-up"></i>
						</a> <a class="btn btn-primary" onclick="loadXMLDoc('DOWN')">Vote
							Down <i class="glyphicon glyphicon-thumbs-down"></i>
						</a>

					</div>

					<p id="status" class="text-info"></p>

					<form id="answerForm" action="AddAnswer" method="post">
						<div class="form-group">
							<label for="answerText">Add your answer:</label>
							<textarea id="answerText" name="answerText" rows="4"
								class="form-control"></textarea>
						</div>

						<input name="qId" id="qId" type="hidden" value="${question.id}" />

						<input id="addBtn" type="submit" class="btn btn-primary"
							value="Add answer" />
					</form>

					<h3>Answers:</h3>

					<ul id="answerList">
						<c:forEach var="answer" items="${question.answers}">
							<li><c:out value="${answer.text}"></c:out></li>
						</c:forEach>
					</ul>
				</c:otherwise>
			</c:choose>

		</div>
	</div>

</div>

<script type="text/javascript">
	function loadXMLDoc(voteType) {
		var xmlhttp;

		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				if (xmlhttp.status == 200) {
					var result = JSON.parse(xmlhttp.responseText);

					document.getElementById("upVote").innerHTML = result.voteUp;
					document.getElementById("downVote").innerHTML = result.voteDown;
					document.getElementById("status").innerHTML = result.status;
					//alert(xmlhttp.responseText);
					/* switch (xmlhttp.responseText) {
					case 'VOTING_SUCCESFULL':
						alert("Your voting has been saved, thanks.");
						break;
					case 'ALREADY_VOTE_DOWN':
						alert("You already voted this question down.");
						break;
					case 'ALREADY_VOTE_UP':
						alert("You already voted this question up.");
						break;
					default:
						alert("Your vote could not be saved.");
						break;
					} */
				} else {
					alert('Your vote cannot be saved.');
				}
			}
		};

		xmlhttp.open("POST", "VotingQuestion");
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send("questionId=${question.id}&type=" + voteType);
	}

	function failedResponse(response) {
	}

	$(document).ready(function() {

		$("#answerForm").submit(function(e) {

			e.preventDefault();

			var endPoint = $(this).attr('action');

			var data = {
				qId : $("#qId").val(),
				answerText : $("#answerText").val()
			};

			//console.log(url);

			$.post(endPoint, data, function(result) {
				if (result.status === 'ANSWER_ADDED') {

					var li = "<li>" + result.text + "</li>";
					$("#answerList").append(li);
					$("#answerText").val("");
				}
			});
		});
	});
</script>

<jsp:include page="../footer.jsp"></jsp:include>
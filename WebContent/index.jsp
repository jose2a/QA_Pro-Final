<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:include page="header.jsp"></jsp:include>

<div class="container">

	<div class="row">
		<div
			class="col-lg-offset-2 col-lg-8 col-md-offset-2 col-md-8 col-sm-12">
			<div class="page-header">
				<h1>
					<small class="pull-right">${questionList.size()} questions</small>
					Questions
				</h1>
			</div>
			<div class="comments-list">

				<c:forEach var="question" items="${questionList}">
					<div class="media">
						<p class="pull-right">
							<small>${question.insertDate}</small>
						</p>
						<a class="media-left" href="#"> <img
							src="http://lorempixel.com/40/40/people/1/">
						</a>
						<div class="media-body">

							<h4 class="media-heading user_name">${question.askerName}</h4>
							${question.text}

						</div>

						<p class="pull-left">
							<small>Votes: UP <span class="badge">${question.upVote}</span> -
								<span class="badge">${question.downVote}</span> DOWN</small>
						</p>

						<c:choose>
							<c:when test="${sessionScope.memberInfo eq null}">
								<p class="pull-right">
									<small>You need to register in order to see the
										answers.</small>
								</p>
							</c:when>
							<c:otherwise>
								<p class="pull-right">
									<small><a href="DisplayQuestion?qId=${question.id}">See
											answers</a></small>
								</p>
							</c:otherwise>
						</c:choose>
					</div>

				</c:forEach>

			</div>

		</div>
	</div>

</div>

<jsp:include page="footer.jsp"></jsp:include>
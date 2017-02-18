package com.qapro.servlets.question;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qapro.dtos.DeleteQuestionDto;
import com.qapro.enums.DeleteQuestionResult;
import com.qapro.service.QuestionService;

/**
 * Servlet implementation class DeleteQuestion
 */
public class DeleteQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DeleteQuestionDto deleteQuestionDto;
		
		try {
			Long qId = Long.parseLong(request.getParameter("qId"));

			QuestionService questionService = new QuestionService();
			questionService.deleteQuestion(qId);

			deleteQuestionDto = new DeleteQuestionDto(qId, DeleteQuestionResult.QUESTION_DELETED);
			
		} catch (Exception e) {
			deleteQuestionDto = new DeleteQuestionDto(0L, DeleteQuestionResult.QUESTION_DELETED_ERROR);			
			e.printStackTrace();
		}
		
		String json = new Gson().toJson(deleteQuestionDto);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

}

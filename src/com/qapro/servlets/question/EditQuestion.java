package com.qapro.servlets.question;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qapro.entity.Question;
import com.qapro.service.QuestionService;

/**
 * Servlet implementation class EditQuestion
 */
public class EditQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			Long qId = Long.parseLong(request.getParameter("qId"));

			QuestionService questionService = new QuestionService();
			Question question = questionService.getQuestion(qId);

			request.setAttribute("question", question);
			request.getRequestDispatcher("/question/questionForm.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

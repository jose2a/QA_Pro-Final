package com.qapro.servlets.question;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qapro.entity.MemberInfo;
import com.qapro.entity.Question;
import com.qapro.service.QuestionService;
import com.qapro.util.SessionUtil;

/**
 * Servlet implementation class MyQuestion
 */
public class ListQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListQuestion() {
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

			MemberInfo memberInfo = SessionUtil.getUserFromSession(request.getSession());

			if (memberInfo == null) {
				response.sendRedirect("./Login");
			}

			Long userId = memberInfo.getId();

			QuestionService questionService = new QuestionService();

			List<Question> questionList = questionService.getQuestionsByUserId(userId);

			request.setAttribute("questionList", questionList);
			
			String message = SessionUtil.getMessageFromSession(request.getSession());
			
			if (message != null) {
				request.setAttribute("message", message);
			}

			request.getRequestDispatcher("/question/questionList.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

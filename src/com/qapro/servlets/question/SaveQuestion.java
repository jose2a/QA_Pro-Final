package com.qapro.servlets.question;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qapro.entity.MemberInfo;
import com.qapro.entity.Question;
import com.qapro.service.QuestionService;
import com.qapro.util.SessionUtil;

/**
 * Servlet implementation class SaveQuestion
 */
public class SaveQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			Long qId = Long.parseLong(request.getParameter("qId"));
			String text = request.getParameter("qText");
			boolean isActive = Boolean.parseBoolean(request.getParameter("active"));

			QuestionService questionService = new QuestionService();

			Question question;

			if (qId == 0) {
				
				question = new Question();
				question.setText(text);
				question.setActive(isActive);

				MemberInfo memberInfo = SessionUtil.getUserFromSession(request.getSession());

				if (memberInfo == null) {
					response.sendRedirect("./Login");
				}
				
				Long userId = memberInfo.getId();
				question.setAskerId(userId);

				Long questionId = questionService.InsertQuestion(question);

				if (isActive && (questionId > 0 || questionId != null)) {
					SessionUtil.putMessageInSession(request.getSession(), "Question has been published.");
					
					response.sendRedirect("./ListQuestion");
				}

			} else {
				
				question = questionService.getQuestion(qId);
				question.setText(text);
				question.setActive(isActive);

				if (isActive) {
					SessionUtil.putMessageInSession(request.getSession(), "Question has been updated.");

					response.sendRedirect("./ListQuestion");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

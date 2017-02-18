package com.qapro.servlets.answer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qapro.dtos.AddAnswerDto;
import com.qapro.entity.Answer;
import com.qapro.entity.MemberInfo;
import com.qapro.enums.AddAnswerResult;
import com.qapro.service.AnswerService;
import com.qapro.util.SessionUtil;

/**
 * Servlet implementation class AddAnswer
 */
public class AddAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddAnswer() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AddAnswerDto addAnswerDto = null;

		try {

			Long questionId = Long.parseLong(request.getParameter("qId"));
			String text = request.getParameter("answerText");

			MemberInfo memberInfo = SessionUtil.getUserFromSession(request.getSession());

			if (memberInfo == null) {
				response.sendRedirect("./Login");
			}

			Long userId = memberInfo.getId();

			Answer answer = new Answer();
			answer.setQuestionId(questionId);
			answer.setText(text);
			answer.setResponderId(userId);

			AnswerService answerService = new AnswerService();
			answerService.addAnswer(answer);

			addAnswerDto = new AddAnswerDto(AddAnswerResult.ANSWER_ADDED, text);

		} catch (Exception e) {
			addAnswerDto = new AddAnswerDto(AddAnswerResult.ERROR, "");
		}

		String json = new Gson().toJson(addAnswerDto);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

}

package com.qapro.servlets.question;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qapro.entity.Question;

/**
 * Servlet implementation class NewQuestion
 */
public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddQuestion() {
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
			Question question = new Question();
			question.setId(0L);
			question.setActive(false);

			request.setAttribute("question", question);

			request.getRequestDispatcher("/question/questionForm.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		

	}

}

package com.qapro.servlets.home;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qapro.entity.Question;
import com.qapro.service.QuestionService;

/**
 * Servlet implementation class Index
 */
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			QuestionService questionService = new QuestionService();
			
			List<Question> questionList = questionService.getQuestionsWithAskerUsername();
			
			request.setAttribute("questionList", questionList);
			
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}

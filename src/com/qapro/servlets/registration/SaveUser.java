package com.qapro.servlets.registration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qapro.entity.MemberInfo;
import com.qapro.exceptions.ExistingUserException;
import com.qapro.service.MemberService;
import com.qapro.util.ApplicationUtil;
import com.qapro.util.SessionUtil;

/**
 * Servlet implementation class SaveUser
 */
public class SaveUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveUser() {
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
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String username = request.getParameter("username");

			MemberService memberService = new MemberService();

			MemberInfo memberInfo = new MemberInfo();
			memberInfo.setName(name);
			memberInfo.setSurname(surname);
			memberInfo.setEmail(email);
			memberInfo.setPassword(password);
			memberInfo.setUserName(username);

			Long userId = memberService.addMember(memberInfo);

			// localhost:8080/QA_Pro/validate?key=sdfsfsdf&userid=423
			System.out.println(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/ValidateEmail?key=" + ApplicationUtil.generateEmailValidationCode(10) + "&userid=" + userId);
			
			response.sendRedirect("./Login");

		} catch (Exception e) {
			if (e instanceof ExistingUserException) {
				SessionUtil.putMessageInSession(request.getSession(), e.getMessage());
				
				response.sendRedirect("./Register");
			}

			e.printStackTrace();
		}
	}

}

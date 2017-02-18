package com.qapro.servlets.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qapro.entity.MemberInfo;
import com.qapro.service.MemberService;
import com.qapro.util.SessionUtil;

/**
 * Servlet implementation class CheckLoginServlet
 */
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckLogin() {
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
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			MemberService memberService = new MemberService();
			
			MemberInfo memberInfo = memberService.verifyUserCredentials(email, password);

			if (memberInfo == null) {
				SessionUtil.putMessageInSession(request.getSession(), "Wrong email or password.");
				response.sendRedirect("./Login");
			}

			if (!memberInfo.getIsActive()) {
				SessionUtil.putMessageInSession(request.getSession(), "Please activate your membership.");
				response.sendRedirect("./Login");
			}

			SessionUtil.removeUserFromSession(request.getSession());
			memberService.addPermissionsToUser(memberInfo);
			SessionUtil.putUserInSession(request.getSession(true), memberInfo);

			response.sendRedirect("./");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

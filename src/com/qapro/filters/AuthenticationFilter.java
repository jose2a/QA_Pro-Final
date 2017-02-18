package com.qapro.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qapro.util.SessionUtil;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter {

	private ServletContext context;
	private HttpServletRequest req;
	private HttpServletResponse res;

	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		req = (HttpServletRequest) request;
		res = (HttpServletResponse) response;

		String uri = req.getRequestURI();

		this.context.log("Requested Resource:" + uri);

		boolean auth = SessionUtil.isUserAuthenticated(req.getSession(false));
		
		if (auth && (uri.endsWith("Login") || uri.endsWith("CheckLogin") || uri.endsWith("Register")
				|| uri.endsWith("SaveUser"))) {

			res.sendRedirect("/");
		}

		if (!auth && !(uri.endsWith("Login") || uri.endsWith("Register") || uri.endsWith("SaveUser")
				|| uri.endsWith("Index") || uri.endsWith("/") || uri.endsWith("CheckLogin")
				|| uri.contains("/assets/"))) {

			this.context.log("Unauthorized access request");

			res.sendRedirect("Login");
		}

		chain.doFilter(request, response);
	}

	public void destroy() {
		// close any resources here
	}

}

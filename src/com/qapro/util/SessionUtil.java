package com.qapro.util;

import javax.servlet.http.HttpSession;

import com.qapro.entity.MemberInfo;

public class SessionUtil {
	
	private static final String USER_KEY = "memberInfo";
	private static final String MESSAGE_KEY = "message";

	public static boolean isUserAuthenticated(HttpSession session) {
		boolean auth = false;

		if (session != null) {
			auth = session.getAttribute(USER_KEY) != null;
		}

		return auth;
	}
	
	public static void putUserInSession(HttpSession session, MemberInfo memberInfo) {
		session.setAttribute(USER_KEY, memberInfo);
	}
	
	public static MemberInfo getUserFromSession(HttpSession session) {
		Object memberInfo = session.getAttribute(USER_KEY);
		
		if (memberInfo != null) { 
			return (MemberInfo) memberInfo;
		}
		
		return null;
	}
	
	public static void removeUserFromSession(HttpSession session) {
		session.removeAttribute(USER_KEY);
	}

	public static void putMessageInSession(HttpSession session, String message) {
		session.setAttribute(MESSAGE_KEY, message);
	}

	public static String getMessageFromSession(HttpSession session) {
		Object message = session.getAttribute(MESSAGE_KEY);		

		if (message != null) {
			
			session.removeAttribute(MESSAGE_KEY);
			return message.toString();
		}
		
		return null;
	}
}

package edu.missouristate.util;

import javax.servlet.http.HttpSession;

public class Helper {

	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static void addSessionMessage(HttpSession session, String message) {
		session.setAttribute("message", message);
	}
	
}

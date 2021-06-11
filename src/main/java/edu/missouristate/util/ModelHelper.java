package edu.missouristate.util;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class ModelHelper {
	
	public static final String MESSAGE_TYPE_INFO = "info";
	public static final String MESSAGE_TYPE_ERROR = "error";
	public static final String MESSAGE_TYPE_SUCCESS = "success";
	
	public static void addMessageToModel(Model model, String messageType, String messageBody) {
		model.addAttribute("messageType", messageType);
		model.addAttribute("messageBody", messageBody);
	}
	
	public static void addFlashMessageToModel(RedirectAttributes redirectAttributes, 
			String messageType, String messageBody) {
		
		redirectAttributes.addFlashAttribute("messageType", messageType);
		redirectAttributes.addFlashAttribute("messageBody", messageBody);
	}
	
}

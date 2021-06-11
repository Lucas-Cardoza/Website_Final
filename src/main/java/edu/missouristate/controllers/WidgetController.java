package edu.missouristate.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.missouristate.dao.WidgetRepository;
import edu.missouristate.model.Widget;
import edu.missouristate.services.WidgetService;
import edu.missouristate.util.Helper;

@Controller
public class WidgetController {

	@Autowired
	WidgetService widgetService;

	@GetMapping("/widgets")
	public String getWidgetTable(Model model, HttpServletRequest request, HttpSession session) {
		List<Widget> widgetList = widgetService.getWidgets();
		model.addAttribute("widgetList", widgetList);
		return "widgets";
	}

	@GetMapping("/widgets/addEditWidget")
	public String getAddEditWidget(HttpServletRequest request, HttpSession session, Model model, String id) {
		// Default the next page to addEditWidget
		String page = "addEditWidget";
		
		// If the ID is an integer, we need to load the "edit widget" page
		// Otherwise, we need to load the "add widget" page
		if (Helper.isInteger(id)) {
			page = widgetService.prepareEditWidget(session, model, Integer.parseInt(id));
		} 
		else {
			page = widgetService.prepareAddWidget(model);
		}
		// Return the Add JSP, Edit JSP, or Widget's Page (Table)
		return page;
	}

	@PostMapping("/widgets/addEditWidget")
	public String postAddEditWidget(HttpSession session, Model model, Widget widget) {

		if (widget != null && widget.getId() != null) {
			widgetService.updateWidget(model, widget);
		}
		else if (widget.getId() == null) {
			widgetService.addWidgetToRepo(model, widget);
		}
		else {
			Helper.addSessionMessage(session, "Sorry, could not find requested address");
		}
		return "redirect:/widgets";
	}

	@GetMapping("/widgets/deleteWidget")
	public String getDeleteWidget(HttpSession session, Model model, String id) {

		if (Helper.isInteger(id)) {
			int deleteCount = widgetService.deleteWidget(model, Integer.parseInt(id));
			Helper.addSessionMessage(session, "Address Deleted: " + deleteCount);
		} 
		else {
			String idString = ((id == null) ? "''" : ("'" + id + "'"));
			Helper.addSessionMessage(session, "Sorry, could not find address with requested id = " + idString);
		}
		return "redirect:/widgets";
	}
	
	@ResponseBody
	@RequestMapping(value = { "/widgets/xAddEditWidget" }, method = RequestMethod.POST, produces = "application/text", consumes = "application/json")
	public String postXaddEditUser(@RequestBody Widget widget, Model model) {
		try {
			widgetService.addWidgetToRepo(model, widget);
			return "Address Added Successfully";
		} 
		catch (Exception e) {
			String msg = (e == null || e.getMessage() == null) ? "Null Pointer Exception" : e.getMessage();
			return "Internal Service Error: " + msg;
		}
		
	}
}

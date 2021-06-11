package edu.missouristate.services.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Template;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import edu.missouristate.dao.WidgetRepository;
import edu.missouristate.model.Widget;
import edu.missouristate.services.WidgetService;
import edu.missouristate.util.Helper;
import edu.missouristate.util.MSU;

@Service("widgetService")
public class WidgetServiceImpl implements WidgetService {
    
    @Autowired
    WidgetRepository widgetRepo;

	@Override
	public List<Widget> getWidgets() {
		return widgetRepo.getWidgets();
	}

	@Override
	public String prepareAddWidget(Model model) {	
		// Generating a new address widget	
		Widget widget = new Widget();
		model.addAttribute("Address", widget);
		model.addAttribute("title", "Add Address");
		// Return the addEditWidget Page
		return "addEditWidget";
	}

	@Override
	public String prepareEditWidget(HttpSession session, Model model, int id) {
		// Get the widget by ID
		Widget widget = getWidgetById(id);
		
		if (widget != null) {
			model.addAttribute("Address", widget); 
			model.addAttribute("title", "Edit Address");
			// Return the addEditWidget page 
			return "addEditWidget";
		} else {
			// Provide error message to the user and redirect to the widgets page
			Helper.addSessionMessage(session, "Sorry, address with ID = " + id + " not found.");
			return "redirect:/widgets";
		}
	}

	@Override
	public Widget getWidgetById(Integer id) {
		// Get the ID of widget
		return widgetRepo.getWidgetById(id);
	}
	
	@Override
	public void updateWidget(Model model, Widget widget) {
		// A call to the widgetRepo to update the widget
		// based on the new values passed into this method
			widgetRepo.updateWidget(widget);
	}

	@Override
	public void addWidgetToRepo(Model model, Widget widget) {
		// A call to the widgetRepo to add the new address
		widgetRepo.addWidget(widget);
	}

	@Override
	public int deleteWidget(Model model, int id) {
		
		// Return the number of records affected by the query
		return widgetRepo.deleteWidget(id);
	}
   
}

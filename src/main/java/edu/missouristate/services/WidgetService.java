package edu.missouristate.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import edu.missouristate.model.Widget;

public interface WidgetService {
	public List<Widget> getWidgets();
	public Widget getWidgetById(Integer id);
	public String prepareAddWidget(Model model);
	public int deleteWidget(Model model, int id);
	public void updateWidget(Model model, Widget widget);
	public void	addWidgetToRepo(Model model, Widget widget);
	public String prepareEditWidget(HttpSession session, Model model, int parseInt);
}

package edu.missouristate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.missouristate.model.Widget;
import edu.missouristate.util.MSU;

@Repository
public class WidgetRepository {

	@Autowired
	JdbcTemplate template;

	public WidgetRepository() {
	}

	public List<Widget> getWidgets() 
	{
		String sql = "SELECT id, first_name, last_name, street, city, state, zip FROM address ";
		Object[] args = null;
		List<Widget> widgetList = template.query(sql, args, MSU.WIDGET_BPRM);
		return widgetList;
	}

	// public List<Widget> getWidgets() {
	// /**
	// * MSU.WIDGET_BPRM to map the values if you like.
	// * /src/main/java/edu/missouristate/util/MSU.java
	// */
	// String sql = "SELECT id, first_name, last_name, street, city, state, zip " +
	// "FROM address ";
	// Object[] args = {};
	// List<Map<String, Object>> result = template.queryForList(sql, args);
	// List<Widget> widgetList = new ArrayList<Widget>();

	// for (Map<String, Object> map : result) {
	// Widget widget = new Widget();

	// for (Map.Entry<String, Object> entry : map.entrySet()) {
	// String key = entry.getKey();
	// String data = ((entry.getValue() == null) ? null :
	// entry.getValue().toString());

	// switch (key) {
	// case "ID":
	// widget.setId(Integer.valueOf(data));
	// break;
	// case "FIRST_NAME":
	// widget.setFirstName(String.valueOf(data));
	// break;
	// case "LAST_NAME":
	// widget.setLastName(String.valueOf(data));
	// break;
	// case "STREET":
	// widget.setStreet(String.valueOf(data));
	// break;
	// case "CITY":
	// widget.setCity(String.valueOf(data));
	// break;
	// case "STATE":
	// widget.setState(String.valueOf(data));
	// break;
	// case "ZIP":
	// widget.setZip(Integer.valueOf(data));
	// break;
	// }
	// }
	// if (widget.getId() != null) {
	// widgetList.add(widget);
	// }
	// }
	// return widgetList;
	// }

	public void addWidget(Widget widget) 
	{
		String sql = "INSERT INTO address " 
				+ "(first_name, last_name, street, city, state, zip) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		Object[] args = {widget.getFirstName(), widget.getLastName(), widget.getStreet(), widget.getCity(),
				widget.getState(), widget.getZip()};
		template.update(sql, args);

	}

	public Widget getWidgetById(Integer id) 
	{
		String sql = "SELECT * FROM address WHERE id = ?";
		Object[] args = {id};
		Widget widget = template.queryForObject(sql, args, MSU.WIDGET_BPRM);
		return widget;
	}

	public void updateWidget(Widget widget) 
	{
		String sql = "UPDATE address SET " + "first_name = ?, " + "last_name = ?, " + "street = ?, " + "city = ?, "
				+ "state = ?, " + "zip = ? " + "WHERE id = ?";
		Object[] args = {widget.getFirstName(), widget.getLastName(), widget.getStreet(), widget.getCity(),
				widget.getState(), widget.getZip(), widget.getId()};
		template.update(sql, args);

	}

	public int deleteWidget(int id) 
	{
		String sql = "DELETE FROM address WHERE id = ?";
		Object[] args = {id};
		return template.update(sql, args);
	}

}

package edu.missouristate.util;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import edu.missouristate.model.Widget;

public class MSU {

	public static BeanPropertyRowMapper<Widget> WIDGET_BPRM = new BeanPropertyRowMapper<Widget>(Widget.class);

}

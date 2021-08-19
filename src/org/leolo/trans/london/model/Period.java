package org.leolo.trans.london.model;

import org.json.JSONObject;

public class Period extends BaseModel{

	public static Period parse(JSONObject object) {
		return new Period();
	}
}

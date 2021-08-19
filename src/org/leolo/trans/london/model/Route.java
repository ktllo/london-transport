package org.leolo.trans.london.model;

import org.json.JSONObject;

public class Route extends BaseModel{
	public static Route parse(JSONObject object) {
		return new Route();
	}
}

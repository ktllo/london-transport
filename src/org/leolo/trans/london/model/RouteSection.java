package org.leolo.trans.london.model;

import org.json.JSONObject;

public class RouteSection extends BaseModel{
	public static RouteSection parse(JSONObject object) {
		return new RouteSection();
	}
}

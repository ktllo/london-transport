package org.leolo.trans.london.model;

import org.json.JSONObject;

public class Crowding extends BaseModel{
	
	public static Crowding parse(JSONObject object) {
		return new Crowding();
	}
	
}

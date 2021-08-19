package org.leolo.trans.london.model;

import org.json.JSONObject;

public class Stop extends BaseModel{
	public static Stop parse(JSONObject object) {
		return new Stop();
	}
}

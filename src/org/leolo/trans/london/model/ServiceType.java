package org.leolo.trans.london.model;

import org.json.JSONObject;

public class ServiceType extends BaseModel{
	public static ServiceType parse(JSONObject object) {
		return new ServiceType();
	}
}

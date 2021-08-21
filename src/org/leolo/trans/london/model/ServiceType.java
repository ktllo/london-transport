package org.leolo.trans.london.model;

import org.json.JSONObject;

public class ServiceType extends BaseModel{
	
	private String name;
	private String uri;
	
	public static ServiceType parse(JSONObject object) {
		ServiceType st = new ServiceType();
		st.name = object.optString("name");
		st.uri = object.optString("uri");
		return st;
	}

	public String getName() {
		return name;
	}

	public String getUri() {
		return uri;
	}
}

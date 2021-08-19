package org.leolo.trans.london.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.leolo.trans.london.Constants;

public class Disruption extends BaseModel{
	
	static Logger log = LogManager.getLogger(Disruption.class);
	
	private String category;
	private String type;
	private String categoryDescription;
	private String description;
	private String summary;
	private String additionalInfo;
	private Date created;
	private Date updated;//lastUpdate
	private Vector<Route> affectedRoute = new Vector<>();
	private Vector<Stop> affectedStop = new Vector<>();
	private String closureText;
	
	public static Disruption parse(JSONObject object) {
		SimpleDateFormat sdf = getDateFormat();
		Disruption disruption = new Disruption();
		disruption.category = object.optString("category");
		disruption.type = object.optString("type");
		disruption.categoryDescription = object.optString("categoryDescription");
		disruption.description = object.optString("description");
		disruption.summary = object.optString("summary");
		disruption.additionalInfo = object.optString("additionalInfo");
		try {
			disruption.created = sdf.parse(object.optString("created"));
			disruption.updated = sdf.parse(object.optString("lastUpdate"));
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
		}
		JSONArray affectedRoutes = object.getJSONArray("affectedRoutes");
		for(int i=0;i<affectedRoutes.length();i++) {
			disruption.affectedRoute.add(Route.parse(affectedRoutes.getJSONObject(i)));
		}
		JSONArray affectedStops = object.getJSONArray("affectedStops");
		for(int i=0;i<affectedStops.length();i++) {
			disruption.affectedStop.add(Stop.parse(affectedStops.getJSONObject(i)));
		}
		disruption.closureText = object.optString("closureText");
		return disruption;
	}

	public static Logger getLog() {
		return log;
	}

	public String getCategory() {
		return category;
	}

	public String getType() {
		return type;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public String getDescription() {
		return description;
	}

	public String getSummary() {
		return summary;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public Vector<Route> getAffectedRoute() {
		return affectedRoute;
	}

	public Vector<Stop> getAffectedStop() {
		return affectedStop;
	}

	public String getClosureText() {
		return closureText;
	}
}

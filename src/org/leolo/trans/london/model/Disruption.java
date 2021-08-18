package org.leolo.trans.london.model;

import java.util.Date;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class Disruption {
	
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
		Disruption disruption = new Disruption();
		disruption.category = object.getString("category");
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

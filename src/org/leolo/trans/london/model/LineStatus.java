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

public class LineStatus extends BaseModel{
	
	static Logger log = LogManager.getLogger(LineStatus.class);
	
	private int id;
	private String lineId;
	private int statusSeverity;
	private String statusSeverityDescription;
	private String reason;
	private Date created;
	private Date updated;
	private Vector<Period> validityPeriod = new Vector<>();
	private Disruption disruption;
	
	public static LineStatus parse(JSONObject object) {
		LineStatus ls = new LineStatus();
		SimpleDateFormat sdf = getDateFormat();
		ls.id = object.getInt("id");
		ls.lineId = object.optString("lineId");
		ls.statusSeverity = object.getInt("statusSeverity");
		ls.statusSeverityDescription = object.optString("statusSeverityDescription");
		try {
			ls.created = sdf.parse(object.optString("created"));
			ls.updated = sdf.parse(object.optString("modified"));
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
		}
		JSONArray validityPeriods = object.getJSONArray("validityPeriods");
		for(int i=0;i<validityPeriods.length();i++) {
			ls.validityPeriod.add(Period.parse(validityPeriods.getJSONObject(i)));
		}
		ls.disruption = Disruption.parse(object.getJSONObject("disruption"));
		return ls;
	}

	public static Logger getLog() {
		return log;
	}

	public int getId() {
		return id;
	}

	public String getLineId() {
		return lineId;
	}

	public int getStatusSeverity() {
		return statusSeverity;
	}

	public String getStatusSeverityDescription() {
		return statusSeverityDescription;
	}

	public String getReason() {
		return reason;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public Vector<Period> getValidityPeriod() {
		return validityPeriod;
	}

	public Disruption getDisruption() {
		return disruption;
	}
	
}

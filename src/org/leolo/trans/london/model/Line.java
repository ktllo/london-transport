package org.leolo.trans.london.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.leolo.trans.london.Constants;

public class Line {
	static Logger log = LogManager.getLogger(Line.class);
	private String id;
	private String name;
	private String modeName;
	private Date created;
	private Date updated;
	private Vector<Disruption> disruption = new Vector<>();
	private Vector<RouteSection> routeSections = new Vector<>();
	private Vector<ServiceType> serviceTypes = new Vector<>();
	private Crowding crowding;
	
	public static Line parse(JSONObject object) {
		Line line = new Line();
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_STR);
		line.id = object.getString("id");
		line.name = object.getString("name");
		line.modeName = object.getString("modeName");
		JSONArray disruptions = object.getJSONArray("disruptions");
		for(int i=0;i<disruptions.length();i++) {
			line.disruption.add(Disruption.parse(disruptions.getJSONObject(i)));
		}
		try {
			line.created=sdf.parse(object.getString("created"));
			line.updated=sdf.parse(object.getString("modified"));
		}catch(ParseException e) {
			log.warn(e.getMessage(), e);
		}
		return line;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModeName() {
		return modeName;
	}
	public void setModeName(String modeName) {
		this.modeName = modeName;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Crowding getCrowding() {
		return crowding;
	}
	public void setCrowding(Crowding crowding) {
		this.crowding = crowding;
	}
	public Vector<Disruption> getDisruption() {
		return disruption;
	}
	public Vector<RouteSection> getRouteSections() {
		return routeSections;
	}
	public Vector<ServiceType> getServiceTypes() {
		return serviceTypes;
	}
}

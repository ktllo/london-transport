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

public class Line extends BaseModel{
	static Logger log = LogManager.getLogger(Line.class);
	private String id;
	private String name;
	private String modeName;
	private Date created;
	private Date updated;
	private Vector<Disruption> disruptions = new Vector<>();
	private Vector<LineStatus> lineStatuses = new Vector<>();
	private Vector<RouteSection> routeSections = new Vector<>();
	private Vector<ServiceType> serviceTypes = new Vector<>();
	private Crowding crowding;
	
	public static Line parse(JSONObject object) {
		Line line = new Line();
		SimpleDateFormat sdf = getDateFormat();
		line.id = object.optString("id");
		line.name = object.optString("name");
		line.modeName = object.optString("modeName");
		JSONArray disruptions = object.getJSONArray("disruptions");
		for(int i=0;i<disruptions.length();i++) {
			line.disruptions.add(Disruption.parse(disruptions.getJSONObject(i)));
		}
		JSONArray lineStatus = object.getJSONArray("lineStatuses");
		for(int i=0;i<lineStatus.length();i++) {
			line.lineStatuses.add(LineStatus.parse(lineStatus.getJSONObject(i)));
		}
		JSONArray routeSection = object.getJSONArray("routeSections");
		for(int i=0;i<routeSection.length();i++) {
			line.routeSections.add(RouteSection.parse(routeSection.getJSONObject(i)));
		}
		JSONArray serviceType = object.getJSONArray("serviceTypes");
		for(int i=0;i<serviceType.length();i++) {
			line.serviceTypes.add(ServiceType.parse(serviceType.getJSONObject(i)));
		}
		line.crowding = Crowding.parse(object.getJSONObject("crowding"));
		try {
			line.created=sdf.parse(object.optString("created"));
			line.updated=sdf.parse(object.optString("modified"));
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
	public Vector<Disruption> getDisruptions() {
		return disruptions;
	}
	public Vector<RouteSection> getRouteSections() {
		return routeSections;
	}
	public Vector<ServiceType> getServiceTypes() {
		return serviceTypes;
	}
	public Vector<LineStatus> getLineStatuses() {
		return lineStatuses;
	}
}

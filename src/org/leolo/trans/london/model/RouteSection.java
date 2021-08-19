package org.leolo.trans.london.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class RouteSection extends BaseModel{
	
	static Logger log = LogManager.getLogger(RouteSection.class);
	
	private String routeCode;
	private String name;
	private String direction;
	private String originationName;
	private String destinationName;
	private String origintor;
	private String destination;
	private String serviveType;
	private Date validTo;
	private Date validFrom;
	
	public static RouteSection parse(JSONObject object) {
		RouteSection rs =  new RouteSection();
		SimpleDateFormat sdf= getDateFormat();
		rs.routeCode = object.optString("routeCode");
		rs.name = object.optString("name");
		rs.direction = object.optString("direction");
		rs.originationName = object.optString("originationName");
		rs.destinationName = object.optString("destinationName");
		rs.origintor = object.optString("origintor");
		rs.destination = object.optString("destination");
		rs.serviveType = object.optString("serviceType");
		try {
			rs.validFrom = sdf.parse(object.optString("validFrom"));
			rs.validTo = sdf.parse(object.optString("validTo"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		}
		
		return rs;
	}

	public static Logger getLog() {
		return log;
	}

	public String getRouteCode() {
		return routeCode;
	}

	public String getName() {
		return name;
	}

	public String getDirection() {
		return direction;
	}

	public String getOriginationName() {
		return originationName;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public String getOrigintor() {
		return origintor;
	}

	public String getDestination() {
		return destination;
	}

	public String getServiveType() {
		return serviveType;
	}

	public Date getValidTo() {
		return validTo;
	}

	public Date getValidFrom() {
		return validFrom;
	}
}

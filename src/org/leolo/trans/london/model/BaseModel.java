package org.leolo.trans.london.model;

import java.text.SimpleDateFormat;

import org.json.JSONObject;
import org.leolo.trans.london.Constants;

public abstract class BaseModel {
	@Deprecated
	protected static final SimpleDateFormat getDateFormat() {
		return getDateFormat(DateFormatType.FULL);
	}
	protected static final SimpleDateFormat getDateFormat(DateFormatType type) {
		if(type == DateFormatType.SECOND)
			return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS");
	}
	
	protected enum DateFormatType{
		FULL,
		SECOND;
	}
}

package org.leolo.trans.london.model;

import java.text.SimpleDateFormat;

import org.json.JSONObject;
import org.leolo.trans.london.Constants;

public abstract class BaseModel {
	protected static final SimpleDateFormat getDateFormat() {
		return new SimpleDateFormat(Constants.DATE_FORMAT_STR);
	}
}

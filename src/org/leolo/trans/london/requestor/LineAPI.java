package org.leolo.trans.london.requestor;

import java.io.IOException;
import java.util.Collection;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.leolo.trans.london.model.Line;
import org.leolo.trans.london.util.APIUtil;

public class LineAPI {
	static Logger log = LogManager.getLogger(LineAPI.class);
	public static Collection<Line> getLines(String requestType){
		Vector<Line> collection = new Vector<>();
		try {
			String json = APIUtil.sendRequest(APIUtil.getURL("Line/Route",null));
			JSONArray mainArray = new JSONArray(json);
			log.info("{} line(s) found.", mainArray.length());
			for(int i=0;i<mainArray.length();i++) {
				collection.add(Line.parse(mainArray.getJSONObject(i)));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return collection;
	}
	
}

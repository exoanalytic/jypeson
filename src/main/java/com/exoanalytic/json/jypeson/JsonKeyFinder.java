package com.exoanalytic.json.jypeson;

import it.dangelo.saj.SAJException;
import it.dangelo.saj.impl.JSONReaderImpl;
import it.dangelo.saj.parser.ContentHandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A SAJ ContentHandler that captures the character offset
 * of each key in the subject JSON.
 *  
 * @author walk_n_wind
 */
public class JsonKeyFinder implements ContentHandler {
	private JSONReaderImpl jsonReader;

	/**
	 * Constructor
	 * @param jsonReader the actual reader that will be notifying this ContentHandler
	 */
	public JsonKeyFinder(JSONReaderImpl jsonReader){
		this.jsonReader = jsonReader;
	}
	
	/**
	 * The JSON keys (which are values in the returned map) are in
	 * dot notation. For example:
	 * 
	 * <pre>
	 * {@code
	 * {
	 * 	"attr1": "value",
	 *  "attr2": {
	 * 		"innerAttr": "inner value"	
	 * 	}
	 * }
	 * }
	 * </pre>
	 * 
	 * would return JSON keys of "attr1", "attr2", and "attr2.innerAttr"
	 *  
	 * @return Map of JSON keys, accessible by their character offset
	 */
	public Map<Integer, String> getKeys() {
		return keys;
	}

	private List<String> currentKey = new ArrayList<String>();
	private boolean isInArray = false;
	private Map<Integer, String> keys = new HashMap<Integer, String>();

	public void startJSON() throws SAJException {}

	public void endJSON() throws SAJException {}

	public void startObject() throws SAJException {}

	private void removeKey() {
		if (currentKey.size() > 0)
			currentKey.remove(currentKey.size() - 1);
	}
	
	public void endObject() throws SAJException {
		removeKey();
	}

	public void startArray() throws SAJException {
		isInArray = true;
	}

	public void endArray() throws SAJException {
		isInArray = false;
		removeKey();
	}

	public void attribute(String name) throws SAJException {
		isInArray = false;
		currentKey.add(name);
		keys.put(new Integer(jsonReader.getOffset()), currentKeyInDotNotation());
	}
	
	private String currentKeyInDotNotation() {
		if (currentKey.size() == 0)
			return null;

		String dotNotation = "";
		for (String key : currentKey) {
			dotNotation = dotNotation.concat("." + key);
		}
		return dotNotation.substring(1);	// remove the 1st '.'
	}

	// literals:

	public void _string(String value) throws SAJException {
		if (isInArray)
			return;
		removeKey();		
	}

	public void _boolean(boolean value) throws SAJException {
		if (isInArray)
			return;
		removeKey();
	}

	public void _number(BigDecimal value) throws SAJException {
		if (isInArray)
			return;
		removeKey();
	}

	public void _null() throws SAJException {
		if (isInArray)
			return;
		removeKey();
	}
	
}

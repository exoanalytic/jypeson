package com.exoanalytic.json.jypeson;

import it.dangelo.saj.SAJException;
import it.dangelo.saj.impl.JSONReaderImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Documents JSON as directed
 * @author walk_n_wind
 *
 */
public class JsonDocumenter {
	private IJsonDocumentReference docReference;
	private SurroundJsonKeyWith surroundWith;
	
	public JsonDocumenter(IJsonDocumentReference docReference,
			SurroundJsonKeyWith surroundWith) {
		this.docReference = docReference;
		this.surroundWith = surroundWith;
	}
	
	
	public String documentedJson(String json) {
		JSONReaderImpl jsonReader = new JSONReaderImpl();
		JsonKeyFinder keyFinder = new JsonKeyFinder(jsonReader);
		jsonReader.setContentHandler(keyFinder);
		try {
			jsonReader.parse(json);
		} catch (SAJException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuffer documentedJson = new StringBuffer();
		Pattern pattern = Pattern.compile("\\\"([a-zA-Z_]+)\\\"\\:");
		Matcher matcher = pattern.matcher(json);
		int afterLastMatch = 0;
		while (matcher.find()) {
			String dotNotatedKey = keyFinder.getKeys().get(matcher.start() + matcher.group(0).length() - 1);
			String keyDoc = docReference.getDocumentationForKey(dotNotatedKey);
			
			// after last match to beginning of this one:
			documentedJson.append(json.substring(afterLastMatch, matcher.start()));

			// documented key
			if (keyDoc != null) {
				documentedJson.append(surroundWith.getDocumentedKey(matcher.group(0), keyDoc));
			}
			else documentedJson.append(matcher.group(0));
			
			afterLastMatch = matcher.start() + matcher.group(0).length();
		}
		documentedJson.append(json.substring(afterLastMatch));
		
		return documentedJson.toString();
	}
}

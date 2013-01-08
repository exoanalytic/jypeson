package com.exoanalytic.json.jypeson;

/**
 * A reference used for accessing documentation of a JSON key
 * @author walk_n_wind
 *
 */
public interface IJsonDocumentReference {
	/**
	 * @param jsonKey the JSON key in dot notation
	 * @return documentation for the given JSON key
	 */
	public String getDocumentationForKey(String jsonKey);
}

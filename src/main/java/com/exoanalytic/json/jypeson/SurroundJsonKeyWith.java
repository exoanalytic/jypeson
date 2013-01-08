package com.exoanalytic.json.jypeson;

/**
 * Used to describe how to format a documented JSON key
 * @see JsonDocumenter
 * @author walk_n_wind
 */
public class SurroundJsonKeyWith {
	private String pre;
	private String post;
	public static final String DOC_GOES_HERE = SurroundJsonKeyWith.class.getName() + "DOC_GOES_HERE";

	/**
	 * Constructor. Note that pre or post should have the DOC_GOES_HERE value
	 * in it so that there is a placeholder to insert documentation for the
	 * JSON key
	 * @param pre String that will lead the JSON key
	 * @param post String that will follow the JSON key
	 */
	public SurroundJsonKeyWith(String pre, String post) {
		this.pre = pre;
		this.post = post;
	}

	public String getPre() {
		return pre;
	}

	public String getPost() {
		return post;
	}
	
	/**
	 * Documents the JSON key and returns the resulting string. Surrounds the
	 * JSON key with the pre and post values set. Also replaces DOC_GOES_HERE
	 * with the specified documentation.
	 * 
	 * @param jsonKey the JSON key being documented
	 * @param documentation the documentation for the JSON key
	 * @return a new String with the JSON key surrounded by pre and post, and with documentation inserted
	 */
	public String getDocumentedKey(String jsonKey, String documentation) {
		return 	pre.replace(DOC_GOES_HERE, documentation)
				+ jsonKey
				+ post.replace(DOC_GOES_HERE, documentation);
	}
}

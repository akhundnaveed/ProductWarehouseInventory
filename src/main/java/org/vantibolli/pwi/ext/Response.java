/**
 * 
 */
package org.vantibolli.pwi.ext;

/**
 * <h1>Response</h1> A bean class to be used to respond web service client about success and failure response
 *
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
public class Response {
	
	private boolean success;
	private String message;
	
	/**
	 * No arguments constructor
	 */
	public Response() {
	}
	
	/**
	 * @param success
	 *            can be true or false
	 * @param message
	 *            to inform client what happened in the back-end
	 */
	public Response(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	/**
	 * @return the success can be true or false
	 */
	public boolean isSuccess() {
		return success;
	}
	
	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Response [success=");
		builder.append(success);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
}

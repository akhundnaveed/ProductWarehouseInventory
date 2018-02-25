/**
 * 
 */
package org.vantibolli.pwi.ext;

/**
 * @author naveed
 *
 */
public class Response {
	
	private boolean success;
	private String message;
	
	/**
	 * 
	 */
	public Response() {
	}
	
	/**
	 * @param success
	 * @param message
	 */
	public Response(boolean success, String message) {
		this.success = success;
		this.message = message;
	}



	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
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
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/* (non-Javadoc)
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

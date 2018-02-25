/**
 * 
 */
package org.vantibolli.pwi.ext;

/**
 * @author naveed
 *
 */
public class PwiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3837462364089583732L;
	
	public PwiException() {
		super();
	}
	
	public PwiException(String message) {
		super(message);
	}
	
	public PwiException(String message, Throwable e) {
		super(message, e);
	}
	
}

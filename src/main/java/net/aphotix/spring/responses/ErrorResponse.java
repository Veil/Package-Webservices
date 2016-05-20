package net.aphotix.spring.responses;

/**
 * Class used to serialize errors in a nice format for json consumers to handle
 *
 * @author Veil (nathan@aphotix.net).
 */
public class ErrorResponse {

	private final String error;

	public ErrorResponse(String error) {
		this.error = error;
	}

	/**
	 * Get the error message indicating what happened
	 *
	 * @return {@link String} The error message
	 */
	public String getError() {
		return error;
	}
}

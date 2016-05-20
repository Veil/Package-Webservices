package net.aphotix.packages;

/**
 * The exception thrown whenever a package is not found in a context it was expected to exist in.
 *
 * @author Veil (nathan@aphotix.net).
 */
public class PackageNotFoundException extends Exception {

	/**
	 * Create an empty {@link PackageNotFoundException}
	 */
	public PackageNotFoundException() {
		super();
	}

	/**
	 * Create a {@link PackageNotFoundException}
	 *
	 * @param message A message explaining what may have happened
	 */
	public PackageNotFoundException(String message) {
		super(message);
	}

	/**
	 * Create a {@link PackageNotFoundException}
	 *
	 * @param message A message explaining what may have happened
	 * @param e The nested cause exception
	 */
	public PackageNotFoundException(String message, Exception e) {
		super(message, e);
	}

}

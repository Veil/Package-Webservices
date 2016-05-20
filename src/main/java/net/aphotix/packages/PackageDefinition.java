package net.aphotix.packages;

import java.util.List;

/**
 * Represents a package's definition rather than the concrete version which includes full details of the products it
 * contains.
 *
 * @see Package
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface PackageDefinition {

	/**
	 * The id of this package
	 *
	 * @return {@literal long} The unique id of this package
	 */
	public Long getId();

	/**
	 * Get the human readable name of this package
	 *
	 * @return {@link String} The name of this package
	 */
	public String getName();

	/**
	 * Get the description of this package
	 *
	 * @return {@link String} The package description
	 */
	public String getDescription();

	/**
	 * Get the list of ids of products associated with a package of this definition
	 *
	 * @return {@link List} A list of associated product ids
	 */
	public List<String> getProductIds();
}

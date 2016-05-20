package net.aphotix.products;

/**
 * Represents a Product which can be assigned to a package in the system
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface Product {

	/**
	 * Get the id of the product
	 *
	 * @return {@link String} The unique identifiable product id
	 */
	public String getId();

	/**
	 * Get the human readable name of the product
	 *
	 * @return {@link String} The name of the product
	 */
	public String getName();

	/**
	 * Get the price of the product, in USD cents only.
	 *
	 * @return {@literal long} The price of the product
	 */
	public long getPrice();

}
